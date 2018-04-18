package com.rest.userapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandlingController {

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<UserExceptionResponse> resourceNotFound(UserNotFoundException ex) {
		UserExceptionResponse response = new UserExceptionResponse();
		response.setErrorCode("404");
		response.setErrorMessage(ex.getMessage());

		return new ResponseEntity<UserExceptionResponse>(response, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<UserExceptionResponse> resourceAlreadyExists(UserAlreadyExistsException ex) {
		UserExceptionResponse response = new UserExceptionResponse();
		response.setErrorCode("409");
		response.setErrorMessage(ex.getMessage());

		return new ResponseEntity<UserExceptionResponse>(response, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<UserExceptionResponse> invalidInput(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        UserExceptionResponse response = new UserExceptionResponse();
        response.setErrorCode("400");
        response.setErrorMessage("Bad Request. Invalid input parameters.");
        response.setErrors(UserValidationUtil.fromBindingErrors(result));
        return new ResponseEntity<UserExceptionResponse>(response, HttpStatus.BAD_REQUEST);
    }
}