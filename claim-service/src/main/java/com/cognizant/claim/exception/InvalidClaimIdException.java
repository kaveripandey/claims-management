package com.cognizant.claim.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class InvalidClaimIdException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public InvalidClaimIdException(String message) {
		super(message);
	}
	
	
}