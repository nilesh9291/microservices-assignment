package com.rest.accountapp.mapper;

import org.springframework.stereotype.Component;

import com.rest.accountapp.dto.AccountDto;
import com.rest.accountapp.entity.Account;

@Component
public class AccountMapper {
	public Account convertDtoToEntity(AccountDto accountDto) {
		Account account = null;

		if (accountDto != null) {
			account = new Account();
			account.setId(accountDto.getAccountId());
			account.setType(accountDto.getType());
			account.setAccountNumber(accountDto.getAccountNumber());
			account.setDescription(accountDto.getDescription());
			account.setUserId(accountDto.getUserId());
		}

		return account;
	}
	
	public AccountDto convertEntityToDto(Account account) {
		AccountDto accountDto = null;

		if (account != null) {
			accountDto = new AccountDto();
			accountDto.setAccountId(account.getId());
			accountDto.setType(account.getType());
			accountDto.setAccountNumber(account.getAccountNumber());
			accountDto.setDescription(account.getDescription());
			accountDto.setUserId(account.getUserId());
		}

		return accountDto;
	}

}
