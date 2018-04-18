package com.rest.aggregator.exception;

public class UserAccountsNotFoundException extends RuntimeException {
 
	private static final long serialVersionUID = 1L;
	 
    public UserAccountsNotFoundException(String message) {
        super(message);
    }
}