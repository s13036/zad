package tests;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import repositories.IRepository;
import repositories.impl.RolesPermissionRepository;
import repositories.impl.RolesPermissionRetriever;
import repositories.impl.UserRolesRepository;
import repositories.impl.UserRolesRetriever;
import unitofwork.IUnitOfWorkRepository;
import unitofwork.UnitOfWork;
import domain.Entity;
import domain.EntityState;
import domain.RolesPermissions;
import domain.UserRoles;

public class RolesPermissionRepositoryTest {

IRepository<RolesPermissions> rolesPermissionRepository;
	
	@Mock
	private Map<Entity, IUnitOfWorkRepository> entities;
	RolesPermissionRetriever retriever;
	Connection connection;
	RolesPermissions rolesPermission;
	
	@InjectMocks
	UnitOfWork uow;
	
	@Before
	public void initMocks() throws SQLException {
		uow = mock (UnitOfWork.class);
	    MockitoAnnotations.initMocks(this);
	    retriever = mock(RolesPermissionRetriever.class);
	    connection = mock(Connection.class);
		when(connection.createStatement()).thenReturn(mock(java.sql.Statement.class));
		when(connection.prepareStatement(anyString())).thenReturn(mock(PreparedStatement.class));
		rolesPermissionRepository = new RolesPermissionRepository(connection, retriever, uow);
		rolesPermission = mock (RolesPermissions.class);
		
	}


	@Test(expected=NullPointerException.class)
	public void test_save_with_null_as_argument() throws SQLException {
		

		rolesPermissionRepository.save(null);
	}

	@Test
	public void test_save_with_correct_argument() throws SQLException {

				
		doCallRealMethod().when(rolesPermission).setState((EntityState) any());
		doCallRealMethod().when(rolesPermission).getState();
		doCallRealMethod().when(uow).markAsNew((Entity) any() ,(IUnitOfWorkRepository) any());
				
		rolesPermissionRepository.save(rolesPermission);
		
		assertSame(EntityState.New, rolesPermission.getState());
	}

	@Test
	public void test_update_with_correct_argument() throws SQLException {
				
		doCallRealMethod().when(rolesPermission).setState((EntityState) any());
		doCallRealMethod().when(rolesPermission).getState();
		doCallRealMethod().when(uow).markAsNew((Entity) any() ,(IUnitOfWorkRepository) any());
				
		rolesPermissionRepository.save(rolesPermission);
		
		doCallRealMethod().when(uow).markAsDirty((Entity) any(), (IUnitOfWorkRepository) any());
		
		rolesPermissionRepository.update(rolesPermission);
		
		assertSame(EntityState.Changed, rolesPermission.getState());
		
		
	}
	
	@Test(expected=IllegalStateException.class)
	public void test_update_with_argument_which_is_not_in_database() throws SQLException {
	
		doCallRealMethod().when(rolesPermission).setState((EntityState) any());
		doCallRealMethod().when(rolesPermission).getState();
		
		doCallRealMethod().when(uow).markAsDirty((Entity) any(), (IUnitOfWorkRepository) any());
		
		rolesPermissionRepository.update(rolesPermission);
	}
	
	@Test(expected=NullPointerException.class)
	public void test_update_with_null_as_argument() throws SQLException {

		
		doCallRealMethod().when(uow).markAsDirty((Entity) any(), (IUnitOfWorkRepository) any());
		
		rolesPermissionRepository.update(null);
	}

	@Test(expected=IllegalStateException.class)
	public void test_delete_with_argument_which_is_not_in_database() throws SQLException {

		
		doCallRealMethod().when(uow).markAsDeleted((Entity) any(), (IUnitOfWorkRepository) any());
		
		rolesPermissionRepository.delete(rolesPermission);
	}
	
	@Test(expected=NullPointerException.class)
	public void test_delete_with_null_as_argument() throws SQLException {
		

		doCallRealMethod().when(uow).markAsDeleted((Entity) any(), (IUnitOfWorkRepository) any());
		
		rolesPermissionRepository.delete(null);
	}
	
	@Test
	public void test_delete_with_correct_argument() throws SQLException {
				
		doCallRealMethod().when(rolesPermission).setState((EntityState) any());
		doCallRealMethod().when(rolesPermission).getState();
		doCallRealMethod().when(uow).markAsNew((Entity) any() ,(IUnitOfWorkRepository) any());
				
		rolesPermissionRepository.save(rolesPermission);
		
		doCallRealMethod().when(uow).markAsDeleted((Entity) any(), (IUnitOfWorkRepository) any());
		
		rolesPermissionRepository.delete(rolesPermission);
		
		assertSame(EntityState.Deleted, rolesPermission.getState());
		
		
	}

}