package com.rest.accountapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AccountExceptionHandlingController {

	@ExceptionHandler(AccountNotFoundException.class)
	public ResponseEntity<AccountExceptionResponse> resourceNotFound(AccountNotFoundException ex) {
		AccountExceptionResponse response = new AccountExceptionResponse();
		response.setErrorCode("404");
		response.setErrorMessage(ex.getMessage());

		return new ResponseEntity<AccountExceptionResponse>(response, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(AccountAlreadyExistsException.class)
	public ResponseEntity<AccountExceptionResponse> resourceAlreadyExists(AccountAlreadyExistsException ex) {
		AccountExceptionResponse response = new AccountExceptionResponse();
		response.setErrorCode("409");
		response.setErrorMessage(ex.getMessage());

		return new ResponseEntity<AccountExceptionResponse>(response, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<AccountExceptionResponse> invalidInput(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        AccountExceptionResponse response = new AccountExceptionResponse();
        response.setErrorCode("400");
        response.setErrorMessage("Bad Request. Invalid input parameters.");
        response.setErrors(AccountValidationUtil.fromBindingErrors(result));
        return new ResponseEntity<AccountExceptionResponse>(response, HttpStatus.BAD_REQUEST);
    }
}