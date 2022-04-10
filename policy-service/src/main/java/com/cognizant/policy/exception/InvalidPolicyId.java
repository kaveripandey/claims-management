package com.cognizant.policy.exception;

public class InvalidPolicyId extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public InvalidPolicyId(String msg) {
		super(msg);
	}

}
