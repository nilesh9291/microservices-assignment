package com.rest.aggregator.exceptions;

import org.springframework.http.HttpStatus;

public class UsersNotFoundException extends UserAccountsAggregatorBaseException {
 
	private static final long serialVersionUID = 1L;

    public UsersNotFoundException() {

    }

    public UsersNotFoundException(HttpStatus statusCode, String errorMessage) {
        super(statusCode,errorMessage);
    }
}