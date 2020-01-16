package com.wearsafe.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * @author nagendra
 * This is general custom exception class
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TRecordNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public TRecordNotFoundException(String message) {
		super(message);
	}
	
	public TRecordNotFoundException(String message, Throwable t) {
		super(message, t);
	}
}
