package com.exercise.ya.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class CallRestException extends RuntimeException {
	
	public CallRestException(String message) {
		super(message);
	}

}
