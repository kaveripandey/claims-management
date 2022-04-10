package com.cognizant.policy.service;

import com.cognizant.policy.dto.BenefitsDTO;
import com.cognizant.policy.dto.ClaimAmountDTO;
import com.cognizant.policy.dto.ProviderDTO;
import com.cognizant.policy.exception.InvalidPolicyId;

public interface PolicyService {
	
	public BenefitsDTO getBenefits(String policyId, String memberId);
	
	public ClaimAmountDTO getClaimAmount(String policyId, String memberId);
	
	public ProviderDTO getProviders(String policyId) throws InvalidPolicyId;
}
