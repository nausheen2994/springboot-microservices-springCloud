package com.app.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.app.model.ErrorMessage;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(value = {Exception.class})	
	public ResponseEntity<?> handleAnyException(Exception ex,WebRequest request){
		String localizedMessage = ex.getLocalizedMessage();
		if(localizedMessage==null) {
			localizedMessage=ex.toString();
		}
		//String error=ex.getLocalizedMessage()==null?ex.getLocalizedMessage().toString():ex.getLocalizedMessage();
		ErrorMessage errorMessage= new ErrorMessage(new Date(),localizedMessage);
		return new ResponseEntity<>(errorMessage,new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@ExceptionHandler(value = {NullPointerException.class,UserServiceException.class})	
	public ResponseEntity<?> handleSpecificException(Exception ex,WebRequest request){
		String localizedMessage = ex.getLocalizedMessage();
		if(localizedMessage==null) {
			localizedMessage=ex.toString();
		}
		//String error=ex.getLocalizedMessage()==null?ex.getLocalizedMessage().toString():ex.getLocalizedMessage();
		ErrorMessage errorMessage= new ErrorMessage(new Date(),localizedMessage);
		return new ResponseEntity<>(errorMessage,new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
		
	}


}
