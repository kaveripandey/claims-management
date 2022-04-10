package com.cognizant.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.member.client.AuthClient;
import com.cognizant.member.dto.ClaimStatusDTO;
import com.cognizant.member.entity.Bills;
import com.cognizant.member.exception.InvalidTokenException;
import com.cognizant.member.model.ClaimDetails;
import com.cognizant.member.service.MemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/memberModule")
@Api(value = "Member module endpoint")
@CrossOrigin
public class MemberController {

	@Autowired
	private MemberService memberService;

	@Autowired
	private AuthClient authClient;

	private String msg = "Token is either expired or invalid...";

	@ApiOperation(value = "to get the Bills for the User")
	@GetMapping(value = "/viewBills/{memberId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Bills> getBills(@PathVariable("memberId") String memberId,
			@RequestHeader(name = "Authorization", required = true) String token) throws InvalidTokenException {
		log.info("Inside the getBills EndPoint : Begin");

		if (!authClient.getsValidity(token).isValidStatus()) {

			throw new InvalidTokenException(msg);
		}

		log.info("Inside the getBills EndPoint : Ended");
		return new ResponseEntity<>(memberService.fetchBills(memberId), HttpStatus.OK);
	}

	@ApiOperation(value = "To get the claim Status For Given Id")
	@GetMapping(value = "/getClaimStatus/{claimId}", produces = "application/json")
	public ResponseEntity<ClaimStatusDTO> returnClaimStatus(@PathVariable("claimId") String claimId,
			@RequestHeader(name = "Authorization", required = true) String token) throws InvalidTokenException {

		log.info("Inside the getClaimStatus EndPoint : Begin");

		if (!authClient.getsValidity(token).isValidStatus()) {

			throw new InvalidTokenException(msg);
		}

		log.info("Inside the getClaimStatus EndPoint : Ended");
		return new ResponseEntity<>(memberService.fetchClaimStatus(claimId, token), HttpStatus.OK);
	}

	@PostMapping(value = "/submitClaim", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "To Submit the Claim", response = ClaimStatusDTO.class, httpMethod = "POST")
	public ResponseEntity<ClaimStatusDTO> returnClaimStatusOnUpdate(@RequestBody ClaimDetails claimDetails,
			@RequestHeader(name = "Authorization", required = true) String token) throws InvalidTokenException {
		log.info("Inside the submitClaim EndPoint : Begin");

		if (!authClient.getsValidity(token).isValidStatus()) {

			throw new InvalidTokenException(msg);
		}

		log.info("Inside the submitClaim EndPoint : Ended");
		return new ResponseEntity<>(memberService.fetchClaimDetails(claimDetails, token), HttpStatus.OK);

	}

}