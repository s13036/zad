package repositories.impl;

import java.sql.Connection;

import domain.EnumerationValue;
import domain.RolesPermissions;
import domain.UserRoles;
import repositories.IRepository;
import repositories.IRepositoryCatalog;
import repositories.IUserRepository;
import unitofwork.IUnitOfWork;

public class RepositoryCatalog implements IRepositoryCatalog{

	private Connection connection;
	private IUnitOfWork uow;
	
	public RepositoryCatalog(Connection connection, IUnitOfWork uow) {
		super();
		this.connection = connection;
		this.uow = uow;
	}

	@Override
	public IUserRepository getUsers() {
		return new UserRepository(connection, new UserRetriever(), uow);
	}

	@Override
	public IRepository<UserRoles> getRoles() {
		return null;
	}

	@Override
	public void commit() {
		uow.commit();
	}

	@Override
	public IRepository<EnumerationValue> getEnumerationValue() {

		return new EnumerationValueRepository(connection, 
				new EnumerationValueRetriever(), uow);
	}

	@Override
	public IRepository<RolesPermissions> getRolesPermissions() {
		
		return new RolesPermissionRepository(connection, 
				new RolesPermissionRetriever(), uow);
	}

	@Override
	public IRepository<UserRoles> getUserRoles() {

		return new UserRolesRepository(connection,
				new UserRolesRetriever(), uow);
	}

}