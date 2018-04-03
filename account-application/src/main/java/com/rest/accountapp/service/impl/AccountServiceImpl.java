package com.rest.accountapp.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rest.accountapp.service.AccountService;
import com.rest.dto.AccountDto;
import com.rest.entity.Account;
import com.rest.entity.User;
import com.rest.exception.ResourceExistsException;
import com.rest.exception.ResourceNotFoundException;
import com.rest.repository.AccountRepository;
import com.rest.repository.UserRepository;
import com.rest.utils.ApplicationConstants;

@Service
public class AccountServiceImpl implements AccountService {	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Transactional
	public Account save(AccountDto accountDto) {
		if (accountRepository.findByTypeAndUserId(accountDto.getType(),accountDto.getUserId()) > 0) {
			throw new ResourceExistsException(accountDto.getType(), ApplicationConstants.CONFLICT_FOUND_409);
		}
		
		Account account = new Account();
		account.setAccountNumber(accountDto.getAccountNumber());
		account.setDescription(accountDto.getDescription());
		account.setType(accountDto.getType());
		
		User user = userRepository.findOne(accountDto.getUserId());
		account.setUser(user);
		
		return accountRepository.save(account);
	}

	public Account update(Account account) {		
		return accountRepository.save(account);
	}
	
	public Account findById(long id) {		
		Account account =  accountRepository.findOne(id);
		
		if (account == null) {
            throw new ResourceNotFoundException(id, ApplicationConstants.NOT_FOUND_404);
        }
		
		return account;
	}
	
	public List<Account> findAll() {
		List<Account> accountList = accountRepository.findAll();
		
		if(accountList == null || accountList.size() == 0) {
			throw new ResourceNotFoundException(null, ApplicationConstants.NOT_FOUND_404);
		}
				
		return accountList;
	}	

	public void delete(long id) {
		Account account =  accountRepository.findOne(id);
		
		if (account == null) {
            throw new ResourceNotFoundException(id, ApplicationConstants.NOT_FOUND_404);
        }
				
		accountRepository.delete(id);
	}
	
	public void deleteAll() {
		accountRepository.deleteAll();
	}

	@Override
	public List<Account> findByUserId(long userId) {
		List<Account> accountList = accountRepository.findByUserId(userId);
		
		/*if(accountList == null || accountList.size() == 0) {
			throw new ResourceNotFoundException(null, ApplicationConstants.NOT_FOUND_404);
		}*/
				
		return accountList;
	}
}
