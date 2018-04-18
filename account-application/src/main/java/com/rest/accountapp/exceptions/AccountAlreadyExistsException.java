package com.rest.accountapp.exceptions;


public class AccountAlreadyExistsException extends RuntimeException { 
	private static final long serialVersionUID = 1L;
 
    public AccountAlreadyExistsException(String message) {
        super(message);
    }
}