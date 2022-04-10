package com.cognizant.claim.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cognizant.claim.client.PolicyClient;
import com.cognizant.claim.dto.BenefitsDTO;
import com.cognizant.claim.dto.ClaimAmountDTO;
import com.cognizant.claim.dto.ClaimDTO;
import com.cognizant.claim.dto.ClaimStatusDTO;
import com.cognizant.claim.dto.ProviderDTO;
import com.cognizant.claim.exception.InvalidClaimIdException;
import com.cognizant.claim.exception.PolicyException;
import com.cognizant.claim.model.Benefits;
import com.cognizant.claim.model.Hospital;
import com.cognizant.claim.repository.ClaimServiceRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
class ClaimServiceImplTest {

    @InjectMocks
	ClaimServiceImpl claimServiceImpl;
	@MockBean
	ClaimServiceRepository claimServiceRepo;
	@MockBean
	PolicyClient policyClient;
	@Test
	@DisplayName("Checking if ClaimStatusService class is loading or not")
	void claimStatusServiceIsLoadedOrNot() {
		assertThat(claimServiceImpl).isNotNull();
	}
	
	@Test
	@DisplayName("Checking if getClaimStatus method is working or not with valid Id")
	void getClaimStatusTestWithValidId() {
		ClaimStatusDTO claimStatusDTO=new ClaimStatusDTO();
		claimStatusDTO.setClaimId("C101");
		claimStatusDTO.setClaimStatus("Pending");
		claimStatusDTO.setClaimDescription("Verified");
		
		
		when(claimServiceRepo.getStatus("C101")).thenReturn("Pending");
		when(claimServiceRepo.getDescription("C101")).thenReturn("Verified");
		
		
		assertEquals(claimStatusDTO.getClaimId(), claimServiceImpl.getClaimStatus("C101").getClaimId()); 
		assertEquals(claimStatusDTO.getClaimStatus(), claimServiceImpl.getClaimStatus("C101").getClaimStatus()); 
		assertEquals(claimStatusDTO.getClaimDescription(), claimServiceImpl.getClaimStatus("C101").getClaimDescription()); 
	}
	
	@Test
	@DisplayName("Checking if getClaimStatus method is working or not with Invalid Id")
	void testGetClaimStatusWithInvalidValidId() {
		
		assertThrows(InvalidClaimIdException.class, () ->
		claimServiceImpl.getClaimStatus("C1561"));
	}
	
	@Test
	@DisplayName("Checking if SubmitClaim method is working or not with valid claim object")
	void testSubmitClaimMethodWithValidClaimObject() {
		ClaimDTO claimDTO=new ClaimDTO("C101","Pending","Verified","Nothing",12000.0,"H101","B101","P101","M101");
		
		String token="AAA";
		Hospital hospital1 = new Hospital("H101","Apollo Hospital","Delhi-Indraprastha");
		Hospital hospital2 = new Hospital("H102","Artemis Hospital","Gurgaon");
		Hospital hospital3 = new Hospital("H103","Fortis Escorts Heart Institute","Delhi-Okhla");
		List<Hospital> hospitalList=new ArrayList<>();
		hospitalList.add(hospital1);
		hospitalList.add(hospital2);
		hospitalList.add(hospital3);
		
		Benefits b1 = new Benefits("B101","Coverage for COVID-19");
		Benefits b2 = new Benefits("B102","Coverage for hospitalization at home");
		Benefits b3 = new Benefits("B103","Ambulance charges upto 2000 covered");
		Benefits b4 = new Benefits("B104","Ambulance charges upto 3000 covered");
		Benefits b5 = new Benefits("B105","Ambulance charges upto 4000 covered");
		List<Benefits> benefitList=new ArrayList<>();
		benefitList.add(b1);
		benefitList.add(b2);
		benefitList.add(b3);
		benefitList.add(b4);
		benefitList.add(b5);
		
		ProviderDTO providerDTO=new ProviderDTO(hospitalList);
		BenefitsDTO benefitsDTO=new BenefitsDTO(benefitList);
		ClaimAmountDTO claimAmountDTO=new ClaimAmountDTO(120000);
		
		when(policyClient.getChainOfProviders("P101", token)).thenReturn(new ResponseEntity<ProviderDTO>(providerDTO,HttpStatus.OK));
		when(policyClient.getEligibleBenefits("P101", "M101", token)).thenReturn(new ResponseEntity<BenefitsDTO>(benefitsDTO,HttpStatus.OK));
		when(policyClient.getEligibleAmount("P101", "M101", token)).thenReturn(new ResponseEntity<ClaimAmountDTO>(claimAmountDTO,HttpStatus.OK));
		
		ClaimStatusDTO claimStatusDTO=new ClaimStatusDTO();
		claimStatusDTO.setClaimId("C101");
		claimStatusDTO.setClaimStatus("Pending Action");
		claimStatusDTO.setClaimDescription("All the fields are successfully verified! Please wait for furthur action");
		
		
		assertEquals(claimStatusDTO.getClaimId(), claimServiceImpl.submitClaim(claimDTO, token).getClaimId()); 
		assertEquals(claimStatusDTO.getClaimStatus(), claimServiceImpl.submitClaim(claimDTO, token).getClaimStatus()); 
		assertEquals(claimStatusDTO.getClaimDescription(),claimServiceImpl.submitClaim(claimDTO, token).getClaimDescription()); 
		
	}
	
