package com.cognizant.policy.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.policy.dto.BenefitsDTO;
import com.cognizant.policy.dto.ClaimAmountDTO;
import com.cognizant.policy.dto.ProviderDTO;
import com.cognizant.policy.entity.MemberPolicy;
import com.cognizant.policy.entity.Policy;
import com.cognizant.policy.exception.ExpiredPolicyException;
import com.cognizant.policy.exception.InvalidMemberIdException;
import com.cognizant.policy.exception.InvalidPolicyId;
import com.cognizant.policy.repository.MemberPolicyRepo;
import com.cognizant.policy.repository.PolicyRepo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PolicyServiceImpl implements PolicyService {
	@Autowired
	private MemberPolicyRepo memberPolicyRepo;
	
	@Autowired
	private PolicyRepo policyRepo;

	public BenefitsDTO getBenefits(String policyId, String memberId) {

		log.info("Inside get benefits method in benefits service");

		Optional<Policy> policy = policyRepo.findById(policyId);

		if (!policy.isPresent()) {
			throw new InvalidPolicyId("Invalid Policy Id");
		}

		if (!isValidMember(memberId)) {
			throw new InvalidMemberIdException("Invalid Member Id");
		}

		if (!isPremiumPaid(memberId)) {
			throw new ExpiredPolicyException("Premium not paid");
		}

		log.info("Exiting get benefits method in benefits service");

		return new BenefitsDTO(policy.get().getBenefits());

	}

	public ClaimAmountDTO getClaimAmount(String policyId, String memberId) throws InvalidPolicyId {

		log.info("Inside get claim amount method in claim amount service...");

		Optional<Policy> policy = policyRepo.findById(policyId);

		if (!policy.isPresent()) {
			throw new InvalidPolicyId("Invalid Policy Id...");
		}

		if (!isValidMember(memberId)) {
			throw new InvalidMemberIdException("Invalid Member Id...");
		}

		if (!isPremiumPaid(memberId)) {
			throw new ExpiredPolicyException("Premium is not paid...");
		}

		log.info("Exiting get claim amount method in claim amount service...");

		return new ClaimAmountDTO(policy.get().getSumInsured());
	}

	public boolean isValidMember(String memberId) {

		Optional<MemberPolicy> member = memberPolicyRepo.findById(memberId);

		return member.isPresent();
	}

	public boolean isPremiumPaid(String memberId) {
		DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		Optional<MemberPolicy> member = memberPolicyRepo.findById(memberId);

		LocalDate today = LocalDate.now();

		if (member.isPresent()) {
			LocalDate lastDate = LocalDate.parse(member.get().getPremiumLastDate(), df);

			if (lastDate.plusYears(1).compareTo(today) >= 0)
				return true;
		}

		return false;
	}

	public ProviderDTO getProviders(String policyId) throws InvalidPolicyId {

		log.info("Inside get providers method in provider service...");

		Optional<Policy> policies = policyRepo.findById(policyId);

		if (!policies.isPresent())
			throw new InvalidPolicyId("Invalid Policy Id");

		log.info("Exiting get providers method in provider service...");

		return new ProviderDTO(policies.get().getHospitals());
	}

}
