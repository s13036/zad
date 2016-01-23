package repositories.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import domain.RolesPermissions;

public class RolesPermissionRetriever implements IEntityRetriever<RolesPermissions> {

	@Override
	public RolesPermissions build(ResultSet rs) throws SQLException {
		
		RolesPermissions result = new RolesPermissions();
		result.setId(rs.getInt("id"));
		result.setName(rs.getString("name"));
		result.setRoleId(rs.getInt("roleId"));
		result.setPermissionId(rs.getInt("permissionId"));
		return result;
	}

}