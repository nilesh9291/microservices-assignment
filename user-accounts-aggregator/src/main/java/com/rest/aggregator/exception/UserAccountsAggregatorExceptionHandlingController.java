package com.rest.aggregator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserAccountsAggregatorExceptionHandlingController {

	@ExceptionHandler(UserAccountsNotFoundException.class)
	public ResponseEntity<UserAccountsExceptionResponse> resourceNotFound(UserAccountsNotFoundException ex) {
		UserAccountsExceptionResponse response = new UserAccountsExceptionResponse();
		response.setErrorCode("404");
		response.setErrorMessage(ex.getMessage());

		return new ResponseEntity<UserAccountsExceptionResponse>(response, HttpStatus.NOT_FOUND);
	}	
}