package repositories;

import java.util.List;

import domain.*;

public interface IUserRepository extends IRepository<User>{

	public List<User> withRole(UserRoles role);
	public List<User> withRole(String roleName);
	public List<User> withRole(int roleId);
}