	@Test
	@DisplayName("Checking Submit Claim Method with Wrong Policy ID")
	void testSubmitClaimWithInvalidPolicyId() {
		ClaimDTO claimDTO=new ClaimDTO("C101","Pending","Verified","Nothing",12000.0,"H101","B101","P1222","M101");
		String token="AAA";
		when(policyClient.getChainOfProviders("P1222", token)).thenThrow(PolicyException.class);
		assertThrows(PolicyException.class, () ->
		claimServiceImpl.submitClaim(claimDTO,token));
		
	}
	
	@Test
	@DisplayName("Checking Submit Claim Method with valid Policy ID But Null record")
	void testSubmitClaimWithNullProvider() {
		ClaimDTO claimDTO=new ClaimDTO("C101","Pending","Verified","Nothing",12000.0,"H101","B101","P101","M101");
		String token="AAA";
		
		Benefits b1 = new Benefits("B101","Coverage for COVID-19");
		Benefits b2 = new Benefits("B102","Coverage for hospitalization at home");
		Benefits b3 = new Benefits("B103","Ambulance charges upto 2000 covered");
		Benefits b4 = new Benefits("B104","Ambulance charges upto 3000 covered");
		Benefits b5 = new Benefits("B105","Ambulance charges upto 4000 covered");
		List<Benefits> benefitList=new ArrayList<>();
		benefitList.add(b1);
		benefitList.add(b2);
		benefitList.add(b3);
		benefitList.add(b4);
		benefitList.add(b5);
		
		ProviderDTO providerDTO=new ProviderDTO();
		BenefitsDTO benefitsDTO=new BenefitsDTO(benefitList);
		ClaimAmountDTO claimAmountDTO=new ClaimAmountDTO(120000);
		
		when(policyClient.getChainOfProviders("P101", token)).thenReturn(new ResponseEntity<ProviderDTO>(providerDTO,HttpStatus.OK));
		when(policyClient.getEligibleBenefits("P101", "M101", token)).thenReturn(new ResponseEntity<BenefitsDTO>(benefitsDTO,HttpStatus.OK));
		when(policyClient.getEligibleAmount("P101", "M101", token)).thenReturn(new ResponseEntity<ClaimAmountDTO>(claimAmountDTO,HttpStatus.OK));
		
		ClaimStatusDTO claimStatusDTO=new ClaimStatusDTO();
		claimStatusDTO.setClaimId("C101");
		claimStatusDTO.setClaimStatus("Claim Rejected");
		claimStatusDTO.setClaimDescription("Insufficient Claim Details");
		
		assertEquals(claimStatusDTO.getClaimId(), claimServiceImpl.submitClaim(claimDTO, token).getClaimId()); 
		assertEquals(claimStatusDTO.getClaimStatus(), claimServiceImpl.submitClaim(claimDTO, token).getClaimStatus()); 
		assertEquals(claimStatusDTO.getClaimDescription(),claimServiceImpl.submitClaim(claimDTO, token).getClaimDescription()); 
	}
	
	@Test
	@DisplayName("Checking Submit Claim Method with valid Benefit ID But Null record")
	void testSubmitClaimWithNullBenefits() {
		ClaimDTO claimDTO=new ClaimDTO("C101","Pending","Verified","Nothing",12000.0,"H101","B101","P101","M101");
		String token="AAA";
		
		Hospital hospital1 = new Hospital("H101","Apollo Hospital","Delhi-Indraprastha");
		Hospital hospital2 = new Hospital("H102","Artemis Hospital","Gurgaon");
		Hospital hospital3 = new Hospital("H103","Fortis Escorts Heart Institute","Delhi-Okhla");
		List<Hospital> hospitalList=new ArrayList<>();
		hospitalList.add(hospital1);
		hospitalList.add(hospital2);
		hospitalList.add(hospital3);
		
		
		
		ProviderDTO providerDTO=new ProviderDTO(hospitalList);
		BenefitsDTO benefitsDTO=new BenefitsDTO();
		ClaimAmountDTO claimAmountDTO=new ClaimAmountDTO(120000.0);
		
		when(policyClient.getChainOfProviders("P101", token)).thenReturn(new ResponseEntity<ProviderDTO>(providerDTO,HttpStatus.OK));
		when(policyClient.getEligibleBenefits("P101", "M101", token)).thenReturn(new ResponseEntity<BenefitsDTO>(benefitsDTO,HttpStatus.OK));
		when(policyClient.getEligibleAmount("P101", "M101", token)).thenReturn(new ResponseEntity<ClaimAmountDTO>(claimAmountDTO,HttpStatus.OK));
		
		ClaimStatusDTO claimStatusDTO=new ClaimStatusDTO();
		claimStatusDTO.setClaimId("C101");
		claimStatusDTO.setClaimStatus("Claim Rejected");
		claimStatusDTO.setClaimDescription("Insufficient Claim Details");
		
		assertEquals(claimStatusDTO.getClaimId(), claimServiceImpl.submitClaim(claimDTO, token).getClaimId()); 
		assertEquals(claimStatusDTO.getClaimStatus(), claimServiceImpl.submitClaim(claimDTO, token).getClaimStatus()); 
		assertEquals(claimStatusDTO.getClaimDescription(),claimServiceImpl.submitClaim(claimDTO, token).getClaimDescription()); 
	}
	
