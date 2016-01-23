package repositories;

import java.util.List;

import domain.UserRoles;

public interface IUserRolesRepository extends IRepository<UserRoles> {

	public List<UserRoles> withRoleId(int roleId);
	public List<UserRoles> withUserId(int userId);
}