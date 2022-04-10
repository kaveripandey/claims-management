package com.cognizant.portal.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cognizant.portal.dto.ClaimStatusDTO;
import com.cognizant.portal.model.Bills;
import com.cognizant.portal.model.ClaimDetails;

@FeignClient(name = "member-service", url = "localhost:8081/memberModule")
public interface MemberClient {
	
	@GetMapping(value="/viewBills/{memberId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Bills> getBills( @PathVariable("memberId") String memberId, @RequestHeader(name = "Authorization", required = true)String token);

    @GetMapping(value="/getClaimStatus/{claimId}", produces = "application/json")
    public ResponseEntity<ClaimStatusDTO> returnClaimStatus(@PathVariable("claimId") String claimId, @RequestHeader(name = "Authorization", required = true)String token);
        
    @PostMapping(value="/submitClaim", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClaimStatusDTO> returnClaimStatusOnUpdate(@RequestBody ClaimDetails claimDetails,  @RequestHeader(name = "Authorization", required = true)String token);

}
