package com.resttemplate.client;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.rest.entity.User;

public class UserOperationsRestfulClient {
	RestTemplate restTemplate;
	
	public UserOperationsRestfulClient(){
		restTemplate = new RestTemplate();
	}
	
	/**
	 * post user
	 */
	public void postUser(String userName, String firstName, String lastName,  int age){
		System.out.println("Begin /POST user!");
		String postUrl = "http://localhost:8080/user-application/users/";
		User user = new User(userName,firstName,lastName,age,null);
				
		ResponseEntity<String> postResponse = restTemplate.postForEntity(postUrl, user, String.class);
		System.out.println("Response for Post Request: " + postResponse.getBody());
	}
	
	
	/**
	 * get user
	 */
	public void getUser(){
		System.out.println("Begin /GET user!");
		String getUrl = "http://localhost:8080/user-application/users/1";
		ResponseEntity<User> getResponse = restTemplate.getForEntity(getUrl, User.class);
		if(getResponse.getBody() != null){
			System.out.println("Response for Get Request: " + getResponse.getBody().toString());	
		}else{
			System.out.println("Response for Get Request: NULL");
		}
	}
	
	/**
	 * get All users
	 */
	public void getAllUsers(){
		System.out.println("Begin /GET All users!");
		String getAllUrl = "http://localhost:8080/user-application/users/getAll";
		
		ResponseEntity<List<User>> getAllResponse =
		        restTemplate.exchange(getAllUrl,HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {});
		
		if(getAllResponse.getBody() != null){
			System.out.println("Response for Get All Request: " + getAllResponse.getBody().toString());	
		}else{
			System.out.println("Response for Get All Request: NULL");
		}
	}
	
	/**
	 * put user
	 */
	/*public void putUser(){
		System.out.println("Begin /PUT user!");
		String putUrl = "http://localhost:8080/user-application/users/1";
		User user = new User(userName,firstName,lastName,age,null);
		restTemplate.put(putUrl, putUser);
	}*/
	
	/**
	 * delete user
	 */
	public void deleteUser(){
		System.out.println("Begin /DELETE user!");
		String deleteUrl = "http://localhost:8080/user-application/users/1";
		restTemplate.delete(deleteUrl);
	}
}
