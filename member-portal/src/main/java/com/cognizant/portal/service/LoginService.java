package com.cognizant.portal.service;


import org.springframework.stereotype.Service;

import com.cognizant.portal.dto.AuthenticationResponseDTO;
import com.cognizant.portal.model.LoginModel;

@Service
public interface LoginService {
	
	boolean getValidity(String token);

        AuthenticationResponseDTO login(LoginModel model);
   
}
  