package com.rest.userapp.exceptions;

import com.rest.userapp.enums.UserResponseStatus;
import com.rest.userapp.utils.UserValidationUtil;
import com.rest.userapp.wrapper.UserResponseWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class UserExceptionHandlingController {

	@ExceptionHandler(UserBaseException.class)
	public ResponseEntity<UserResponseWrapper> handleUserExceptions(UserBaseException ex) {
		List<ErrorDetails> errorList = new ArrayList<ErrorDetails>();
		errorList.add(new ErrorDetails(ex.getStatusCode().toString(),ex.getErrorMessage()));

		UserResponseWrapper  userResponseWrapper = new UserResponseWrapper(UserResponseStatus.ERROR,errorList);

		return new ResponseEntity<UserResponseWrapper>(userResponseWrapper, ex.getStatusCode());
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<UserResponseWrapper> validateInput(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();

		List<ErrorDetails> errorDetailsList = null;
		errorDetailsList = UserValidationUtil.fromBindingErrors(result);

		UserResponseWrapper  userResponseWrapper = new UserResponseWrapper(UserResponseStatus.ERROR,errorDetailsList);

        return new ResponseEntity<UserResponseWrapper>(userResponseWrapper, HttpStatus.BAD_REQUEST);
    }
}