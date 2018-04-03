package com.resttemplate.client;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.rest.dto.AccountDto;
import com.rest.entity.Account;

public class AccountOperationsRestfulClient {	
	RestTemplate restTemplate;
	
	public AccountOperationsRestfulClient(){
		restTemplate = new RestTemplate();
	}
	
	/**
	 * post account
	 */	
	public void saveAccount(String type, String accNumber, String description, long userId){
		System.out.println("Begin /POST account!");
		String postUrl = "http://localhost:8081/account-application/accounts/";
		AccountDto account = new AccountDto(type, accNumber,description, userId);
				
		ResponseEntity<Account> postResponse = restTemplate.postForEntity(postUrl, account, Account.class);
		System.out.println("Response for Post Request: " + postResponse.getBody().toString());
	}
	
	
	/**
	 * get account
	 */
	public void getAccount(){
		System.out.println("Begin /GET account!");
		String getUrl = "http://localhost:8081/account-application/accounts/1";
		ResponseEntity<Account> getResponse = restTemplate.getForEntity(getUrl, Account.class);
		if(getResponse.getBody() != null){
			System.out.println("Response for Get Request: " + getResponse.getBody().toString());	
		}else{
			System.out.println("Response for Get Request: NULL");
		}
	}
	
	/**
	 * get All accounts
	 */
	public void getAllAccounts(){
		System.out.println("Begin /GET All accounts!");
		String getAllUrl = "http://localhost:8081/account-application/accounts/getAll";
		
		ResponseEntity<List<Account>> getAllResponse =
		        restTemplate.exchange(getAllUrl,HttpMethod.GET, null, new ParameterizedTypeReference<List<Account>>() {});
		
		if(getAllResponse.getBody() != null){
			System.out.println("Response for Get All Request: " + getAllResponse.getBody().toString());	
		}else{
			System.out.println("Response for Get All Request: NULL");
		}
	}
	
	/**
	 * put account
	 */
	/*public void putAccount(){
		System.out.println("Begin /PUT account!");
		String putUrl = "http://localhost:8081/account-application/accounts/1";
		Account putAccount = new Account("Account 2");
		restTemplate.put(putUrl, putAccount);
	}*/
	
	/**
	 * delete account
	 */
	public void deleteAccount(){
		System.out.println("Begin /DELETE account!");
		String deleteUrl = "http://localhost:8081/account-application/accounts/1";
		restTemplate.delete(deleteUrl);
	}
}
