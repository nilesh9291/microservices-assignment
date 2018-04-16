//package com.rest.userapp.mock.controllers;
//
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertNull;
//import static org.mockito.Matchers.any;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.util.Arrays;
//import java.util.List;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import com.google.gson.reflect.TypeToken;
//import com.rest.entity.User;
//import com.rest.userapp.TestUtils;
//import com.rest.userapp.controllers.UserController;
//import com.rest.userapp.service.UserService;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(UserController.class)
//public class UserControllerTest {
//
//	@Autowired
//	private MockMvc mockMvc;
//
//	@MockBean
//	UserService userService;
//
//	private final String URL = "/users/";
//
//	//@Test
//	public void testSave() throws Exception {
//
//		// prepare data and mock's behavior
//		User userStub = new User("User 1", "first 1", "last 1", 23,null);
//		userStub.setId(1L);
//		when(userService.save(any(User.class))).thenReturn(userStub);
//
//		// execute
//		MvcResult result = mockMvc.perform(MockMvcRequestBuilders
//				.post(URL)
//				.contentType(MediaType.APPLICATION_JSON_UTF8)
//				.accept(MediaType.APPLICATION_JSON_UTF8)
//				.content(TestUtils.objectToJson(userStub)))
//				.andReturn();
//
//		// verify
//		int status = result.getResponse().getStatus();
//		assertEquals("Incorrect Response Status", HttpStatus.OK.value(), status);
//
//		// verify that service method was called once
//		verify(userService).save(any(User.class));
//
//		User resultUser = TestUtils.jsonToObject(result.getResponse().getContentAsString(), User.class);
//		assertNotNull(resultUser);
//		assertEquals(1l, resultUser.getId().longValue());
//	}
//	
//	@Test
//	public void testUpdate() throws Exception {
//		// prepare data and mock's behavior
//		// here the stub is the updated user object with ID equal to ID of
//		// user need to be updated
//		User userStub = new User("User 1", "updated first 1", "updated last 1", 23,null);
//		userStub.setId(1L);
//		when(userService.findById(any(Long.class))).thenReturn(userStub);
//				
//		// execute
//		MvcResult result = mockMvc.perform(MockMvcRequestBuilders
//				.put(URL + "{id}", new Long(1))
//				.contentType(MediaType.APPLICATION_JSON_UTF8)
//				.accept(MediaType.APPLICATION_JSON_UTF8)
//				.content(TestUtils.objectToJson(userStub)))
//				.andReturn();
//
//		// verify
//		int status = result.getResponse().getStatus();
//		assertEquals("Incorrect Response Status", HttpStatus.OK.value(), status);
//
//		// verify that service method was called once
//		//verify(userService).save(any(User.class));
//	}
//
//	@Test
//	public void testGetById() throws Exception {
//		// prepare data and mock's behavior
//		User userStub = new User("User 1", "first 1", "last 1", 23,null);
//		userStub.setId(1L);
//		when(userService.findById(any(Long.class))).thenReturn(userStub);
//
//		// execute
//		MvcResult result = mockMvc
//				.perform(MockMvcRequestBuilders
//				.get(URL + "{id}", new Long(1))
//				.accept(MediaType.APPLICATION_JSON_UTF8))
//				.andReturn();
//
//		// verify
//		int status = result.getResponse().getStatus();
//		assertEquals("Incorrect Response Status", HttpStatus.OK.value(), status);
//
//		// verify that service method was called once
//		verify(userService).findById(any(Long.class));
//
//		User resultUser = TestUtils.jsonToObject(result.getResponse().getContentAsString(), User.class);
//		assertNotNull(resultUser);
//		assertEquals(1l, resultUser.getId().longValue());
//	}
//
//	//@Test
//	public void testGetByIdNotExist() throws Exception {
//		// prepare data and mock's behavior
//		// Not Required as user Not Exist scenario
//
//		// execute
//		MvcResult result = mockMvc
//				.perform(MockMvcRequestBuilders
//				.get(URL + "{id}", new Long(1))
//				.accept(MediaType.APPLICATION_JSON_UTF8))
//				.andReturn();
//
//		// verify
//		int status = result.getResponse().getStatus();
//		assertEquals("Incorrect Response Status", HttpStatus.NOT_FOUND.value(), status);
//
//		// verify that service method was called once
//		verify(userService).findById(any(Long.class));
//
//		User resultUser = TestUtils.jsonToObject(result.getResponse().getContentAsString(), User.class);
//		assertNull(resultUser);
//	}
//
//	@Test
//	public void testGetAll() throws Exception {
//		// prepare data and mock's behavior
//		List<User> userList = buildUsers();
//		when(userService.findAll()).thenReturn(userList);
//
//		// execute
//		MvcResult result = mockMvc.perform(MockMvcRequestBuilders
//				.get(URL)
//				.accept(MediaType.APPLICATION_JSON_UTF8))
//				.andReturn();
//
//		// verify
//		int status = result.getResponse().getStatus();
//		assertEquals("Incorrect Response Status", HttpStatus.OK.value(), status);
//
//		// verify that service method was called once
//		verify(userService).findAll();
//
//		// get the List<User> from the Json response
//		TypeToken<List<User>> token = new TypeToken<List<User>>() {};
//		@SuppressWarnings("unchecked")
//		List<User> userListResult = TestUtils.jsonToList(result.getResponse().getContentAsString(), token);
//
//		assertNotNull("Users not found", userListResult);
//		assertEquals("Incorrect User List", userList.size(), userListResult.size());
//	}
//	
//	//@Test
//	public void testDelete() throws Exception {
//		// prepare data and mock's behavior
//		User userStub = new User("User 1", "first 1", "last 1", 23,null);
//		userStub.setId(1L);
//		when(userService.findById(any(Long.class))).thenReturn(userStub);
//
//		// execute
//		MvcResult result = mockMvc.perform(MockMvcRequestBuilders
//		.delete(URL + "{id}", new Long(1))).andReturn();
//
//		// verify
//		int status = result.getResponse().getStatus();
//		assertEquals("Incorrect Response Status", HttpStatus.GONE.value(), status);
//
//		// verify that service method was called once
//		verify(userService).delete(any(Long.class));
//
//	}	
//
//	private List<User> buildUsers() {
//		User user1 = new User("User 1", "first 1", "last 1", 23,null);
//		User user2 = new User("User 2", "first 2", "last2 ", 23,null);
//		List<User> userList = Arrays.asList(user1,user2);
//		return userList;
//	}
//	
//	@Configuration
//	@ComponentScan(basePackages = {"com.rest.userapp.controllers"})
//	public static class TestConfiguration {
//	}
//
//}
