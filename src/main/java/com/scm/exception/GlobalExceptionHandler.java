package com.scm.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
		
	//1) Created a class GlobalExceptionHandler which exteded ResponseEntityExceptionHandler
	
	//2) Create cstom exception by creating class and extend RuntimeException 
	//and annotate with @ResponseStatus(value = HttpStatus.NOT_FOUND)
	
	//3) Create exception handler mathod and annotate with @ExceptionHandler(ResourceNotFoundException.class)
	
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webrequest){
		ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), webrequest.getDescription(false));     
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ApplicationSqlException.class)
	public ResponseEntity<ErrorDetails> handleApplicationSqlException(ApplicationSqlException exception, WebRequest request){
		ErrorDetails error = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(error, HttpStatus.NOT_FOUND);
	}
	
	
	
}
