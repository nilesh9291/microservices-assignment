package com.rest.accountapp.controllers;

import java.util.List;

import javax.validation.Valid;

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

import com.rest.accountapp.dto.AccountDto;
import com.rest.accountapp.service.AccountService;
import com.rest.accountapp.constants.AccountAppConstants;
import com.rest.accountapp.enums.AccountResponseStatus;
import com.rest.accountapp.wrapper.AccountResponseWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * This controller class specifies the operations performed on a account.
 * The typical operations performed includes the create, update, retrive, retriveAll, delete and deleteAll operations.  
 * @author ii
 *
 */

@RestController
@RequestMapping("/accounts")
@Api(value = "account-application", description = "Operations of account in account Application")
public class AccountController {
	
	@Autowired
	AccountService accountService;
	
	// -------------------Create a account-------------------------------------------
	/**
	 * The method inserts a new record into the database table for the account entity.
	 * On successful insertion the response is returned in jsend format.
	 * The response includes the status as string, data as array and the error object along with the error code and error message if any.    
	 * @param accountDto
	 * @return response Entity with a Response Wrapper
	 * @see ResponseEntity<ResponseWrapper<<AccountDto>>
	 */
	@ApiOperation(value = "Add an account")
	@ApiResponses(value = {
            @ApiResponse(code = 201, message = AccountAppConstants.CREATED_201),
            @ApiResponse(code = 401, message = AccountAppConstants.NOT_AUTHORIZED_401),
            @ApiResponse(code = 403, message = AccountAppConstants.FORBIDDEN_403)
    	}
    )
	@PostMapping	
	public ResponseEntity<AccountResponseWrapper<AccountDto>> save(@Valid @RequestBody AccountDto accountDto) {		
		return new ResponseEntity<AccountResponseWrapper<AccountDto>>(new AccountResponseWrapper<AccountDto>(AccountResponseStatus.SUCCESS,accountService.save(accountDto)),HttpStatus.CREATED);
	}

	// ------------------- Update a account-------------------------------------------
	/**
	 * The method updatets an existing record from the database table for the account entity.
	 * On successful updation the response is returned in jsend format.
	 * The response includes the status as string "SUCCESS / FAIL / ERROR / PARTIAL", data as array and the error object along with the error code and error message if any.    
	 * @param accountId and accountDto
	 * @return response Entity with a Response Wrapper
	 * @see ResponseEntity<ResponseWrapper<<AccountDto>>
	 */
	@ApiOperation(value = "Update an account")
	@ApiResponses(value = {
            @ApiResponse(code = 201, message = AccountAppConstants.SUCCESS_200),
            @ApiResponse(code = 401, message = AccountAppConstants.NOT_AUTHORIZED_401),
            @ApiResponse(code = 403, message = AccountAppConstants.FORBIDDEN_403)
    	}
    )
	@PutMapping(value = "/{accountId}")
	public ResponseEntity<AccountResponseWrapper<AccountDto>> update(@PathVariable("accountId") long accountId, @RequestBody AccountDto accountDto) {
		return new ResponseEntity<AccountResponseWrapper<AccountDto>>(new AccountResponseWrapper<AccountDto>(AccountResponseStatus.SUCCESS,accountService.update(accountDto)),HttpStatus.OK);
	}

