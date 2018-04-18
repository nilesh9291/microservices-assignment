package com.rest.aggregator.exception;

public class UsersNotFoundException extends RuntimeException {
 
	private static final long serialVersionUID = 1L;
	 
    public UsersNotFoundException(String message) {
        super(message);
    }
}