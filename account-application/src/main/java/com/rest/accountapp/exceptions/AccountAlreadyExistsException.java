package com.rest.accountapp.exceptions;


import org.springframework.http.HttpStatus;

public class AccountAlreadyExistsException extends AccountBaseException {
	private static final long serialVersionUID = 1L;

    public AccountAlreadyExistsException(HttpStatus statusCode, String errorMessage) {
        super(statusCode,errorMessage);
    }
}