package com.rest.userapp.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rest.entity.User;
import com.rest.repository.UserRepository;


@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceImplTest {
	
	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	private UserServiceImpl userService;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testSave(){
		User user = new User("User 1", "first 1", "last 1", 23,null);
		user.setId(1L);
		when(userRepository.save(user)).thenReturn(user);
		
		User result = userService.save(user);
		assertEquals(Long.valueOf(1), Long.valueOf(result.getId()));
		//assertEquals("Todo Sample 8", result.getText());
		//assertEquals(true, resuSlt.isCompleted());
	}
	
	@Test
	public void testGetById(){
		User user = new User("User 1", "first 1", "last 1", 23,null);
		user.setId(1L);
		when(userRepository.findOne(1L)).thenReturn(user);
		User result = userService.findById(1);
		assertEquals(Long.valueOf(1), Long.valueOf(result.getId()));
		//assertEquals("Todo Sample 1", result.getText());
		//assertEquals(true, result.isCompleted());
	}
	
	@Test
	public void testGetAll(){
		User user1 = new User("User 1", "first 1", "last 1", 23,null);
		user1.setId(1L);
		
		User user2 = new User("User 2", "first 2", "last 2", 23,null);
		user2.setId(1L);
		
		User user3 = new User("User 3", "first 3", "last 3", 33,null);
		user3.setId(1L);
		
		List<User> userList = new ArrayList<User>();
		userList.add(user1);
		userList.add(user2);
		userList.add(user3);
		when(userRepository.findAll()).thenReturn(userList);
		
		List<User> result = userService.findAll();
		assertEquals(3, result.size());
	}
	
	@Test
	public void testDelete(){		
		User user = new User("User 1", "first 1", "last 1", 23,null);
		user.setId(1L);
		
		when(userRepository.findOne(1L)).thenReturn(user);
				
		userService.delete(user.getId());
        verify(userRepository, times(1)).delete(1L);
	}
}

