package com.rest.userapp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rest.entity.Account;
import com.rest.entity.User;
import com.rest.exception.ResourceExistsException;
import com.rest.exception.ResourceNotFoundException;
import com.rest.repository.UserRepository;
import com.rest.userapp.service.UserService;
import com.rest.utils.ApplicationConstants;

@Service
public class UserServiceImpl implements UserService {	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RestTemplateBuilder restTemplate;
	
	@Transactional
	public User save(User user) {		
		if (userRepository.findByUserName(user.getUserName()) != null) {
			 throw new ResourceExistsException(user.getUserName(),ApplicationConstants.CONFLICT_FOUND_409);
		}
		
		return userRepository.save(user);
	}

	public User update(User user) {
		return userRepository.save(user);
	}
	
	public User findById(long id) {
		User user =  userRepository.findOne(id);
		
		if (user == null) {
            throw new ResourceNotFoundException(id, ApplicationConstants.NOT_FOUND_404);
        }
		
		return user;
	}
	
	public List<User> findAll() {
		List<User> userList = userRepository.findAll();
				
		if(userList == null || userList.size() == 0) {
			throw new ResourceNotFoundException(null, ApplicationConstants.NOT_FOUND_404);
		}
				
		return userList;
	}
	
	public List<User> findAllUsersAndAccounts() {
		List<User> userList = userRepository.findAll();
				
		if(userList == null || userList.size() == 0) {
			throw new ResourceNotFoundException(null, ApplicationConstants.NOT_FOUND_404);
		}
		
		for (User user : userList) {
			this.getAccountDetailsByUser(user);
		}
		
		return userList;
	}
	
	public List<Account> findAllAccounts(long userId) {
		User user = userRepository.findOne(userId);

		if (user == null) {
            throw new ResourceNotFoundException(userId, ApplicationConstants.NOT_FOUND_404);
        }
		
		this.getAccountDetailsByUser(user);

		List<Account> accountList = user.getAccounts().stream().collect(Collectors.toList());
		
		if (accountList == null || accountList.size() == 0) {
			throw new ResourceNotFoundException(null, ApplicationConstants.NOT_FOUND_404);
		}	

		return accountList;
	}
	
	public void delete(long id) {
		User user =  userRepository.findOne(id);
		
		if (user == null) {
            throw new ResourceNotFoundException(id, ApplicationConstants.NOT_FOUND_404);
        }
		userRepository.delete(id);
	}
	
	public void deleteAll() {
		userRepository.deleteAll();
	}
	
	public User findByUserName(String name) {		
		return userRepository.findByUserName(name);
	}
	
	private void getAccountDetailsByUser(User user) {
		String getAllUrl = "http://localhost:8081/account-application/accounts/byUser/{id}";

		Map<String, Long> params = new HashMap<String, Long>();
		params.put("id", user.getId());

		ResponseEntity<List<Account>> accountList = restTemplate.build().exchange(getAllUrl, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Account>>() {}, params);

		if (accountList.getBody() != null) {
			user.setAccounts(accountList.getBody().stream().collect(Collectors.toSet()));
		} /*else {			
			throw new ResourceNotFoundException(null, ApplicationConstants.NOT_FOUND_404);
		}*/		
	}

}
