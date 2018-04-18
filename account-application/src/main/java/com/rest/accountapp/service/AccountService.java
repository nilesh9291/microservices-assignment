package com.rest.accountapp.service;

import java.util.List;

import com.rest.accountapp.dto.AccountDto;

public interface AccountService {	

	AccountDto save(AccountDto accountDto);

	AccountDto update(AccountDto accountDto);
	
	AccountDto findById(long accountId);

	List<AccountDto> findAll();

	void delete(long accountId);
	
	void deleteAll();
	
	List<AccountDto> findByUserId(long userId);

}
