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

import com.rest.constant.ApplicationConstants;
import com.rest.dto.AccountDto;
import com.rest.entity.Account;
import com.rest.enums.ResponseStatus;
import com.rest.accountapp.service.AccountService;
import com.rest.wrapper.ResponseWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/accounts")
@Api(value = "account-application", description = "Operations of account in account Application")
public class AccountController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	AccountService accountService;
	
	// -------------------Create a account-------------------------------------------
	@ApiOperation(value = "Add an account")
	@ApiResponses(value = {
            @ApiResponse(code = 201, message = ApplicationConstants.CREATED_201),
            @ApiResponse(code = 401, message = ApplicationConstants.NOT_AUTHORIZED_401),
            @ApiResponse(code = 403, message = ApplicationConstants.FORBIDDEN_403)
    	}
    )
	@PostMapping
	//public account save(@Valid @RequestBody account account) {	
	public ResponseEntity<ResponseWrapper<Account>> save(@Valid @RequestBody AccountDto accountDto) {
		//accountService.save(account);
		
		return new ResponseEntity<ResponseWrapper<Account>>(new ResponseWrapper<Account>(ResponseStatus.SUCCESS,accountService.save(accountDto)),HttpStatus.CREATED);
	}

	// ------------------- Update a account-------------------------------------------
	@ApiOperation(value = "Update an account")
	@ApiResponses(value = {
            @ApiResponse(code = 201, message = ApplicationConstants.SUCCESS_200),
            @ApiResponse(code = 401, message = ApplicationConstants.NOT_AUTHORIZED_401),
            @ApiResponse(code = 403, message = ApplicationConstants.FORBIDDEN_403)
    	}
    )
	@PutMapping(value = "/{id}")
	public ResponseEntity<ResponseWrapper<Account>> update(@PathVariable("id") long id, @RequestBody Account account) {
		//return accountService.update(account);
		return new ResponseEntity<ResponseWrapper<Account>>(new ResponseWrapper<Account>(ResponseStatus.SUCCESS,accountService.update(account)),HttpStatus.OK);
	}

	// -------------------Retrieve Single account ------------------------------------------
	@ApiOperation(value = "Search an account with an ID", response = Account.class)
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = ApplicationConstants.SUCCESS_200),
            @ApiResponse(code = 404, message = ApplicationConstants.NOT_FOUND_404)
    	}
    )
	@GetMapping(value = "/{id}")
	public ResponseEntity<ResponseWrapper<Account>> getById(@PathVariable("id") long id) {		
		//return accountService.findById(id);
		return new ResponseEntity<ResponseWrapper<Account>>(new ResponseWrapper<Account>(ResponseStatus.SUCCESS,accountService.findById(id)),HttpStatus.OK);
	}

	// -------------------Retrieve All account ------------------------------------------	
	@ApiOperation(value = "View a list of available accounts",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ApplicationConstants.SUCCESS_200),
            @ApiResponse(code = 401, message = ApplicationConstants.NOT_AUTHORIZED_401),
            @ApiResponse(code = 403, message = ApplicationConstants.FORBIDDEN_403),
            @ApiResponse(code = 404, message = ApplicationConstants.NOT_FOUND_404)
    	}
    )
	@GetMapping(value = "/")
	public ResponseEntity<ResponseWrapper<List<Account>>> getAll() {		
		//return accountService.findAll();
		return new ResponseEntity<ResponseWrapper<List<Account>>>(new ResponseWrapper<List<Account>>(ResponseStatus.SUCCESS,accountService.findAll()),HttpStatus.OK);
	}
	
	// -------------------Retrieve All Accounts by userId------------------------------------------
	@ApiOperation(value = "Retrieve All Accounts by userId")
	@ApiResponses(value = { @ApiResponse(code = 200, message = ApplicationConstants.SUCCESS_200),
			@ApiResponse(code = 401, message = ApplicationConstants.NOT_AUTHORIZED_401),
			@ApiResponse(code = 403, message = ApplicationConstants.FORBIDDEN_403),
			@ApiResponse(code = 404, message = ApplicationConstants.NOT_FOUND_404) })
	@GetMapping(value = "/byUser/{userId}")
	public ResponseEntity<ResponseWrapper<List<Account>>> getAllByUserId(@PathVariable("userId") long userId) {
		//return accountService.findByUserId(userId);
		return new ResponseEntity<ResponseWrapper<List<Account>>>(new ResponseWrapper<List<Account>>(ResponseStatus.SUCCESS,accountService.findByUserId(userId)),HttpStatus.OK);
	}
		
	// ------------------- Delete a account -----------------------------------------
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

	// ------------------- Delete All account-----------------------------
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