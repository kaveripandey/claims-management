package com.cognizant.claim.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.claim.client.AuthClient;
import com.cognizant.claim.dto.ClaimDTO;
import com.cognizant.claim.dto.ClaimStatusDTO;
import com.cognizant.claim.exception.InvalidClaimIdException;
import com.cognizant.claim.exception.InvalidTokenException;
import com.cognizant.claim.service.ClaimService;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/claimModule")
@Api(value = "For dealing with claim ")
public class ClaimsController {

	@Autowired
	private ClaimService claimService;
	
	@Autowired
	private AuthClient authClient;
	
	@GetMapping(path="/getClaimStatus/{id}")
	public ResponseEntity<ClaimStatusDTO> getClaimStatus(@PathVariable("id") String id,@RequestHeader(name = "Authorization", required = true) String token)throws InvalidClaimIdException,InvalidTokenException {
		log.info(token);
		if (!authClient.getsValidity(token).isValidStatus()) {
			throw new InvalidTokenException("Token is either expired or invalid");
		}
		
		
		log.info("inside the get Claim Status : BEGIN");
		return new ResponseEntity<>(claimService.getClaimStatus(id), HttpStatus.OK); 
	}
	
	@PostMapping(path ="/submitClaim")
	public ResponseEntity<ClaimStatusDTO> submitClaim(@RequestBody ClaimDTO claimDTO,@RequestHeader(name = "Authorization", required = true) String token)throws InvalidTokenException,NullPointerException {
		log.info(token);
		if (!authClient.getsValidity(token).isValidStatus()) {
			throw new InvalidTokenException("Token is either expired or invalid");
		}
		
		log.info("inside the submit Claim : BEGIN");
		return new ResponseEntity<>(claimService.submitClaim(claimDTO,token), HttpStatus.OK);
	}
	
}