package com.rest.accountapp.exceptions;

import org.springframework.http.HttpStatus;

public class AccountBaseException extends RuntimeException{
	private HttpStatus statusCode;
	private String errorMessage;

	public AccountBaseException(HttpStatus statusCode, String errorMessage) {
		super();
		this.statusCode = statusCode;
		this.errorMessage = errorMessage;
	}

	public HttpStatus getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}