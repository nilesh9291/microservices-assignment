package com.rest.accountapp.exceptions;

import com.rest.accountapp.enums.AccountResponseStatus;
import com.rest.accountapp.wrapper.AccountResponseWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.rest.accountapp.exceptions.AccountBaseException;


import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class AccountExceptionHandlingController {

	@ExceptionHandler(AccountBaseException.class)
	public ResponseEntity<AccountResponseWrapper> handleAccountExceptions(AccountBaseException ex) {
		List<ErrorDetails> errorList = new ArrayList<ErrorDetails>();
		errorList.add(new ErrorDetails(ex.getStatusCode().toString(),ex.getErrorMessage()));

		AccountResponseWrapper  accountResponseWrapper = new AccountResponseWrapper();
		accountResponseWrapper.setStatus(AccountResponseStatus.ERROR);
		accountResponseWrapper.setErrorDetails(errorList);

		return new ResponseEntity<AccountResponseWrapper>(accountResponseWrapper, ex.getStatusCode());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<AccountResponseWrapper> validateInput(MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult();

		AccountResponseWrapper  accountResponseWrapper = new AccountResponseWrapper();
		accountResponseWrapper.setStatus(AccountResponseStatus.ERROR);
		accountResponseWrapper.setErrorDetails(AccountValidationUtil.fromBindingErrors(result));

		return new ResponseEntity<AccountResponseWrapper>(accountResponseWrapper, HttpStatus.BAD_REQUEST);
	}
}