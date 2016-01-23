package repositories.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.EnumerationValue;
import domain.User;
import repositories.*;
import unitofwork.IUnitOfWork;

public class EnumerationValueRepository 
	extends Repository<EnumerationValue> implements IEnumerationValueRepository{

	protected PreparedStatement selectByName;
	protected PreparedStatement selectByValue;
	protected PreparedStatement selectByStringKey;
	
	protected String selectByNameSql=
			"SELECT * FROM "
			+ "t_sys_enums"
			+ " WHERE enumerationName=?";
	
	protected String selectByValueSql=
			"SELECT * FROM "
			+ "t_sys_enums"
			+ " WHERE value=?";
	
	protected String selectByStringKeySql=
			"SELECT * FROM "
			+ "t_sys_enums"
			+ " WHERE stringKey=?";
	

	
	protected EnumerationValueRepository(Connection connection,
			IEntityRetriever<EnumerationValue> retriever, IUnitOfWork uow) {
		super(connection, retriever, uow);
		
		try {
			selectByName=connection.prepareStatement(selectByNameSql);
			selectByValue=connection.prepareStatement(selectByValueSql);
			selectByStringKey=connection.prepareStatement(selectByStringKeySql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<EnumerationValue> withName(String name) {
		
		List<EnumerationValue> result = new ArrayList<EnumerationValue>();
		
		try {
			selectByName.setString(1, name);
			ResultSet rs = selectByName.executeQuery();
			while(rs.next())
			{
				result.add(retriever.build(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public List<EnumerationValue> withValue(String value) {
		
		List<EnumerationValue> result = new ArrayList<EnumerationValue>();
		
		try {
			selectByValue.setString(1, value);
			ResultSet rs = selectByValue.executeQuery();
			while(rs.next())
			{
				result.add(retriever.build(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public List<EnumerationValue> withStringKey(String key) {

		List<EnumerationValue> result = new ArrayList<EnumerationValue>();
		
		try {
			selectByStringKey.setString(1, key);
			ResultSet rs = selectByStringKey.executeQuery();
			while(rs.next())
			{
				result.add(retriever.build(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	protected void setUpUpdateQuery(EnumerationValue entity)
			throws SQLException {
		
		update.setInt(1, entity.getIntKey());
		update.setString(2, entity.getStringKey());
		update.setString(3, entity.getValue());
		update.setString(4, entity.getEnumerationName());
		update.setInt(5, entity.getId());
		
	}

	@Override
	protected void setUpInsertQuery(EnumerationValue entity)
			throws SQLException {
		
		insert.setInt(1, entity.getIntKey());
		insert.setString(2, entity.getStringKey());
		insert.setString(3, entity.getValue());
		insert.setString(4, entity.getEnumerationName());
	}

	@Override
	protected String getCreateTable() {
		
		return 
				"CREATE TABLE IF NOT EXISTS t_sys_enums("
				+ "id int GENERATED BY DEFAULT AS IDENTITY(start with 1),"
				+ "intkey BIGINT,"
				+ "stringkey VARCHAR(20),"
				+ "value VARCHAR(20),"
				+ "enumerationName VARCHAR(20)"
				+ ")";
	}

	@Override
	protected String getTableName() {
		
		return
				"t_sys_enums";
	}

	@Override
	protected String getUpdateQuery() {
		
		return
				"UPDATE t_sys_enums SET (intKey, stringKey, value,"
				+ " enumerationName)=(?,?,?,?) WHERE id=?";
	}

	@Override
	protected String getInsertQuery() {
		
		return
				"INSERT INTO t_sys_enums(intKey, stringKey, value,"
				+ " enumerationName) VALUES(?,?,?,?)";
	}



}