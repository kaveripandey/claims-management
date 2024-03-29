package com.cognizant.member.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.sql.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.cognizant.member.client.AuthClient;
import com.cognizant.member.dto.ClaimStatusDTO;
import com.cognizant.member.dto.ValidatingDTO;
import com.cognizant.member.entity.Bills;
import com.cognizant.member.exception.InvalidClaimIdException;
import com.cognizant.member.exception.InvalidTokenException;
import com.cognizant.member.model.ClaimDetails;
import com.cognizant.member.service.MemberService;

@SpringBootTest
public class MemberControllerTest {

	@InjectMocks
	MemberController memberController;

	@Mock
	MemberService memberService;

	@Mock
	AuthClient authClient;

	@Test
	@DisplayName("Checking for MemberController - if it is loading or not for @GetMapping")
	void MemberControllerNotNullTest() {

		MemberController memberController = new MemberController();
		assertThat(memberController).isNotNull();
	}

	@Test
	@DisplayName("Testing get Bills is working correctly or not")
	public void testgetBills() {

		Date d2 = Date.valueOf("2020-06-04");
		Date d3 = Date.valueOf("2011-07-02");

		Bills billsObj = new Bills("221", "P2314", d3, 65600, 1200, d2);
		when(authClient.getsValidity(anyString())).thenReturn(new ValidatingDTO(true));
		when(memberService.fetchBills("121")).thenReturn(new Bills("221", "P2314", d3, 65600, 1200, d2));

		ResponseEntity<Bills> response = memberController.getBills("121", "token");

		assertEquals(billsObj.getMemberId(), response.getBody().getMemberId());
	}

	@Test()
	@DisplayName("Testing get Bills invalid Token Id Exception")
	public void testgetBills_fails2() {

		when(authClient.getsValidity(anyString())).thenReturn(new ValidatingDTO(false));
		Assertions.assertThrows(InvalidTokenException.class, () -> {
			memberController.getBills("121", "token");
		});
	}

	@Test
	@DisplayName("Testing get claim Status is working correctly or not")
	public void testgetClaimStatus() {

		when(authClient.getsValidity(anyString())).thenReturn(new ValidatingDTO(true));
		when(memberService.fetchClaimStatus("AAAA", "token"))
				.thenReturn(new ClaimStatusDTO("AAAA", "Pending", "Need More Action"));

		ResponseEntity<ClaimStatusDTO> response = memberController.returnClaimStatus("AAAA", "token");

		assertEquals("Pending", response.getBody().getClaimStatus());
	}

	@Test
	@DisplayName("Testing get Claim Status invalid Claim Id Exception")
	public void testgetClaimStatus_fails1() {

		when(authClient.getsValidity(anyString())).thenReturn(new ValidatingDTO(true));
		when(memberService.fetchClaimStatus(anyString(), anyString())).thenThrow(InvalidClaimIdException.class);

		Assertions.assertThrows(InvalidClaimIdException.class, () -> {
			memberController.returnClaimStatus("AAAA", "token");
		});
	}

	@Test
	@DisplayName("Testing get Claim Status invalid Token Id Exception")
	public void testgetClaimStatus_fails2() {

		when(authClient.getsValidity(anyString())).thenReturn(new ValidatingDTO(false));
		Assertions.assertThrows(InvalidTokenException.class, () -> {
			memberController.returnClaimStatus("AAAA", "token");
		});
	}

	@Test
	@DisplayName("Testing get Submitting Claim is working correctly or not")
	public void testgetClaimStatusOnUpdate() {

		ClaimDetails claimDetails = new ClaimDetails();
		claimDetails.setClaimId("AAAA");
		claimDetails.setStatus("Pending");
		when(authClient.getsValidity(anyString())).thenReturn(new ValidatingDTO(true));
		when(memberService.fetchClaimDetails(claimDetails, "token"))
				.thenReturn(new ClaimStatusDTO("AAAA", "Pending", "Need More Action"));

		ResponseEntity<ClaimStatusDTO> response = memberController.returnClaimStatusOnUpdate(claimDetails, "token");

		assertEquals("Pending", response.getBody().getClaimStatus());
	}

	@Test
	@DisplayName("Testing Submitting Bills invalid Token Id Exception")
	public void testClaimStatusOnUpdate_fails1() {

		ClaimDetails claimDetails = new ClaimDetails();
		when(authClient.getsValidity(anyString())).thenReturn(new ValidatingDTO(false));
		when(authClient.getsValidity(anyString())).thenReturn(new ValidatingDTO(false));
		Assertions.assertThrows(InvalidTokenException.class, () -> {
			memberController.returnClaimStatusOnUpdate(claimDetails, "token");
		});

	}

}
