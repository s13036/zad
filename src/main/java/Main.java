import java.sql.*;
import java.util.List;

import domain.*;
import repositories.*;
import repositories.impl.RepositoryCatalog;
import unitofwork.IUnitOfWork;
import unitofwork.UnitOfWork;


public class Main {

	public static void main(String[] args) {

		String url="jdbc:hsqldb:hsql://localhost/workdb";
//-------------------------enums value--------------------------------------------		
		EnumerationValue enumR1 = new EnumerationValue();
		enumR1.setIntKey(1);
		enumR1.setStringKey("DAdm");
		enumR1.setValue("Admin");
		enumR1.setEnumerationName("Role");
		
		EnumerationValue enumR2 = new EnumerationValue();
		enumR2.setIntKey(2);
		enumR2.setStringKey("UserGlobal");
		enumR2.setValue("User Global");
		enumR2.setEnumerationName("Role");
		
		EnumerationValue enumR3 = new EnumerationValue();
		enumR3.setIntKey(3);
		enumR3.setStringKey("LocalU");
		enumR3.setValue("Local user");
		enumR3.setEnumerationName("Role");
		
		
		EnumerationValue enumP1 = new EnumerationValue();
		enumP1.setIntKey(1);
		enumP1.setStringKey("FPerm");
		enumP1.setValue("Full permission");
		enumP1.setEnumerationName("Permission");
		
		EnumerationValue enumP2 = new EnumerationValue();
		enumP2.setIntKey(2);
		enumP2.setStringKey("GPrem");
		enumP2.setValue("Global permmision");
		enumP2.setEnumerationName("Permission");
		
		EnumerationValue enumP3 = new EnumerationValue();
		enumP3.setIntKey(3);
		enumP3.setStringKey("LPerm");
		enumP3.setValue("Local permission");
		enumP3.setEnumerationName("Permission");
		
//---------------------------Roles Permisson-------------------------------------------	
		
		RolesPermissions rP1 = new RolesPermissions();
		rP1.setName(enumR1.getValue());
		rP1.setRoleId(enumR1.getIntKey());
		rP1.setPermissionId(enumP1.getIntKey());
		
		RolesPermissions rP2 = new RolesPermissions();
		rP2.setName(enumR2.getValue());
		rP2.setRoleId(enumR2.getIntKey());
		rP2.setPermissionId(enumP2.getIntKey());
		
		RolesPermissions rP3 = new RolesPermissions();
		rP3.setName(enumR3.getValue());
		rP3.setRoleId(enumR3.getIntKey());
		rP3.setPermissionId(enumP3.getIntKey());
		
//---------------------Users --------------------------------------------------------
		User u1 = new User();
		u1.setLogin("YnKam44");
		u1.setPassword("test123");
		
		User u2 = new User();
		u2.setLogin("januszeL");
		u2.setPassword("123456");
		
		User u3 = new User();
		u3.setLogin("noname");
		u3.setPassword("qazwsx");
		
//-------------------------------User roles-----------------------------------------
		
		UserRoles userRoles1 = new UserRoles();
		userRoles1.setName("Administrator");
		userRoles1.setRoleId(rP1.getRoleId());
		userRoles1.setUserId(1);
		
		UserRoles userRoles2 = new UserRoles();
		userRoles2.setName("User Global");
		userRoles2.setRoleId(rP2.getRoleId());
		userRoles2.setUserId(2);
		
		UserRoles userRoles3 = new UserRoles();
		userRoles3.setName("Normal User");
		userRoles3.setRoleId(rP3.getRoleId());
		userRoles3.setUserId(3);
		
		
		
		try {
			
			Connection connection = DriverManager.getConnection(url);
			IUnitOfWork uow = new UnitOfWork(connection);
			
			IRepositoryCatalog catalog = new RepositoryCatalog(connection, uow);
			
			catalog.getEnumerationValue().save(enumR1);
			catalog.getEnumerationValue().save(enumR2);
			catalog.getEnumerationValue().save(enumR3);
			catalog.getEnumerationValue().save(enumP1);
			catalog.getEnumerationValue().save(enumP2);
			catalog.getEnumerationValue().save(enumP3);
			
			catalog.getRolesPermissions().save(rP1);
			catalog.getRolesPermissions().save(rP2);
			catalog.getRolesPermissions().save(rP3);
			
			catalog.getUsers().save(u1);
			catalog.getUsers().save(u2);
			catalog.getUsers().save(u3);
			
			catalog.getUserRoles().save(userRoles1);
			catalog.getUserRoles().save(userRoles2);
			catalog.getUserRoles().save(userRoles3);
			catalog.commit();
			
			
			
			
			List <EnumerationValue> tableEnums = catalog.getEnumerationValue().getAll();
			for(EnumerationValue tableEnum: tableEnums)
				System.out.println(tableEnum.getId()+" "+tableEnum.getIntKey()+" "
						+tableEnum.getStringKey()+" "+tableEnum.getValue()+" "
						+tableEnum.getEnumerationName());
			
			System.out.println("\n----------------------------------------------");
			
			
			List<RolesPermissions> rPsFromDb= catalog.getRolesPermissions().getAll();
			
			for(RolesPermissions rPFromDb: rPsFromDb)
				System.out.println(rPFromDb.getId()+" "+rPFromDb.getName()+""
						+ " "+rPFromDb.getRoleId()+ " "+rPFromDb.getPermissionId());

			System.out.println("\n----------------------------------------------");
			
			
			List<User> usersFromDb= catalog.getUsers().getAll();
			
			for(User userFromDb: usersFromDb)
				System.out.println(userFromDb.getId()+" "+userFromDb.getLogin()+""
						+ " "+userFromDb.getPassword());
			
			System.out.println("\n----------------------------------------------");
			
			List<UserRoles> uRsFromDb= catalog.getUserRoles().getAll();
			
			for(UserRoles uRFromDb: uRsFromDb)
				System.out.println(uRFromDb.getId()+" "+uRFromDb.getName()+""
						+ " "+uRFromDb.getUserId()+ " "+uRFromDb.getRoleId());

			System.out.println("\n----------------------------------------------");
			
			List<User> uRsFromDbwithroleName= catalog.getUsers().withRole("User l1");
			
			for(User uRFromDb: uRsFromDbwithroleName)
				System.out.println(uRFromDb.getId()+" "+uRFromDb.getLogin());

			System.out.println("\n----------------------------------------------");
			
			List<User> uRsFromDbwithroleId= catalog.getUsers().withRole(1);
			
			for(User uRFromDb: uRsFromDbwithroleId)
				System.out.println(uRFromDb.getId()+" "+uRFromDb.getLogin());
			
			
//			User u = catalog.getUsers().get(2);
//			u.setPassword("1qaz2wsx");
//			catalog.getUsers().update(u);
//			
//			
//			catalog.getUsers().delete(usersFromDb.get(0));
//		
//			catalog.commit();
//			
//			for(User userFromDb: catalog.getUsers().getAll())
//				System.out.println(userFromDb.getId()+" "+userFromDb.getLogin()+" "+userFromDb.getPassword());
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("\n---------end-----------");
	}

}