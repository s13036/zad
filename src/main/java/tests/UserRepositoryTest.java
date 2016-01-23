package tests;

import static org.junit.Assert.*;  
import static org.mockito.Mockito.*;  

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import org.junit.*; 
import org.mockito.*;

import domain.*;
import repositories.IRepository;
import repositories.impl.*;
import unitofwork.*;

public class UserRepositoryTest {
	
	IRepository<User> userRepository;
	
	@Mock
	private Map<Entity, IUnitOfWorkRepository> entities;
	UserRetriever retriever;
	Connection connection;
	User user;
	
	@InjectMocks
	UnitOfWork uow;
	
	@Before
	public void initMocks() throws SQLException {
		uow = mock (UnitOfWork.class);
	    MockitoAnnotations.initMocks(this);
	    retriever = mock(UserRetriever.class);
	    connection = mock(Connection.class);
		when(connection.createStatement()).thenReturn(mock(java.sql.Statement.class));
		when(connection.prepareStatement(anyString())).thenReturn(mock(PreparedStatement.class));
		userRepository = new UserRepository(connection, retriever, uow);
		user = mock (User.class);
	}


	@Test(expected=NullPointerException.class)
	public void test_save_with_null_as_argument() throws SQLException {
		

		userRepository.save(null);
	}

	@Test
	public void test_save_with_correct_argument() throws SQLException {

				
		doCallRealMethod().when(user).setState((EntityState) any());
		doCallRealMethod().when(user).getState();
		doCallRealMethod().when(uow).markAsNew((Entity) any() ,(IUnitOfWorkRepository) any());
				
		userRepository.save(user);
		
		assertSame(EntityState.New, user.getState());
	}

	@Test
	public void test_update_with_correct_argument() throws SQLException {
				
		doCallRealMethod().when(user).setState((EntityState) any());
		doCallRealMethod().when(user).getState();
		doCallRealMethod().when(uow).markAsNew((Entity) any() ,(IUnitOfWorkRepository) any());
				
		userRepository.save(user);
		
		doCallRealMethod().when(uow).markAsDirty((Entity) any(), (IUnitOfWorkRepository) any());
		
		userRepository.update(user);
		
		assertSame(EntityState.Changed, user.getState());
		
		
	}
	
	@Test(expected=IllegalStateException.class)
	public void test_update_with_argument_which_is_not_in_database() throws SQLException {
	
		doCallRealMethod().when(user).setState((EntityState) any());
		doCallRealMethod().when(user).getState();
		
		doCallRealMethod().when(uow).markAsDirty((Entity) any(), (IUnitOfWorkRepository) any());
		
		userRepository.update(user);
	}
	
	@Test(expected=NullPointerException.class)
	public void test_update_with_null_as_argument() throws SQLException {

		
		doCallRealMethod().when(uow).markAsDirty((Entity) any(), (IUnitOfWorkRepository) any());
		
		userRepository.update(null);
	}

	@Test(expected=IllegalStateException.class)
	public void test_delete_with_argument_which_is_not_in_database() throws SQLException {

		
		doCallRealMethod().when(uow).markAsDeleted((Entity) any(), (IUnitOfWorkRepository) any());
		
		userRepository.delete(user);
	}
	
	@Test(expected=NullPointerException.class)
	public void test_delete_with_null_as_argument() throws SQLException {
		

		doCallRealMethod().when(uow).markAsDeleted((Entity) any(), (IUnitOfWorkRepository) any());
		
		userRepository.delete(null);
	}
	
	@Test
	public void test_delete_with_correct_argument() throws SQLException {
				
		doCallRealMethod().when(user).setState((EntityState) any());
		doCallRealMethod().when(user).getState();
		doCallRealMethod().when(uow).markAsNew((Entity) any() ,(IUnitOfWorkRepository) any());
				
		userRepository.save(user);
		
		doCallRealMethod().when(uow).markAsDeleted((Entity) any(), (IUnitOfWorkRepository) any());
		
		userRepository.delete(user);
		
		assertSame(EntityState.Deleted, user.getState());
		
		
	}
}
