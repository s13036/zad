package domain;

import java.util.*;

public class UserRoles extends Entity {

	private String name;
	private int roleId;
	private int userId;
	
	private List<RolesPermissions> privileges;
	private List<User> users;
	
	public UserRoles()
	{
		privileges=new ArrayList<RolesPermissions>();
		users= new ArrayList<User>();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<RolesPermissions> getPrivileges() {
		return privileges;
	}
	public void setPrivileges(List<RolesPermissions> privileges) {
		this.privileges = privileges;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
}