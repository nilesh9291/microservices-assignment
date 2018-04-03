package com.rest.exception;


public class ResourceExistsException extends RuntimeException { 
	private static final long serialVersionUID = 1L;
	private String name;
 
    public ResourceExistsException(String name, String message) {
        super(message);
        this.name = name;
    }
}