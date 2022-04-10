package com.cognizant.portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cognizant.portal.client.AuthClient;
import com.cognizant.portal.dto.AuthenticationResponseDTO;
import com.cognizant.portal.model.LoginModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
    private AuthClient authClient;


	@Override
	public AuthenticationResponseDTO login(LoginModel loginModel) {
		log.info("LoginServiceImpl [login Method]");
		return authClient.login(loginModel);
	}
	

	@Override
	public boolean getValidity(@RequestHeader("Authorization")String token) {
		log.info("LoginServiceImpl [getValidity Method]");
		boolean validityStatus = authClient.getsValidity(token).isValidStatus();
		log.info("Status is ");
		System.out.println(validityStatus);
		return validityStatus;
	}
}
