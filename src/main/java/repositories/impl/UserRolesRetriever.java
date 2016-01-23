package repositories.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import domain.UserRoles;

public class UserRolesRetriever implements IEntityRetriever<UserRoles> {

	@Override
	public UserRoles build(ResultSet rs) throws SQLException {
		UserRoles result = new UserRoles();
		result.setId(rs.getInt("id"));
		result.setName(rs.getString("name"));
		result.setUserId(rs.getInt("userId"));
		result.setRoleId(rs.getInt("roleId"));
		return result;
	}

}