package com.rest.aggregator.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rest.aggregator.constants.UserAccountsAggregatorConstants;
import com.rest.aggregator.dto.AccountDetailsDto;
import com.rest.aggregator.dto.UserAccountsAggregatorDto;
import com.rest.aggregator.dto.UserDetailsDto;
import com.rest.aggregator.exception.UserAccountsNotFoundException;
import com.rest.aggregator.exception.UsersNotFoundException;
import com.rest.aggregator.service.UserAccountsAggregatorService;
import com.rest.aggregator.wrapper.UserAccountsAggregatorResponseWrapper;

@Service
public class UserAccountsAggregatorServiceImpl implements UserAccountsAggregatorService {
	@Autowired
	private RestTemplate restTemplate;
	
	public List<UserAccountsAggregatorDto> findAllUserAndAccounts() {
		List<UserAccountsAggregatorDto> userAccountsList = new ArrayList<UserAccountsAggregatorDto>();
		
		List<UserDetailsDto> userDetailsList = new ArrayList<UserDetailsDto>();
		userDetailsList = this.getAllUsers();
				
		if(userDetailsList == null || userDetailsList.size() == 0) {
			throw new UserAccountsNotFoundException(UserAccountsAggregatorConstants.NOT_FOUND_404);
		}
				
		for (UserDetailsDto userDetailsDto : userDetailsList) {
			this.getAccountDetailsByUser(userDetailsDto);
			userAccountsList.add(new UserAccountsAggregatorDto(userDetailsDto));
		}
	
		return userAccountsList;		
	}
	
	private List<UserDetailsDto> getAllUsers() {
		String getAllUrl = "http://localhost:8080/user-application/users/";

		ResponseEntity<UserAccountsAggregatorResponseWrapper<List<UserDetailsDto>>> userList = null;
		
		try {
			userList = restTemplate.exchange(getAllUrl, HttpMethod.GET, null,
					new ParameterizedTypeReference<UserAccountsAggregatorResponseWrapper<List<UserDetailsDto>>>() {});				
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		if (userList.getBody() == null) {
			throw new UsersNotFoundException(UserAccountsAggregatorConstants.NOT_FOUND_404);
		}

		return userList.getBody().getData();
	}
	
	private void getAccountDetailsByUser(UserDetailsDto userDetailsDto) {
		String getAllUrl = "http://localhost:8081/account-application/accounts/byUser/{userId}";

		Map<String, Long> params = new HashMap<String, Long>();
		params.put("userId", userDetailsDto.getUserId());
		ResponseEntity<UserAccountsAggregatorResponseWrapper<List<AccountDetailsDto>>> accountList = null;
		
		try {
			accountList = restTemplate.exchange(getAllUrl, HttpMethod.GET, null,
					new ParameterizedTypeReference<UserAccountsAggregatorResponseWrapper<List<AccountDetailsDto>>>() {}, params);

		} catch(Exception exception) {
			exception.printStackTrace();
			//throw new UserAccountsExceptionResponse(exception.getMessage());
		}
		
		if (accountList.getBody().getData() != null) {
			userDetailsDto.setAccountDetailsDto(accountList.getBody().getData().stream().collect(Collectors.toSet()));
		} /*else {			
			throw new ResourceNotFoundException(null, ApplicationConstants.NOT_FOUND_404);
		}*/		
	}
}
