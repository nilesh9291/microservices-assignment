package com.rest.userapp.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

public class UserValidationUtil {

	public static List<ErrorDetails> fromBindingErrors(Errors errors) {
		List<ErrorDetails> errorDetailsList = new ArrayList<ErrorDetails>();

		for (ObjectError objectError : errors.getAllErrors()) {
			errorDetailsList.add(new ErrorDetails(objectError.getCode(),objectError.getDefaultMessage()));
		}
		
		return errorDetailsList;
	}
}