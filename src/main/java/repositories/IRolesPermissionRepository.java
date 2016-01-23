package repositories;

import java.util.List;

import domain.RolesPermissions;
import domain.User;
import domain.UserRoles;

public interface IRolesPermissionRepository extends IRepository<RolesPermissions> {
	
	public List<RolesPermissions> withRoleId(int roleId);
	public List<RolesPermissions> withPermissionId(int permissionId);

}