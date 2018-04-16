package com.rest.exception;


public class ResourceExistsException extends RuntimeException { 
	private static final long serialVersionUID = 1L;
 
    public ResourceExistsException(String message) {
        super(message);
    }
}