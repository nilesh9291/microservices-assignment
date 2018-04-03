package com.rest.accountapp.service;

import java.util.List;

import com.rest.dto.AccountDto;
import com.rest.entity.Account;

public interface AccountService {	

	Account save(AccountDto accountDto);

	Account update(Account account);
	
	Account findById(long id);

	List<Account> findAll();

	void delete(long id);
	
	void deleteAll();
	
	List<Account> findByUserId(long userId);

}
