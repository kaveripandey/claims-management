package com.cognizant.policy.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cognizant.policy.dto.ValidatingDTO;

@FeignClient(name = "auth-client", url = "${Authorization.URL}")
public interface AuthClient {
	
	 @GetMapping(value = "/validate")
     public ValidatingDTO getsValidity(@RequestHeader(name = "Authorization", required = true) String token);

}