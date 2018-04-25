package com.rest.accountapp.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.rest.accountapp.constants.AccountAppConstants;
import com.rest.accountapp.dto.AccountDto;
import com.rest.accountapp.entity.Account;
import com.rest.accountapp.exceptions.AccountAlreadyExistsException;
import com.rest.accountapp.exceptions.AccountNotFoundException;
import com.rest.accountapp.mapper.AccountMapper;
import com.rest.accountapp.repository.AccountRepository;
import com.rest.accountapp.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private AccountMapper accountMapper;

	@Transactional
	public AccountDto save(com.rest.accountapp.dto.AccountDto accountDto) {
		if (accountRepository.findByTypeAndUserId(accountDto.getType(),accountDto.getUserId()) > 0) {
			throw new AccountAlreadyExistsException(HttpStatus.CONFLICT, AccountAppConstants.ACCOUNT_TYPE_ALREADY_EXISTS);
		}
		
		accountDto.setAccountNumber(String.valueOf(System.currentTimeMillis()));
						
		return accountMapper.convertEntityToDto(accountRepository.save(accountMapper.convertDtoToEntity(accountDto)));
	}

	@Transactional
	public AccountDto update(AccountDto accountDto) {		
		return accountMapper.convertEntityToDto(accountRepository.save(accountMapper.convertDtoToEntity(accountDto)));
	}
	
	public AccountDto findById(long accountId) {		
		Account account =  accountRepository.findOne(accountId);
		
		if (account == null) {
            throw new AccountNotFoundException(HttpStatus.NOT_FOUND,AccountAppConstants.ACCOUNT_NOT_FOUND);
        }
		
		return accountMapper.convertEntityToDto(account);
	}
	
	public List<AccountDto> findAll() {
		List<Account> accountList = accountRepository.findAll();
		
		if(accountList == null || accountList.size() == 0) {
			throw new AccountNotFoundException(HttpStatus.NOT_FOUND,AccountAppConstants.ACCOUNT_NOT_FOUND);
		}
				
		return accountList.stream()
				.map(account -> accountMapper.convertEntityToDto(account))
				.collect(Collectors.toList());
	}

	@Transactional
	public void delete(long accountId) {
		Account account =  accountRepository.findOne(accountId);
		
		if (account == null) {
            throw new AccountNotFoundException(HttpStatus.NOT_FOUND,AccountAppConstants.ACCOUNT_NOT_FOUND);
        }
				
		accountRepository.delete(accountId);
	}

	@Transactional
	public void deleteAll() {
		accountRepository.deleteAll();
	}

	@Override
	public List<AccountDto> findByUserId(long userId) {
		List<Account> accountList = accountRepository.findByUserId(userId);
						
		return accountList.stream()
				.map(account->accountMapper.convertEntityToDto(account))
				.collect(Collectors.toList());
	}
}
