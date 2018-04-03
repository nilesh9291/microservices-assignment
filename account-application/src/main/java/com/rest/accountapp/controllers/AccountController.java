package com.rest.accountapp.controllers;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.accountapp.service.AccountService;
import com.rest.dto.AccountDto;
import com.rest.entity.Account;
import com.rest.utils.ApplicationConstants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/accounts")
@Api(value = "account-application", description = "Operations of account in Account Application")
public class AccountController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	AccountService accountService;

	// -------------------Create a Account-------------------------------------------
	@ApiOperation(value = "Add an account")
	@ApiResponses(value = {
            @ApiResponse(code = 201, message = ApplicationConstants.CREATED_201),
            @ApiResponse(code = 401, message = ApplicationConstants.NOT_AUTHORIZED_401),
            @ApiResponse(code = 403, message = ApplicationConstants.FORBIDDEN_403)
    	}
    )
	@PostMapping
	public Account save(@Valid @RequestBody AccountDto accountDto) {		
		return accountService.save(accountDto);
	}

	// ------------------- Update a Account-------------------------------------------
	@ApiOperation(value = "Update an account")
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = ApplicationConstants.SUCCESS_200),
            @ApiResponse(code = 401, message = ApplicationConstants.NOT_AUTHORIZED_401),
            @ApiResponse(code = 403, message = ApplicationConstants.FORBIDDEN_403)
    	}
    )
	@PutMapping(value = "/{id}")
	public Account update(@PathVariable("id") long id, @RequestBody Account account) {			
		return accountService.update(account);
	}

	// -------------------Retrieve Single Account ------------------------------------------
	@ApiOperation(value = "Search an account with an ID", response = Account.class)
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = ApplicationConstants.SUCCESS_200),
            @ApiResponse(code = 404, message = ApplicationConstants.NOT_FOUND_404)
    	}
    )
	@GetMapping(value = "/{id}")
	public Account getById(@PathVariable("id") long id) {
		return accountService.findById(id);
	}
	
	// -------------------Retrieve All Accounts ------------------------------------------
	@ApiOperation(value = "View a list of available account",response = Iterable.class)
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = ApplicationConstants.SUCCESS_200),
            @ApiResponse(code = 401, message = ApplicationConstants.NOT_AUTHORIZED_401),
            @ApiResponse(code = 403, message = ApplicationConstants.FORBIDDEN_403),
            @ApiResponse(code = 404, message = ApplicationConstants.NOT_FOUND_404)
    	}
    )
	@GetMapping(value = "/")
	public List<Account> getAll() {		
		return accountService.findAll();
	}
	
	// -------------------Retrieve All Accounts by userId------------------------------------------
	@ApiOperation(value = "Retrieve All Accounts by userId")
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = ApplicationConstants.SUCCESS_200),
            @ApiResponse(code = 401, message = ApplicationConstants.NOT_AUTHORIZED_401),
            @ApiResponse(code = 403, message = ApplicationConstants.FORBIDDEN_403),
            @ApiResponse(code = 404, message = ApplicationConstants.NOT_FOUND_404)
    	}
    )
	@GetMapping(value = "/byUser/{userId}")
	public List<Account> getAllByUserId(@PathVariable("userId") long userId) {
		return accountService.findByUserId(userId);
	}

	// ------------------- Delete an Account -----------------------------------------
	@ApiOperation(value = "Delete an account")
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = ApplicationConstants.SUCCESS_200),
            @ApiResponse(code = 401, message = ApplicationConstants.NOT_AUTHORIZED_401),
            @ApiResponse(code = 403, message = ApplicationConstants.FORBIDDEN_403),
            @ApiResponse(code = 404, message = ApplicationConstants.NOT_FOUND_404)
    	}
    )
	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable("id") long id) {
		accountService.delete(id);
	}

	// ------------------- Delete All Account-----------------------------
	@ApiOperation(value = "Delete all accounts")
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = ApplicationConstants.SUCCESS_200),
            @ApiResponse(code = 401, message = ApplicationConstants.NOT_AUTHORIZED_401),
            @ApiResponse(code = 403, message = ApplicationConstants.FORBIDDEN_403),
            @ApiResponse(code = 404, message = ApplicationConstants.NOT_FOUND_404)
    	}
    )
	@DeleteMapping(value = "/")
	public void deleteAll() {
		accountService.deleteAll();
	}

}