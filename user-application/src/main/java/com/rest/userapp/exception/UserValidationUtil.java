package com.rest.userapp.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

public class UserValidationUtil {

	public static List<String> fromBindingErrors(Errors errors) {
		List<String> validErrors = new ArrayList<String>();
		
		for (ObjectError objectError : errors.getAllErrors()) {
			validErrors.add(objectError.getDefaultMessage());
		}
		
		return validErrors;
	}
}