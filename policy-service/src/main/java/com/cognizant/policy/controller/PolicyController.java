package com.cognizant.policy.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.policy.client.AuthClient;
import com.cognizant.policy.dto.BenefitsDTO;
import com.cognizant.policy.dto.ClaimAmountDTO;
import com.cognizant.policy.dto.ProviderDTO;
import com.cognizant.policy.exception.InvalidTokenException;
import com.cognizant.policy.service.PolicyService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class PolicyController {
	
	@Autowired
	private PolicyService policyService;
	
	 @Autowired
	private AuthClient authClient;
	 
	private String msg = "Token is either expired or invalid...";
	
	@GetMapping(path="/getChainOfProviders/{policyId}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProviderDTO> getChainOfProviders(@PathVariable String policyId, @RequestHeader(name = "Authorization", required = true)String token) throws InvalidTokenException{
		
		log.info("Inside get chain of providers...");
		
			if (!authClient.getsValidity(token).isValidStatus()) {
				
				throw new InvalidTokenException(msg);
			}
		
		log.info("Exiting get chain of providers...");
		
		return new ResponseEntity<>(policyService.getProviders(policyId), HttpStatus.OK);
		
	}
	
		
	@GetMapping(path="/getEligibleBenefits/{policyId}/{memberId}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BenefitsDTO> getEligibleBenefits(@PathVariable String policyId, @PathVariable String memberId, @RequestHeader(name = "Authorization", required = true)String token) throws InvalidTokenException{
			
		log.info("Inside get eligible benefits");
		
			if (!authClient.getsValidity(token).isValidStatus()) {
				
				throw new InvalidTokenException(msg);
			}
			
		log.info("Exiting get eligible benefits");
    	return new ResponseEntity<>(policyService.getBenefits(policyId,memberId), HttpStatus.OK);
	}
	
	
	
	@GetMapping(path="/getEligibleClaimAmount/{policyId}/{memberId}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ClaimAmountDTO> getEligibleAmount(@PathVariable String policyId, @PathVariable String memberId, @RequestHeader(name = "Authorization", required = true)String token) throws InvalidTokenException{
		
		log.info("Inside get eligible benefits");
		
			if (!authClient.getsValidity(token).isValidStatus()) {										
				throw new InvalidTokenException(msg);
		}	   
		
		log.info("Exiting get elibile amount");
		
		return new ResponseEntity<>(policyService.getClaimAmount(policyId,memberId), HttpStatus.OK);
	}
}