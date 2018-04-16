package com.rest.useraggregate.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rest.constant.ApplicationConstants;
import com.rest.entity.Account;
import com.rest.entity.User;
import com.rest.exception.ResourceNotFoundException;
import com.rest.useraggregate.service.UserAggregateService;
import com.rest.wrapper.ResponseWrapper;

@Service
public class UserAggregateServiceImpl implements UserAggregateService {
	@Autowired
	private RestTemplateBuilder restTemplate;
	
	public List<User> findAll() {
		List<User> userList = new ArrayList<User>();
		userList = this.getAllUsers();
		
		if(userList == null || userList.size() == 0) {
			throw new ResourceNotFoundException(ApplicationConstants.NOT_FOUND_404);
		}
		
		for (User user : userList) {
			this.getAccountDetailsByUser(user);
		}

		return userList;
	}
	
	private List<User> getAllUsers() {
		String getAllUrl = "http://localhost:8080/user-application/users/";

		ResponseEntity<ResponseWrapper<List<User>>> userList = restTemplate.build().exchange(getAllUrl, HttpMethod.GET, null,
				new ParameterizedTypeReference<ResponseWrapper<List<User>>>() {});

		if (userList.getBody() == null) {
			throw new ResourceNotFoundException(ApplicationConstants.NOT_FOUND_404);
		}

		return userList.getBody().getData();
	}
	
	private void getAccountDetailsByUser(User user) {
		String getAllUrl = "http://localhost:8081/account-application/accounts/byUser/{userId}";

		Map<String, Long> params = new HashMap<String, Long>();
		params.put("userId", user.getId());

		ResponseEntity<ResponseWrapper<List<Account>>> accountList = restTemplate.build().exchange(getAllUrl, HttpMethod.GET, null,
				new ParameterizedTypeReference<ResponseWrapper<List<Account>>>() {}, params);

		if (accountList.getBody() != null) {
			user.setAccounts(accountList.getBody().getData().stream().collect(Collectors.toSet()));
		} /*else {			
			throw new ResourceNotFoundException(null, ApplicationConstants.NOT_FOUND_404);
		}*/		
	}

}
