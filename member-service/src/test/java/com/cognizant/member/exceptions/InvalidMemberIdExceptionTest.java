package com.cognizant.member.exceptions;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.cognizant.member.exception.InvalidMemberIdException;

public class InvalidMemberIdExceptionTest {
	InvalidMemberIdException invalidMemberIdException = new InvalidMemberIdException("Exception");
	@Test
	 void testComponentTyepNotFoundExceptionTest() {
		
		assertThat(invalidMemberIdException).isNotNull();    
	}
	
}