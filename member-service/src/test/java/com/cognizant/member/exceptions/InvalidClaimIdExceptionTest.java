package com.cognizant.member.exceptions;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.cognizant.member.exception.InvalidClaimIdException;

public class InvalidClaimIdExceptionTest {
	InvalidClaimIdException invalidClaimIdException = new InvalidClaimIdException("Exception");
	@Test
	 void testComponentTyepNotFoundExceptionTest() {
		
		assertThat(invalidClaimIdException).isNotNull();    
	}
	
}