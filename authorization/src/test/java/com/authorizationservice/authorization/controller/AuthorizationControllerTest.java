package com.authorizationservice.authorization.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import com.authorizationservice.authorization.dto.AuthenticationRequestDTO;
import com.authorizationservice.authorization.exceptions.LoginException;
import com.authorizationservice.authorization.repository.AuthorizationServiceRepository;
import com.authorizationservice.authorization.service.AuthorizationService;
import com.authorizationservice.authorization.util.JwtUtil;

import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
class AuthorizationControllerTest {

	@Mock
	private JwtUtil jwtUtil;

	@Mock
	private AuthorizationService appUserDetailsService;

	@Mock
	private AuthorizationServiceRepository authRequestRepo;

	@InjectMocks
	private AuthorizationController authenticationController;

	@Test
	void testValidLogin() throws LoginException {
		AuthenticationRequestDTO authenticationRequestDTO = new AuthenticationRequestDTO("kaveri", "kaveri01");
		UserDetails userDetails = new User(authenticationRequestDTO.getUsername(), authenticationRequestDTO.getPassword(),
				new ArrayList<>());

		when(appUserDetailsService.loadUserByUsername("kaveri")).thenReturn(userDetails);
		when(jwtUtil.generateToken(userDetails)).thenReturn("dummy-token");

		ResponseEntity<Object> authenticationResponse = authenticationController
				.createAuthorizationToken(authenticationRequestDTO);
		assertEquals(HttpStatus.OK, authenticationResponse.getStatusCode());
	}


	@Test
	void testInvalidLogin() {
		AuthenticationRequestDTO authenticationRequestDTO = new AuthenticationRequestDTO("kaveri", "0099kav");
		UserDetails userDetails = new User(authenticationRequestDTO.getUsername(), "kaveri", new ArrayList<>());
		
		when(appUserDetailsService.loadUserByUsername("kaveri")).thenReturn(userDetails);
		Exception exception = Assertions.assertThrows(LoginException.class,
				() -> authenticationController.createAuthorizationToken(authenticationRequestDTO));
		assertEquals("Invalid Username or Password", exception.getMessage());
	}

	@Test
	void testValidToken() {
		AuthenticationRequestDTO authenticationRequestDTO = new AuthenticationRequestDTO("kaveri", "kaveri01");
		UserDetails userDetails = new User(authenticationRequestDTO.getUsername(), authenticationRequestDTO.getPassword(),
				new ArrayList<>());

		when(jwtUtil.validateToken("token", userDetails)).thenReturn(true);
		when(jwtUtil.extractUsername("token")).thenReturn("kaveri");
		when(appUserDetailsService.loadUserByUsername("kaveri")).thenReturn(userDetails);

		ResponseEntity<?> validity = authenticationController.validatingAuthorizationToken("Bearer token");
		assertFalse(validity.getBody().toString().contains("true"));
	}

	@Test
	void testInvalidToken() {
		AuthenticationRequestDTO authenticationRequestDTO = new AuthenticationRequestDTO("kaveri", "kaveri01");
		UserDetails userDetails = new User(authenticationRequestDTO.getUsername(), authenticationRequestDTO.getPassword(),
				new ArrayList<>());

		when(jwtUtil.validateToken("token", userDetails)).thenReturn(false);
		when(jwtUtil.extractUsername("token")).thenReturn("kaveri");
		when(appUserDetailsService.loadUserByUsername("kaveri")).thenReturn(userDetails);

		ResponseEntity<?> validity = authenticationController.validatingAuthorizationToken("Bearer token");
		assertEquals(false, validity.getBody().toString().contains("false"));
	}

	@Test
	void testInvalidTokenWhenSignatureInvalid() {
		AuthenticationRequestDTO authenticationRequestDTO = new AuthenticationRequestDTO("kaveri", "kaveri01");
		UserDetails userDetails = new User(authenticationRequestDTO.getUsername(), authenticationRequestDTO.getPassword(),
				new ArrayList<>());

		when(jwtUtil.validateToken("token", userDetails)).thenThrow(SignatureException.class);
		when(jwtUtil.extractUsername("token")).thenReturn("kaveri");
		when(appUserDetailsService.loadUserByUsername("kaveri")).thenReturn(userDetails);

		ResponseEntity<?> validity = authenticationController.validatingAuthorizationToken("Bearer token");
		assertEquals(false, validity.getBody().toString().contains("false"));
	}
}
