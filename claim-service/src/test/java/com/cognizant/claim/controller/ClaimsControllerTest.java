package com.cognizant.claim.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cognizant.claim.client.AuthClient;
import com.cognizant.claim.dto.ClaimDTO;
import com.cognizant.claim.dto.ClaimStatusDTO;
import com.cognizant.claim.dto.ValidatingDTO;
import com.cognizant.claim.exception.InvalidTokenException;
import com.cognizant.claim.service.ClaimService;

@SpringBootTest
class ClaimsControllerTest {

	@Autowired
	ClaimsController claimsController;
	@MockBean
	AuthClient authClient;
	@MockBean
	ClaimService claimService;
	
	@Test
    @DisplayName("Checking for Claims Controller - if it is loading or not for @GetMapping")
    void componentProcessingControllerNotNullTest(){
        assertThat(claimsController).isNotNull();
    }
	
	@Test
	@DisplayName("Checking getClaimStatus Endpoint with Invalid Token")
	void testGetClaimStatusEndpointWithInvalidToken() {
		String token="BBB";
		ValidatingDTO validatingDTO=new ValidatingDTO(false);
		when(authClient.getsValidity(token)).thenReturn(validatingDTO);
		
		assertThrows(InvalidTokenException.class, () ->
        claimsController.getClaimStatus("C101",token));
	}
	
	@Test
	@DisplayName("Checking submit Claim Endpoint with Invalid Token")
	void testSubmitClaimEndpointWithInvalidToken() {
		String token="BBB";
		ValidatingDTO validatingDTO=new ValidatingDTO(false);
		when(authClient.getsValidity(token)).thenReturn(validatingDTO);
		ClaimDTO claimDTO=new ClaimDTO();
		assertThrows(InvalidTokenException.class, () ->
        claimsController.submitClaim(claimDTO,token));
	}

	
	@Test
	@DisplayName("Checking the working of get claim Status Endpoint")
	void testGetClaimStatusWithValidParameters() {
		String token="BBB";
		ValidatingDTO validatingDTO=new ValidatingDTO(true);
		
		ClaimStatusDTO claimStatusDTO=new ClaimStatusDTO();
		claimStatusDTO.setClaimId("C101");
		claimStatusDTO.setClaimStatus("Pending Action");
		claimStatusDTO.setClaimDescription("Verified");
		
		when(authClient.getsValidity(token)).thenReturn(validatingDTO);
		when(claimService.getClaimStatus("C101")).thenReturn(claimStatusDTO);
		
		
		assertEquals(claimStatusDTO.getClaimId(), claimsController.getClaimStatus("C101",token).getBody().getClaimId()); 
		assertEquals(claimStatusDTO.getClaimStatus(), claimsController.getClaimStatus("C101",token).getBody().getClaimStatus()); 
		assertEquals(claimStatusDTO.getClaimDescription(), claimsController.getClaimStatus("C101",token).getBody().getClaimDescription()); 
	}
	
	@Test
	@DisplayName("Checking the working of Submit claim  Endpoint")
	void testSubmitClaimWithValidParameters() {
		String token="BBB";
		ValidatingDTO validatingDTO=new ValidatingDTO(true);
		ClaimDTO claimDTO=new ClaimDTO();
		ClaimStatusDTO claimStatusDTO=new ClaimStatusDTO();
		claimStatusDTO.setClaimId("C101");
		claimStatusDTO.setClaimStatus("Pending Action");
		claimStatusDTO.setClaimDescription("Verified");
		
		when(authClient.getsValidity(token)).thenReturn(validatingDTO);
		when(claimService.submitClaim(claimDTO,token)).thenReturn(claimStatusDTO);
		
		
		assertEquals(claimStatusDTO.getClaimId(), claimsController.submitClaim(claimDTO,token).getBody().getClaimId()); 
		assertEquals(claimStatusDTO.getClaimStatus(), claimsController.submitClaim(claimDTO,token).getBody().getClaimStatus()); 
		assertEquals(claimStatusDTO.getClaimDescription(),claimsController.submitClaim(claimDTO,token).getBody().getClaimDescription()); 
	}
}
