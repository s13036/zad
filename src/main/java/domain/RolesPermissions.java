package domain;

import java.util.ArrayList;
import java.util.List;

public class RolesPermissions extends Entity {

	private String name;
	private int roleId;
	private int permissionId;
	
	
	private List<UserRoles> roles;

	public RolesPermissions()
	{
		roles = new ArrayList<UserRoles>();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<UserRoles> getRoles() {
		return roles;
	}

	public void setRoles(List<UserRoles> roles) {
		this.roles = roles;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(int permissionId) {
		this.permissionId = permissionId;
	}
	
	
}