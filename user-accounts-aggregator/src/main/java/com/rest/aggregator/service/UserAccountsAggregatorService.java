package com.rest.aggregator.service;

import java.util.List;

import com.rest.aggregator.dto.UserAccountsAggregatorDto;

public interface UserAccountsAggregatorService {
	//List<UserDetailsDto> findAllUsers();
	
	//List<AccountDetailsDto> findAllAccounts(long userId);
	
	List<UserAccountsAggregatorDto> findAllUserAndAccounts();
}
