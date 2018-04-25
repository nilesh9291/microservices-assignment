package com.rest.aggregator.exceptions;

import org.springframework.http.HttpStatus;

public class UserAccountsNotFoundException extends UserAccountsAggregatorBaseException {
 
	private static final long serialVersionUID = 1L;

    public UserAccountsNotFoundException() {

    }

    public UserAccountsNotFoundException(HttpStatus statusCode, String errorMessage) {
        super(statusCode,errorMessage);
    }
}