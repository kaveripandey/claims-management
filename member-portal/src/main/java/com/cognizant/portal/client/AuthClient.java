package com.cognizant.portal.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cognizant.portal.dto.AuthenticationResponseDTO;
import com.cognizant.portal.dto.ValidatingDTO;
import com.cognizant.portal.model.LoginModel;

@FeignClient(name = "auth-client", url = "localhost:8008/authorization")
public interface AuthClient {

	@PostMapping(value = "/login")
	public AuthenticationResponseDTO login(@RequestBody LoginModel userlogincredentials);

	@GetMapping(value = "/validate")
	public ValidatingDTO getsValidity(@RequestHeader(name = "Authorization", required = true) String token);

}
