package com.rest.userapp.service;

import java.util.List;

import com.rest.entity.Account;
import com.rest.entity.User;

public interface UserService {//extends CRUDService<User> {
	User save(User user);
	
	User update(User user);

	User findById(long id);

	List<User> findAll();

	void delete(long id);
	
	void deleteAll();

	User findByUserName(String userName);

	//boolean isUserExist(String userName);
	
	//List<Account> findAllAccounts(long id);
	
	//List<User> findAllUsersAndAccounts();
}
