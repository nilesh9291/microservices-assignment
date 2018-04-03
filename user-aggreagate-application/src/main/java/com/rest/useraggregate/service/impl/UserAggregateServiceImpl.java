package com.rest.useraggregate.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rest.entity.User;
import com.rest.exception.ResourceNotFoundException;
import com.rest.useraggregate.service.UserAggregateService;
import com.rest.utils.ApplicationConstants;

@Service
public class UserAggregateServiceImpl implements UserAggregateService {
	@Autowired
	private RestTemplateBuilder restTemplate;

	public List<User> findAllUsersAndAccounts() {
		List<User> userList = new ArrayList<User>();
		userList = this.getAllUsersAndAccounts();

		return userList;
	}
	
	public List<User> findAll() {
		List<User> userList = new ArrayList<User>();
		userList = this.getAllUsers();

		return userList;
	}

	private List<User> getAllUsersAndAccounts() {
		String getAllUrl = "http://localhost:8080/user-application/users/allUsersAndAccounts";

		ResponseEntity<List<User>> userList = restTemplate.build().exchange(getAllUrl, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<User>>() {});

		if (userList.getBody() == null) {
			throw new ResourceNotFoundException(null, ApplicationConstants.NOT_FOUND_404);
		}

		return userList.getBody();
	}
	
	private List<User> getAllUsers() {
		String getAllUrl = "http://localhost:8080/user-application/users/";

		ResponseEntity<List<User>> userList = restTemplate.build().exchange(getAllUrl, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<User>>() {});

		if (userList.getBody() == null) {
			throw new ResourceNotFoundException(null, ApplicationConstants.NOT_FOUND_404);
		}

		return userList.getBody();
	}

}
