package com.rest.aggregator.exceptions;

import com.rest.aggregator.enums.UserAccountsAggregatorResponseStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.rest.aggregator.wrapper.UserAccountsAggregatorResponseWrapper;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class UserAccountsAggregatorExceptionHandlingController {

	@ExceptionHandler(UserAccountsAggregatorBaseException.class)
	public ResponseEntity<UserAccountsAggregatorResponseWrapper> handleUserAccountsAggregatorExceptions(UserAccountsAggregatorBaseException ex) {
		List<ErrorDetails> errorList = new ArrayList<ErrorDetails>();
		errorList.add(new ErrorDetails(ex.getStatusCode().toString(),ex.getErrorMessage()));

		UserAccountsAggregatorResponseWrapper  aggregatorResponseWrapper = new UserAccountsAggregatorResponseWrapper();
		aggregatorResponseWrapper.setStatus(UserAccountsAggregatorResponseStatus.ERROR);
		aggregatorResponseWrapper.setErrorDetails(errorList);

		return new ResponseEntity<UserAccountsAggregatorResponseWrapper>(aggregatorResponseWrapper, ex.getStatusCode());
	}
}