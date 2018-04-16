package com.resttemplate;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.EmbeddedServletContainerAutoConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;

import com.rest.constant.ApplicationConstants;
import com.resttemplate.client.AccountOperationsRestfulClient;
import com.resttemplate.client.UserOperationsRestfulClient;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {EmbeddedServletContainerAutoConfiguration.class, WebMvcAutoConfiguration.class})
public class RestClientApplication {

	public static void main(String[] args) {
		AccountOperationsRestfulClient accountOperations = new AccountOperationsRestfulClient();
		UserOperationsRestfulClient userOperations = new UserOperationsRestfulClient();
		
		/*-----------------User Operations-----------------*/
		userOperations.postUser("User 1", "first 1", "last 1", 31);	
		userOperations.postUser("User 2", "first 2", "last 2", 33);	
		userOperations.postUser("User 3", "first 3", "last 3", 35);	
		//userOperations.getUser();		
		//userOperations.getAllUsers();		
		//userOperations.putUser();		
		//userOperations.deleteUser();
		
		/*-----------------Account Operations-----------------*/
		accountOperations.saveAccount(ApplicationConstants.ACCOUNT_TYPE_SAVINGS,"1","Savings Account",1);
		
		accountOperations.saveAccount(ApplicationConstants.ACCOUNT_TYPE_SAVINGS,"2","Savings Account",2);
		accountOperations.saveAccount(ApplicationConstants.ACCOUNT_TYPE_CURRENT,"2","Current Account",2);
		
		accountOperations.saveAccount(ApplicationConstants.ACCOUNT_TYPE_SAVINGS,"3","Savings Account",3);
		accountOperations.saveAccount(ApplicationConstants.ACCOUNT_TYPE_CURRENT,"3","Current Account",3);
		accountOperations.saveAccount(ApplicationConstants.ACCOUNT_TYPE_LOAN,"3","Loan Account",3);
		
		//accountOperations.getAccount();		
		//accountOperations.getAllAccounts();		
		//accountOperations.putAccount();		
		//accountOperations.deleteAccount();	
		
		//userOperations.getAllUsers();		
	}
}