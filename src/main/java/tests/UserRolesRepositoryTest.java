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
import repositories.impl.*;
import unitofwork.IUnitOfWorkRepository;
import unitofwork.UnitOfWork;
import domain.*;

public class UserRolesRepositoryTest {

IRepository<UserRoles> userRolesRepository;
	
	@Mock
	private Map<Entity, IUnitOfWorkRepository> entities;
	UserRolesRetriever retriever;
	Connection connection;
	UserRoles userRoles;
	
	@InjectMocks
	UnitOfWork uow;
	
	@Before
	public void initMocks() throws SQLException {
		uow = mock (UnitOfWork.class);
	    MockitoAnnotations.initMocks(this);
	    retriever = mock(UserRolesRetriever.class);
	    connection = mock(Connection.class);
		when(connection.createStatement()).thenReturn(mock(java.sql.Statement.class));
		when(connection.prepareStatement(anyString())).thenReturn(mock(PreparedStatement.class));
		userRolesRepository = new UserRolesRepository(connection, retriever, uow);
		userRoles = mock (UserRoles.class);
		
	}


	@Test(expected=NullPointerException.class)
	public void test_save_with_null_as_argument() throws SQLException {
		

		userRolesRepository.save(null);
	}

	@Test
	public void test_save_with_correct_argument() throws SQLException {

				
		doCallRealMethod().when(userRoles).setState((EntityState) any());
		doCallRealMethod().when(userRoles).getState();
		doCallRealMethod().when(uow).markAsNew((Entity) any() ,(IUnitOfWorkRepository) any());
				
		userRolesRepository.save(userRoles);
		
		assertSame(EntityState.New, userRoles.getState());
	}

	@Test
	public void test_update_with_correct_argument() throws SQLException {
				
		doCallRealMethod().when(userRoles).setState((EntityState) any());
		doCallRealMethod().when(userRoles).getState();
		doCallRealMethod().when(uow).markAsNew((Entity) any() ,(IUnitOfWorkRepository) any());
				
		userRolesRepository.save(userRoles);
		
		doCallRealMethod().when(uow).markAsDirty((Entity) any(), (IUnitOfWorkRepository) any());
		
		userRolesRepository.update(userRoles);
		
		assertSame(EntityState.Changed, userRoles.getState());
		
		
	}
	
	@Test(expected=IllegalStateException.class)
	public void test_update_with_argument_which_is_not_in_database() throws SQLException {
	
		doCallRealMethod().when(userRoles).setState((EntityState) any());
		doCallRealMethod().when(userRoles).getState();
		
		doCallRealMethod().when(uow).markAsDirty((Entity) any(), (IUnitOfWorkRepository) any());
		
		userRolesRepository.update(userRoles);
	}
	
	@Test(expected=NullPointerException.class)
	public void test_update_with_null_as_argument() throws SQLException {

		
		doCallRealMethod().when(uow).markAsDirty((Entity) any(), (IUnitOfWorkRepository) any());
		
		userRolesRepository.update(null);
	}

	@Test(expected=IllegalStateException.class)
	public void test_delete_with_argument_which_is_not_in_database() throws SQLException {

		
		doCallRealMethod().when(uow).markAsDeleted((Entity) any(), (IUnitOfWorkRepository) any());
		
		userRolesRepository.delete(userRoles);
	}
	
	@Test(expected=NullPointerException.class)
	public void test_delete_with_null_as_argument() throws SQLException {
		

		doCallRealMethod().when(uow).markAsDeleted((Entity) any(), (IUnitOfWorkRepository) any());
		
		userRolesRepository.delete(null);
	}
	
	@Test
	public void test_delete_with_correct_argument() throws SQLException {
				
		doCallRealMethod().when(userRoles).setState((EntityState) any());
		doCallRealMethod().when(userRoles).getState();
		doCallRealMethod().when(uow).markAsNew((Entity) any() ,(IUnitOfWorkRepository) any());
				
		userRolesRepository.save(userRoles);
		
		doCallRealMethod().when(uow).markAsDeleted((Entity) any(), (IUnitOfWorkRepository) any());
		
		userRolesRepository.delete(userRoles);
		
		assertSame(EntityState.Deleted, userRoles.getState());
		
		
	}

}