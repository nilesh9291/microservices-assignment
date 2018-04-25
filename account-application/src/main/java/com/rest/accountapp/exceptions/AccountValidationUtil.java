package com.rest.accountapp.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

public class AccountValidationUtil {

	public static List<ErrorDetails> fromBindingErrors(Errors errors) {
		List<ErrorDetails> errorDetailsList = new ArrayList<ErrorDetails>();

		for (ObjectError objectError : errors.getAllErrors()) {
			errorDetailsList.add(new ErrorDetails(objectError.getCode(),objectError.getDefaultMessage()));
		}

		return errorDetailsList;
	}
}