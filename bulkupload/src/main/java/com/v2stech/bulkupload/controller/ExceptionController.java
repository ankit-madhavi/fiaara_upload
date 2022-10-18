package com.v2stech.bulkupload.controller;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.v2stech.bulkupload.bean.ExceptionDetails;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> exceptionHandler(Exception ex) {
		ExceptionDetails details = new ExceptionDetails(ex.getMessage());
		return new ResponseEntity<>(details, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(IOException.class)
	public ResponseEntity<?> exceptionHandler(IOException ex) {
		ExceptionDetails details = new ExceptionDetails(ex.getMessage());
		return new ResponseEntity<>(details, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
