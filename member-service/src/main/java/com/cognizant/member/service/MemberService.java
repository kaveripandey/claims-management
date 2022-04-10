package com.cognizant.member.service;

import com.cognizant.member.dto.ClaimStatusDTO;
import com.cognizant.member.entity.Bills;
import com.cognizant.member.model.ClaimDetails;

public interface MemberService {
	
	public  ClaimStatusDTO fetchClaimStatus(String claimId, String token);
	
	public  Bills fetchBills(String memberId);
	
	public  ClaimStatusDTO fetchClaimDetails( ClaimDetails claimDetails,String token);

}
