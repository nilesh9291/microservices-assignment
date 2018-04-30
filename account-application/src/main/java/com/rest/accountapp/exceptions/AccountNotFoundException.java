package com.rest.accountapp.exceptions;

import org.springframework.http.HttpStatus;

public class AccountNotFoundException extends AccountBaseException {
 
	private static final long serialVersionUID = 1L;

    public AccountNotFoundException(HttpStatus statusCode, String errorMessage) {
        super(statusCode,errorMessage);
    }
}