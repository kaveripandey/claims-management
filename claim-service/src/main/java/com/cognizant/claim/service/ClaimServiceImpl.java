package com.cognizant.claim.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.claim.client.PolicyClient;
import com.cognizant.claim.dto.BenefitsDTO;
import com.cognizant.claim.dto.ClaimAmountDTO;
import com.cognizant.claim.dto.ClaimDTO;
import com.cognizant.claim.dto.ClaimStatusDTO;
import com.cognizant.claim.dto.ProviderDTO;
import com.cognizant.claim.entity.Claim;
import com.cognizant.claim.exception.InvalidClaimIdException;
import com.cognizant.claim.exception.PolicyException;
import com.cognizant.claim.model.Benefits;
import com.cognizant.claim.model.Hospital;
import com.cognizant.claim.repository.ClaimServiceRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ClaimServiceImpl implements ClaimService {

	@Autowired
	ClaimServiceRepository claimRepo;
	
	@Autowired
	private PolicyClient policyClient;
	
	public ClaimStatusDTO getClaimStatus(String id) throws InvalidClaimIdException {
			log.info("inside getClaimStatus in service ");
			ClaimStatusDTO claimStatusDTO = new ClaimStatusDTO();
			String status = claimRepo.getStatus(id);
			String desc = claimRepo.getDescription(id);
			if (status == null) {
				throw new InvalidClaimIdException("Invalid Claim ID");
			}
			claimStatusDTO.setClaimStatus(status);
			claimStatusDTO.setClaimDescription(desc);
			claimStatusDTO.setClaimId(id);
			log.info("inside the get Claim Status : ENDED");
			return claimStatusDTO;
	}
	
	private boolean checkProvider(ClaimDTO claimDTO, String token) {
		ProviderDTO providerDTO = policyClient.getChainOfProviders(claimDTO.getPolicyId(), token).getBody();
			log.info("came here");
			
			if (providerDTO !=null && providerDTO.getProviders() != null) 
			for (Hospital hospitalDetails : providerDTO.getProviders()) 
				if (claimDTO.getHospitalId().equalsIgnoreCase(hospitalDetails.getHospitalId())) 
					return true;
		return false;
	}

	private boolean checkBenefit(ClaimDTO claimDTO, String token) {
		BenefitsDTO benefitsDTO = policyClient
				.getEligibleBenefits(claimDTO.getPolicyId(), claimDTO.getMemberId(), token).getBody();
		if (benefitsDTO != null &&  benefitsDTO.getBenefits() !=null) 
		for (Benefits benefits : benefitsDTO.getBenefits()) 
			if (claimDTO.getBenefitId().equalsIgnoreCase(benefits.getBenefitId())) 
				return true;
		return false;
	}

	private boolean checkAmount(ClaimDTO claimDTO, String token) {
		ClaimAmountDTO claimAmountDTO = policyClient
				.getEligibleAmount(claimDTO.getPolicyId(), claimDTO.getMemberId(), token).getBody();
		if (claimAmountDTO != null) {
		return (claimDTO.getClaimAmount() <= claimAmountDTO.getEligibleAmount());
		}
		return false;
	}

	public ClaimStatusDTO submitClaim(ClaimDTO claimDTO, String token) throws NullPointerException {
		boolean hospitalFlag = false;
		boolean benefitFlag = false;
		boolean amountFlag = false;
		try {
		log.info("inside the submit claim service method : BEGIN");
		log.info("checking the provider : BEGIN");
		hospitalFlag = checkProvider(claimDTO, token);
		log.info("checking of provider : ENDED");

		log.info("checking the Benefit : BEGIN");
		benefitFlag = checkBenefit(claimDTO, token);
		log.info("checking of Benefit : ENDED");

		log.info("checking the Amount : BEGIN");
		amountFlag = checkAmount(claimDTO, token);
		log.info("checking of provider : ENDED");
		}catch(PolicyException pe) {
			throw new PolicyException("Policy Serivce is Not working Properly");
		}
		log.info("creating claim and setting the status : BEGIN");
		Claim claim = new Claim();
		claim.setClaimId(claimDTO.getClaimId());
		claim.setBenefitId(claimDTO.getBenefitId());
		claim.setClaimAmount(claimDTO.getClaimAmount());
		claim.setHospitalId(claimDTO.getHospitalId());
		claim.setMemberId(claimDTO.getMemberId());
		claim.setPolicyId(claimDTO.getPolicyId());
		claim.setRemarks(claim.getRemarks());
		if (hospitalFlag && benefitFlag && amountFlag) {
			claim.setStatus("Pending Action");
			claim.setDescription("All the fields are successfully verified! Please wait for furthur action");
		} else {
			claim.setStatus("Claim Rejected");
			if (!hospitalFlag) {
				claim.setDescription("Insufficient Claim Details");
			} else if (!benefitFlag) {
				claim.setDescription("Insufficient Claim Details");
			} else {
				claim.setDescription("Claim amount is not valid");
			}
		}
		log.info("setting the status : ENDED");
		if (hospitalFlag && benefitFlag && amountFlag) {
			claimRepo.save(claim);
		}

		ClaimStatusDTO claimStatusDTO = new ClaimStatusDTO();
		claimStatusDTO.setClaimStatus(claim.getStatus());
		claimStatusDTO.setClaimDescription(claim.getDescription());
		claimStatusDTO.setClaimId(claim.getClaimId());
		return claimStatusDTO;
	}

}