	@Test
	@DisplayName("Checking Submit Claim Method with Invalid Amount record")
	void testSubmitClaimWithInvalidAmount() {
		ClaimDTO claimDTO=new ClaimDTO("C101","Pending","Verified","Nothing",12000.0,"H101","B101","P101","M101");
		String token="AAA";
		
		Hospital hospital1 = new Hospital("H101","Apollo Hospital","Delhi-Indraprastha");
		Hospital hospital2 = new Hospital("H102","Artemis Hospital","Gurgaon");
		Hospital hospital3 = new Hospital("H103","Fortis Escorts Heart Institute","Delhi-Okhla");
		List<Hospital> hospitalList=new ArrayList<>();
		hospitalList.add(hospital1);
		hospitalList.add(hospital2);
		hospitalList.add(hospital3);
		Benefits b1 = new Benefits("B101","Coverage for COVID-19");
		Benefits b2 = new Benefits("B102","Coverage for hospitalization at home");
		Benefits b3 = new Benefits("B103","Ambulance charges upto 2000 covered");
		Benefits b4 = new Benefits("B104","Ambulance charges upto 3000 covered");
		Benefits b5 = new Benefits("B105","Ambulance charges upto 4000 covered");
		List<Benefits> benefitList=new ArrayList<>();
		benefitList.add(b1);
		benefitList.add(b2);
		benefitList.add(b3);
		benefitList.add(b4);
		benefitList.add(b5);
		
		ProviderDTO providerDTO=new ProviderDTO(hospitalList);
		BenefitsDTO benefitsDTO=new BenefitsDTO(benefitList);
		ClaimAmountDTO claimAmountDTO=new ClaimAmountDTO(120.0);
		
		when(policyClient.getChainOfProviders("P101", token)).thenReturn(new ResponseEntity<ProviderDTO>(providerDTO,HttpStatus.OK));
		when(policyClient.getEligibleBenefits("P101", "M101", token)).thenReturn(new ResponseEntity<BenefitsDTO>(benefitsDTO,HttpStatus.OK));
		when(policyClient.getEligibleAmount("P101", "M101", token)).thenReturn(new ResponseEntity<ClaimAmountDTO>(claimAmountDTO,HttpStatus.OK));
		
		ClaimStatusDTO claimStatusDTO=new ClaimStatusDTO();
		claimStatusDTO.setClaimId("C101");
		claimStatusDTO.setClaimStatus("Claim Rejected");
		claimStatusDTO.setClaimDescription("Claim amount is not valid");
		
		assertEquals(claimStatusDTO.getClaimId(), claimServiceImpl.submitClaim(claimDTO, token).getClaimId()); 
		assertEquals(claimStatusDTO.getClaimStatus(), claimServiceImpl.submitClaim(claimDTO, token).getClaimStatus()); 
		assertEquals(claimStatusDTO.getClaimDescription(),claimServiceImpl.submitClaim(claimDTO, token).getClaimDescription()); 
	}
	
	
	@Test
	@DisplayName("Checking Submit Claim Method with Wrong Member ID")
	void testSubmitClaimWithInvalidMemeberId() {
		ClaimDTO claimDTO=new ClaimDTO("C101","Pending","Verified","Nothing",12000.0,"H101","B101","P101","M1222");
		String token="AAA";
		Hospital hospital1 = new Hospital("H101","Apollo Hospital","Delhi-Indraprastha");
		Hospital hospital2 = new Hospital("H102","Artemis Hospital","Gurgaon");
		Hospital hospital3 = new Hospital("H103","Fortis Escorts Heart Institute","Delhi-Okhla");
		List<Hospital> hospitalList=new ArrayList<>();
		hospitalList.add(hospital1);
		hospitalList.add(hospital2);
		hospitalList.add(hospital3);
		ProviderDTO providerDTO=new ProviderDTO(hospitalList);
		when(policyClient.getChainOfProviders("P101", token)).thenReturn(new ResponseEntity<ProviderDTO>(providerDTO,HttpStatus.OK));
		when(policyClient.getEligibleBenefits("P101","M1222", token)).thenThrow(PolicyException.class);
		assertThrows(PolicyException.class, () ->
		claimServiceImpl.submitClaim(claimDTO,token));
		
	}
	
	
	
}
