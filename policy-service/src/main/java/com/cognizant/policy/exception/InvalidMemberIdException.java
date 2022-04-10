package com.cognizant.policy.exception;

public class InvalidMemberIdException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public InvalidMemberIdException(String msg) {
		super(msg);
	}

}
