package com.rest.useraggregate.service;

import java.util.List;

import com.rest.entity.User;

public interface UserAggregateService {
	List<User> findAll();

//	List<Account> findAllAccounts(long id);
	
	public List<User> findAllUsersAndAccounts();
}
