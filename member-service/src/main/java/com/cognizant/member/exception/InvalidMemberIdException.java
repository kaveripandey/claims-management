package com.cognizant.member.exception;

public class InvalidMemberIdException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidMemberIdException(String message)
	{
		super(message);
	}
}