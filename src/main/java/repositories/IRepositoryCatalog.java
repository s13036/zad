package repositories;

import domain.*;

public interface IRepositoryCatalog {

	public IUserRepository getUsers();
	public IRepository<UserRoles> getRoles();
	public IRepository<EnumerationValue> getEnumerationValue();
	public IRepository<RolesPermissions> getRolesPermissions();
	public IRepository<UserRoles> getUserRoles();
	public void commit();
}