package com.MatrimonyApp.CustomException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler{

	@ExceptionHandler(UserAlreadyExistException.class)
	public ResponseEntity<UserAlreadyExistException> resourceNotFoundException(UserAlreadyExistException exception) {
		String message = exception.getMessage();
		UserAlreadyExistException userAlreadyExist = new UserAlreadyExistException(message);
		return new ResponseEntity<UserAlreadyExistException>(userAlreadyExist, HttpStatus.CONFLICT);
	}
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<UserNotFoundException> resourceNotFoundException(UserNotFoundException exception) {
		String message = exception.getMessage();
		UserNotFoundException userNotFoundException = new UserNotFoundException(message);
		return new ResponseEntity<UserNotFoundException>(userNotFoundException, HttpStatus.CONFLICT);
	}

}
