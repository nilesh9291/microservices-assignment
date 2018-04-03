package com.rest.accountapp.controllers;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.rest.accountapp.TestUtils;
import com.rest.accountapp.service.AccountService;
import com.rest.dto.AccountDto;
import com.rest.entity.Account;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountController.class)
public class AccountControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	AccountService accountService;

	private final String URL = "/accounts/";

	@Test
	public void testAddAccount() throws Exception {

		// prepare data and mock's behavior
		AccountDto accountStub = new AccountDto("Current","13456","Current Account",1L);
		accountStub.setId(1L);
		Account account = new Account("Current","13456","Current Account",null);
		account.setId(1L);
		when(accountService.save(any(AccountDto.class))).thenReturn(account);

		// execute
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders
				.post(URL)
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.content(TestUtils.objectToJson(accountStub)))
				.andReturn();

		// verify
		int status = result.getResponse().getStatus();
		assertEquals("Incorrect Response Status", HttpStatus.OK.value(), status);

		// verify that service method was called once
		verify(accountService).save(any(AccountDto.class));

		Account resultAccount = TestUtils.jsonToObject(result.getResponse().getContentAsString(), Account.class);
		assertNotNull(resultAccount);
		assertEquals(1l, resultAccount.getId().longValue());
	}
	
	@Test
	public void testUpdateAccount() throws Exception {
		// prepare data and mock's behavior
		// here the stub is the updated account object with ID equal to ID of
		// account need to be updated
		Account accountStub = new Account("Current","13456","Current Account",null);
		accountStub.setId(1L);
		when(accountService.findById(any(Long.class))).thenReturn(accountStub);
				
		// execute
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put(URL + "{id}", new Long(1))
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.content(TestUtils.objectToJson(accountStub)))
				.andReturn();

		// verify
		int status = result.getResponse().getStatus();
		assertEquals("Incorrect Response Status", HttpStatus.OK.value(), status);

		// verify that service method was called once
		//verify(accountService).save(any(Account.class));
	}

	@Test
	public void testGetAccount() throws Exception {
		// prepare data and mock's behavior
		Account accountStub = new Account("Current","13456","Current Account",null);;
		accountStub.setId(1L);
		when(accountService.findById(any(Long.class))).thenReturn(accountStub);

		// execute
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.get(URL + "{id}", new Long(1))
				.accept(MediaType.APPLICATION_JSON_UTF8))
				.andReturn();

		// verify
		int status = result.getResponse().getStatus();
		assertEquals("Incorrect Response Status", HttpStatus.OK.value(), status);

		// verify that service method was called once
		verify(accountService).findById(any(Long.class));

		Account resultAccount = TestUtils.jsonToObject(result.getResponse().getContentAsString(), Account.class);
		assertNotNull(resultAccount);
		assertEquals(1l, resultAccount.getId().longValue());
	}

	//@Test
	public void testGetAccountNotExist() throws Exception {
		// prepare data and mock's behavior
		// Not Required as account Not Exist scenario

		// execute
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.get(URL + "{id}", new Long(1))
				.accept(MediaType.APPLICATION_JSON_UTF8))
				.andReturn();

		// verify
		int status = result.getResponse().getStatus();
		assertEquals("Incorrect Response Status", HttpStatus.NOT_FOUND.value(), status);

		// verify that service method was called once
		verify(accountService).findById(any(Long.class));

		Account resultAccount = TestUtils.jsonToObject(result.getResponse().getContentAsString(), Account.class);
		assertNull(resultAccount);
	}

	//@Test
	public void testDeleteAccount() throws Exception {
		// prepare data and mock's behavior
		Account accountStub = new Account("Current","13456","Current Account",null);
		accountStub.setId(1L);
		when(accountService.findById(any(Long.class))).thenReturn(accountStub);

		// execute
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete(URL + "{id}", new Long(1))).andReturn();

		// verify
		int status = result.getResponse().getStatus();
		assertEquals("Incorrect Response Status", HttpStatus.GONE.value(), status);

		// verify that service method was called once
		verify(accountService).delete(any(Long.class));

	}	

	private List<Account> buildAccounts() {
		Account account1 = new Account("Current","13456","Current Account",null);
		Account account2 = new Account("Loan","42424","Loan Account",null);
		List<Account> accountList = Arrays.asList(account1,account2);
		return accountList;
	}
	
	@Configuration
	@ComponentScan(basePackages = {"com.rest.accountapp.controllers"})
	public static class TestConfiguration {
	}

}
