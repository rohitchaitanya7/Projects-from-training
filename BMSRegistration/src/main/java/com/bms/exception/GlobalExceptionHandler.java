package com.bms.exception;

import java.time.LocalDateTime;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bms.model.ResponseForException;

import feign.RetryableException;
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	   
	    return errors;
	}
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(InvalidTokenException.class)
	public ResponseEntity<ResponseForException> invalidTokenException(InvalidTokenException invalidTokenException) {
		
		return new ResponseEntity<>(
				new ResponseForException(invalidTokenException.getMessage(), LocalDateTime.now(), HttpStatus.UNAUTHORIZED),
				HttpStatus.UNAUTHORIZED);
	}
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(RetryableException.class)
	public ResponseEntity<ResponseForException> microServiceUnavailableException( ) {
		return new ResponseEntity<>(
				new ResponseForException("MicroServiceUnavailable", LocalDateTime.now(), HttpStatus.NOT_FOUND),
				HttpStatus.NOT_FOUND);
	}
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<Object> handleUnauthorizedExceptions(UnauthorizedException ex) {
		
		return new ResponseEntity<>(
				new ResponseForException("Un Authorized, Login Again ...", LocalDateTime.now(), HttpStatus.UNAUTHORIZED),
				HttpStatus.UNAUTHORIZED);
	}
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(InitialDepositException.class)
	public ResponseEntity<ResponseForException> initialDepositException(InitialDepositException ex) {
		
		return new ResponseEntity<>(
				new ResponseForException("In sufficient deposit amount", LocalDateTime.now(), HttpStatus.BAD_REQUEST),
				HttpStatus.BAD_REQUEST);
	}
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(UserExistsException.class)
	public ResponseEntity<Object> handleUserExistsExceptions(UserExistsException ex) {
		
		return new ResponseEntity<>(
				new ResponseForException("User Already Exsists", LocalDateTime.now(), HttpStatus.BAD_REQUEST),
				HttpStatus.BAD_REQUEST);
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> handleUserNotFoundExceptions(UserNotFoundException ex) {
		
		return new ResponseEntity<>(
				new ResponseForException("User Not Exsists", LocalDateTime.now(), HttpStatus.NOT_FOUND),
				HttpStatus.NOT_FOUND);
	}
	
}
