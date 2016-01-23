package repositories.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import repositories.IRepository;
import unitofwork.IUnitOfWork;
import unitofwork.IUnitOfWorkRepository;
import domain.Entity;

public abstract class Repository<TEntity extends Entity> 
	implements IRepository<TEntity>, IUnitOfWorkRepository
{
	
	protected IUnitOfWork uow;
	protected Connection connection;
	protected PreparedStatement selectByID;
	protected PreparedStatement insert;
	protected PreparedStatement delete;
	protected PreparedStatement update;
	protected PreparedStatement selectAll;
	protected IEntityRetriever<TEntity> retriever;

	protected String selectByIDSql=
			"SELECT * FROM "
			+ getTableName()
			+ " WHERE id=?";
	protected String deleteSql=
			"DELETE FROM "
			+ getTableName()
			+ " WHERE id=?";
	protected String selectAllSql=
			"SELECT * FROM " + getTableName();
	
	protected Repository(Connection connection,
			IEntityRetriever<TEntity> retriever, IUnitOfWork uow)
	{
		this.uow=uow;
		this.retriever=retriever;
		this.connection = connection;
		try {
			Statement createTable = connection.createStatement();
			createTable.executeUpdate(getCreateTable());
			
			selectByID=connection.prepareStatement(selectByIDSql);
			insert = connection.prepareStatement(getInsertQuery());
			delete = connection.prepareStatement(deleteSql);
			update = connection.prepareStatement(getUpdateQuery());
			selectAll = connection.prepareStatement(selectAllSql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void save (TEntity entity)
	{
    	checkNullPointerException(entity);
		uow.markAsNew(entity, this);
	}

	public void update(TEntity entity)
	{
		checkNullPointerException(entity);
    	if(entity.getState()==null)
		throw new IllegalStateException();
    	else
		uow.markAsDirty(entity, this);
	}

	public void delete(TEntity entity)
	{
    	checkNullPointerException(entity);
    	if(entity.getState()==null)
    		throw new IllegalStateException();
    	else
		uow.markAsDeleted(entity, this);
	}

	public void persistAdd(Entity entity) {

		try {
			setUpInsertQuery((TEntity)entity);
			insert.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
	}


	@Override
	public void persistUpdate(Entity entity) {

		try {
			setUpUpdateQuery((TEntity)entity);
			update.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void persistDelete(Entity entity) {

		try {
			delete.setInt(1, entity.getId());
			delete.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public TEntity get(int id) {

		try {
			selectByID.setInt(1, id);
			ResultSet rs = selectByID.executeQuery();
			while(rs.next())
			{
				return retriever.build(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}



	@Override
	public List<TEntity> getAll() {

		List<TEntity> result = new ArrayList<TEntity>();
		
		try {
			ResultSet rs= selectAll.executeQuery();
			while(rs.next())
			{
				result.add(retriever.build(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	private void checkNullPointerException (Entity entity){
		
    	if(entity==null)
			throw new NullPointerException();
		
	}
	
	protected abstract void setUpUpdateQuery(TEntity entity) throws SQLException;
	protected abstract void setUpInsertQuery(TEntity entity) throws SQLException;
	protected abstract String getCreateTable();
	protected abstract String getTableName();
	protected abstract String getUpdateQuery();
	protected abstract String getInsertQuery();
}