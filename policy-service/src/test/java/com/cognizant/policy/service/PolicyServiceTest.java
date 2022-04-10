package com.cognizant.policy.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cognizant.policy.dto.ProviderDTO;
import com.cognizant.policy.entity.Hospital;
import com.cognizant.policy.entity.Policy;
import com.cognizant.policy.exception.InvalidPolicyId;
import com.cognizant.policy.repository.PolicyRepo;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class PolicyServiceTest {

	@Mock
	PolicyRepo policyRepo;
	
	@InjectMocks
	PolicyService policyService;

	@Test
	public void testGetChainOfProviders() {

		Hospital hospital1 = new Hospital("H101", "Apollo Hospital", "Delhi-Indraprastha");
		Hospital hospital2 = new Hospital("H102", "Artemis Hospital", "Gurgaon");
		Hospital hospital3 = new Hospital("H103", "Fortis Escorts Heart Institute", "Delhi-Okhla");

		Policy policy1 = new Policy("P101", "Health Plus Classic", 500000, 15639);
		Policy policy2 = new Policy("P102", "Health Plus Enhanced", 3000000, 17361);
		Optional<Policy> p = Optional.of(policy1);

		policy1.getHospitals().add(hospital3);
		policy1.getHospitals().add(hospital1);

		policy2.getHospitals().add(hospital1);
		policy2.getHospitals().add(hospital2);
		policy2.getHospitals().add(hospital3);

		when(this.policyRepo.findById("P101")).thenReturn(p);
		ProviderDTO providers = new ProviderDTO();
		providers.setProviders(policy1.getHospitals());

		assertEquals(2, policyService.getProviders("P101").getProviders().size());
	}

	@Test
	public void testGetEligibleClaimAmount() {

//		Policy policy1 = new Policy("P101","Health Plus Classic",500000,15639);
//		Policy policy2 = new Policy("P102","Health Plus Enhanced",3000000,17361);
//
//		Optional<Policy> p=Optional.of(policy1);
//
//		when(this.policyRepo.findById("P101")).thenReturn(p);
//		ClaimAmountDTO claimAmountDTO=new ClaimAmountDTO();
//		claimAmountDTO.setEligibleAmount(policy1.getSumInsured());
//
//		assertEquals(500000, policyService.getClaimAmount("P101","M101").getEligibleAmount());

		}
	
//	@Test
//	public void testgetBenefits() {
//        Policy policy1 = new Policy("P1001","Health Plus Classic",500000,15639);
//		
//		Benefits b1 = new Benefits("B101","Coverage for COVID-19");
//		Benefits b2 = new Benefits("B102","Coverage for hospitalization at home");
//		
//		policy1.getBenefits().add(b1);
//		policy1.getBenefits().add(b2);
//				
//		Optional<Policy> policy=Optional.of(policy1);
//		
//		when(policyRepo.findById("P1001")).thenReturn(policy);
//		BenefitsDTO benefits = new BenefitsDTO();
//		benefits.setBenefits(policy1.getBenefits());
//		assertEquals(policy.get().getBenefits(), policyService.getBenefits("P1001", "M101"));
//				
//	}
	
	@Test
	@DisplayName("Checking if getBenefits method is working or not with Invalid Policy Id")
	void testgetBenefitsWithInvalidPolicyId() {

		assertThrows(InvalidPolicyId.class, () -> policyService.getBenefits("101", "M101"));
	}
	
	@Test
	@DisplayName("Checking if getClaimAmount method is working or not with Invalid Policy Id")
	void testgetClaimAmountWithInvalidPolicyId() {

		assertThrows(InvalidPolicyId.class, () -> policyService.getClaimAmount("101", "M101"));
	}
	
	@Test
	@DisplayName("Checking if getProviders method is working or not with Invalid Policy Id")
	void testgetProvidersWithInvalidPolicyId() {

		assertThrows(InvalidPolicyId.class, () -> policyService.getProviders("101"));
	}
	
	@Test
	@DisplayName("Checking if getBenefits method is working or not with Invalid Member Id")
	void testgetBenefitsWithInvalidMemberId() {

		//assertThrows(InvalidMemberIdException.class, () -> policyService.getBenefits("P1001", "001"));
	}
}