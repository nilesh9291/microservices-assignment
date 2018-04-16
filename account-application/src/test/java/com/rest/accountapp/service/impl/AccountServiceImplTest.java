//package com.rest.accountapp.service.impl;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import com.rest.dto.AccountDto;
//import com.rest.entity.Account;
//import com.rest.entity.User;
//import com.rest.repository.AccountRepository;
//import com.rest.repository.UserRepository;
//
//
//@RunWith(SpringJUnit4ClassRunner.class)
//public class AccountServiceImplTest {
//	
//	@Mock
//	private AccountRepository accountRepository;
//	
//	@Mock
//	private UserRepository userRepository;
//	
//	@InjectMocks
//	private AccountServiceImpl accountService;
//	
//	@Before
//	public void setup(){
//		MockitoAnnotations.initMocks(this);
//	}
//	
//	//@Test
//	public void testSave(){
//		AccountDto accountDto = new AccountDto("Current","13456","Current Account",1L);
//		accountDto.setId(1L);
//		
//		User user = new User("User 1", "first 1", "last 1", 23,null);
//		user.setId(1L);
//		when(userRepository.findOne(1L)).thenReturn(user);
//		
//		Account account = new Account("Current","13456","Current Account",user);
//		account.setId(1L);
//		
//		when(accountRepository.save(account)).thenReturn(account);
//		
//		Account result = accountService.save(accountDto);
//		assertEquals(Long.valueOf(1), Long.valueOf(result.getId()));
//		//assertEquals("Todo Sample 8", result.getText());
//		//assertEquals(true, resuSlt.isCompleted());
//	}
//	
//	@Test
//	public void testGetById(){
//		Account account = new Account("Current","13456","Current Account",null);
//		account.setId(1L);
//		when(accountRepository.findOne(1L)).thenReturn(account);
//		Account result = accountService.findById(1);
//		assertEquals(Long.valueOf(1), Long.valueOf(result.getId()));
//		//assertEquals("Todo Sample 1", result.getText());
//		//assertEquals(true, result.isCompleted());
//	}
//	
//	@Test
//	public void testGetAll(){
//		Account account1 = new Account("Current","1","Current Account",null);
//		account1.setId(1L);
//		
//		Account account2 = new Account("Loan","3","Loan Account",null);
//		account2.setId(1L);
//		
//		Account account3 = new Account("Savings","5","Savings Account",null);
//		account3.setId(1L);
//		
//		List<Account> accountList = new ArrayList<Account>();
//		accountList.add(account1);
//		accountList.add(account2);
//		accountList.add(account3);
//		when(accountRepository.findAll()).thenReturn(accountList);
//		
//		List<Account> result = accountService.findAll();
//		assertEquals(3, result.size());
//	}
//	
//	@Test
//	public void testDelete(){		
//		Account account = new Account("Savings","5","Savings Account",null);
//		account.setId(1L);
//		
//		when(accountRepository.findOne(1L)).thenReturn(account);
//				
//		accountService.delete(account.getId());
//        verify(accountRepository, times(1)).delete(1L);
//	}
//}
//
