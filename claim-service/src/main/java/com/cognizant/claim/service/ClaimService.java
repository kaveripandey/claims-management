package com.cognizant.claim.service;

import com.cognizant.claim.dto.ClaimDTO;
import com.cognizant.claim.dto.ClaimStatusDTO;
import com.cognizant.claim.exception.InvalidClaimIdException;

public interface ClaimService {
	
	public ClaimStatusDTO getClaimStatus(String id) throws InvalidClaimIdException;
	
	public ClaimStatusDTO submitClaim(ClaimDTO claimDTO, String token) throws NullPointerException;	

}
