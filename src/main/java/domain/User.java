package domain;

import java.util.ArrayList;
import java.util.List;

public class User extends Entity {

	private String login;
	private String password;
	
	private List<UserRoles> roles;
	private List<RolesPermissions> permission;
	
	public User()
	{
		roles=new ArrayList<UserRoles>();
		permission=new ArrayList<RolesPermissions>();
	}
	
	public List<UserRoles> getRoles() {
		return roles;
	}
	public void setRoles(List<UserRoles> roles) {
		this.roles = roles;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}