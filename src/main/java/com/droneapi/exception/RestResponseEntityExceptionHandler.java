package com.droneapi.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<?> handleException(Exception ex) {
		logger.error("Exception Error: ", ex.getMessage());
		return new ResponseEntity<Object>(ex.getMessage(),new HttpHeaders(),  HttpStatus.INTERNAL_SERVER_ERROR);

	}
	@ExceptionHandler(value = { RuntimeException.class })
	public ResponseEntity<?> handleInvalidInputException(RuntimeException ex) {
		logger.error("Exception Error: ", ex.getMessage());
		return new ResponseEntity<Object>(ex.getMessage(),new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

}
