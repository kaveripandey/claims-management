package com.cognizant.member.exceptions;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import com.cognizant.member.exception.ControllerExceptionHandler;


public class ControllerExceptionHandlerTest {
	
	@InjectMocks
	ControllerExceptionHandler controllerHandler;
	
	
	@Test
	@DisplayName("Checking if Controller Advice is Loading or not")
	 void testControllerExceptionHandlerTest() {
		
		ControllerExceptionHandler controllerExceptionHandler = new ControllerExceptionHandler();
		assertThat(controllerExceptionHandler).isNotNull();    
	}
	
	
	
}