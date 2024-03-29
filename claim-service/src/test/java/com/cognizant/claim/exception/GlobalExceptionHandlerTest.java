package com.cognizant.claim.exception;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


@SpringBootTest
class GlobalExceptionHandlerTest {

	@Autowired
	GlobalExceptionHandler globalExceptionHandler;
	@MockBean
	GlobalExceptionHandler gloabalExceptionHandlerMockBean;
	
	@Test
	@DisplayName("Checking if GlobalExceptionHandler class is loading or not")
	void globalExceptionHandlerIsLoadingOrNot() {
		
		assertThat(globalExceptionHandler).isNotNull();
	}
	
	
	
}