	// -------------------Retrieve Single account ------------------------------------------
	/**
	 * The method retrives a single record from the database table for the account entity.
	 * On successful retrival the response is returned in jsend format.
	 * The response includes the status as string "SUCCESS / FAIL / ERROR / PARTIAL", data as array and the error object along with the error code and error message if any.    
	 * @param accountId
	 * @return response Entity with a Response Wrapper
	 * @see ResponseEntity<ResponseWrapper<<AccountDto>>
	 */
	@ApiOperation(value = "Search an account with an ID", response = AccountDto.class)
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = AccountAppConstants.SUCCESS_200),
            @ApiResponse(code = 404, message = AccountAppConstants.NOT_FOUND_404)
    	}
    )
	@GetMapping(value = "/{accountId}")
	public ResponseEntity<AccountResponseWrapper<AccountDto>> getById(@PathVariable("accountId") long accountId) {	
		return new ResponseEntity<AccountResponseWrapper<AccountDto>>(new AccountResponseWrapper<AccountDto>(AccountResponseStatus.SUCCESS,accountService.findById(accountId)),HttpStatus.OK);
	}

	// -------------------Retrieve All accounts ------------------------------------------
	/**
	 * The method retrives all the records from the database table for the account entity.
	 * On successful retrival the response is returned in jsend format.
	 * The response includes the status as string "SUCCESS / FAIL / ERROR / PARTIAL", data as array and the error object along with the error code and error message if any.    
	 * @return response Entity with a Response Wrapper
	 * @see ResponseEntity<ResponseWrapper<List<AccountDto>>>
	 */
	@ApiOperation(value = "View a list of available accounts",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = AccountAppConstants.SUCCESS_200),
            @ApiResponse(code = 401, message = AccountAppConstants.NOT_AUTHORIZED_401),
            @ApiResponse(code = 403, message = AccountAppConstants.FORBIDDEN_403),
            @ApiResponse(code = 404, message = AccountAppConstants.NOT_FOUND_404)
    	}
    )
	@GetMapping(value = "/")
	public ResponseEntity<AccountResponseWrapper<List<AccountDto>>> getAll() {
		return new ResponseEntity<AccountResponseWrapper<List<AccountDto>>>(new AccountResponseWrapper<List<AccountDto>>(AccountResponseStatus.SUCCESS,accountService.findAll()),HttpStatus.OK);
	}
	
	// -------------------Retrieve All Accounts by userId------------------------------------------
	/**
	 * The method retrives all the records from the database table for the account entity for a particular userId.
	 * On successful retrival the response is returned in jsend format.
	 * The response includes the status as string "SUCCESS / FAIL / ERROR / PARTIAL", data as array and the error object along with the error code and error message if any.    
	 * @return response Entity with a Response Wrapper
	 * @see ResponseEntity<ResponseWrapper<List<AccountDto>>>
	 */
	@ApiOperation(value = "Retrieve All Accounts by userId")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = AccountAppConstants.SUCCESS_200),
			@ApiResponse(code = 401, message = AccountAppConstants.NOT_AUTHORIZED_401),
			@ApiResponse(code = 403, message = AccountAppConstants.FORBIDDEN_403),
			@ApiResponse(code = 404, message = AccountAppConstants.NOT_FOUND_404) })
	@GetMapping(value = "/byUser/{userId}")
	public ResponseEntity<AccountResponseWrapper<List<AccountDto>>> getAllByUserId(@PathVariable("userId") long userId) {
		return new ResponseEntity<AccountResponseWrapper<List<AccountDto>>>(new AccountResponseWrapper<List<AccountDto>>(AccountResponseStatus.SUCCESS,accountService.findByUserId(userId)),HttpStatus.OK);
	}
		
	// ------------------- Delete a account -----------------------------------------
	/**
	 * The method deletes a single record from the database table for the account entity.
	 * On successful deletion no respone is returned.	 
	 * @param accountId
	 */
	@ApiOperation(value = "Delete an account")
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = AccountAppConstants.SUCCESS_200),
            @ApiResponse(code = 401, message = AccountAppConstants.NOT_AUTHORIZED_401),
            @ApiResponse(code = 403, message = AccountAppConstants.FORBIDDEN_403),
            @ApiResponse(code = 404, message = AccountAppConstants.NOT_FOUND_404)
    	}
    )
	@DeleteMapping(value = "/{accountId}")
	public void delete(@PathVariable("accountId") long accountId) {
		accountService.delete(accountId);
	}

	// ------------------- Delete All accounts -----------------------------
	/**
	 * The method deletes all the records from the database table for the account entity.
	 * On successful deletion no respone is returned.
	 * @return void
	 */
	@ApiOperation(value = "Delete all accounts")
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = AccountAppConstants.SUCCESS_200),
            @ApiResponse(code = 401, message = AccountAppConstants.NOT_AUTHORIZED_401),
            @ApiResponse(code = 403, message = AccountAppConstants.FORBIDDEN_403),
            @ApiResponse(code = 404, message = AccountAppConstants.NOT_FOUND_404)
    	}
    )
	@DeleteMapping(value = "/")
	public void deleteAll() {
		accountService.deleteAll();
	}
}