package com.cognizant.policy.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cognizant.policy.client.AuthClient;
import com.cognizant.policy.dto.BenefitsDTO;
import com.cognizant.policy.dto.ClaimAmountDTO;
import com.cognizant.policy.dto.ProviderDTO;
import com.cognizant.policy.dto.ValidatingDTO;
import com.cognizant.policy.entity.Benefits;
import com.cognizant.policy.entity.Hospital;
import com.cognizant.policy.entity.Policy;
import com.cognizant.policy.exception.InvalidTokenException;
import com.cognizant.policy.service.PolicyServiceImpl;


@SpringBootTest
class PolicyControllerTest {
	
	@InjectMocks
	PolicyController policyController;
	
	@Mock
	AuthClient authClient;
	
	@Mock
	PolicyServiceImpl policyServiceImpl;
	
	@Test
    @DisplayName("Checking for Policy Controller - if it is loading or not for @GetMapping")
    void policyControllerNotNullTest(){
		PolicyController policyController = new PolicyController();
        assertThat(policyController).isNotNull();
    }
	
	@Test
	@DisplayName("Checking getChainOfProviders is working or not")
	public void testgetChainOfProviders() {
	Hospital hospital1 = new Hospital("H101", "Apollo Hospital", "Delhi-Indraprastha");
	Hospital hospital2 = new Hospital("H102", "Artemis Hospital", "Gurgaon");
	Hospital hospital3 = new Hospital("H103", "Fortis Escorts Heart Institute", "Delhi-Okhla");

	Policy policy1 = new Policy("P101","Health Plus Classic",500000,15639);
	Policy policy2 = new Policy("P102","Health Plus Enhanced",3000000,17361);

	policy1.getHospitals().add(hospital3);
	policy1.getHospitals().add(hospital1);

	policy2.getHospitals().add(hospital1);
	policy2.getHospitals().add(hospital2);
	policy2.getHospitals().add(hospital3);

	String token = "AAA";
	ValidatingDTO validatingDTO = new ValidatingDTO(true);
	when(authClient.getsValidity(token)).thenReturn(validatingDTO);

	ProviderDTO providers=new ProviderDTO();
	providers.setProviders(policy1.getHospitals());

	when(this.policyServiceImpl.getProviders("P101")).thenReturn(providers);
	ResponseEntity<ProviderDTO> providers1=policyController.getChainOfProviders("P101", token);
	assertEquals(HttpStatus.OK,providers1.getStatusCode());

	}
	
	@Test
   	@DisplayName("Testing getChainOfProviders invalid Token Id Exception")
   	public void getChainOfProviders_fails() {
       	
    	when(authClient.getsValidity(anyString())).thenReturn(new ValidatingDTO(false));
    	Assertions.assertThrows(InvalidTokenException.class, ()->{
    		policyController.getChainOfProviders("100", "token");
    	});
   	}
	
	@Test
	@DisplayName("Checking getEligibleBenefits is working or not")
	void testgetEligibleBenefits() {
		Policy policy1 = new Policy("P1001","Health Plus Classic",500000,15639);
		
		Benefits b1 = new Benefits("B101","Coverage for COVID-19");
		Benefits b2 = new Benefits("B102","Coverage for hospitalization at home");
		
		policy1.getBenefits().add(b1);
		policy1.getBenefits().add(b2);

		String token = "AAA";
		ValidatingDTO validatingDTO = new ValidatingDTO(true);
		when(authClient.getsValidity(token)).thenReturn(validatingDTO);

		BenefitsDTO benefits=new BenefitsDTO();
		benefits.setBenefits(policy1.getBenefits());

		when(this.policyServiceImpl.getBenefits("P1001", token)).thenReturn(benefits);
		ResponseEntity<BenefitsDTO> response=policyController.getEligibleBenefits("P1001", "M101", token);
		assertEquals(HttpStatus.OK,response.getStatusCode());		
	}
	
	@Test
   	@DisplayName("Testing getEligibleBenefits invalid Token Id Exception")
   	public void getEligibleBenefits_fails() {
       	
    	when(authClient.getsValidity(anyString())).thenReturn(new ValidatingDTO(false));
    	Assertions.assertThrows(InvalidTokenException.class, ()->{
    		policyController.getEligibleBenefits("100", "123", "token");
    	});
   	}
	
	@Test
	@DisplayName("Checking getEligibleAmount is working or not")
	void testgetEligibleAmount() {
		ClaimAmountDTO amount = new ClaimAmountDTO(5000); 
		
		when(authClient.getsValidity(anyString())).thenReturn(new ValidatingDTO(true));
		
		when(policyServiceImpl.getClaimAmount("P1001", "M101")).thenReturn(new ClaimAmountDTO(5000));
		
    	ResponseEntity<ClaimAmountDTO> response = policyController.getEligibleAmount("P1001", "M101", "BBB");    	
    	assertEquals(amount.getEligibleAmount(), response.getBody().getEligibleAmount());
	}
	
	@Test
   	@DisplayName("Testing getEligibleAmount invalid Token Id Exception")
   	public void testgetEligibleAmount_fails() {
       	
    	when(authClient.getsValidity(anyString())).thenReturn(new ValidatingDTO(false));
    	Assertions.assertThrows(InvalidTokenException.class, ()->{
    		policyController.getEligibleAmount("100", "123", "token");
    	});
   	}

}
