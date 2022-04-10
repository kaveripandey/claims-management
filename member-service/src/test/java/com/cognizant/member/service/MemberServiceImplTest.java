package com.cognizant.member.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cognizant.member.client.ClaimsClient;
import com.cognizant.member.dto.ClaimStatusDTO;
import com.cognizant.member.entity.Bills;
import com.cognizant.member.exception.InvalidClaimIdException;
import com.cognizant.member.exception.InvalidMemberIdException;
import com.cognizant.member.model.ClaimDetails;
import com.cognizant.member.repository.MemberServiceRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class MemberServiceImplTest {

	@MockBean
	private MemberServiceRepository billsRepo;

	@MockBean
	private ClaimsClient claimClient;

	@InjectMocks
	MemberServiceImpl claimStatusAndDetails;

	@Test
	@DisplayName("Checking if [ClaimStatusAndDetailsTest] class is loading or not.")
	void processingRequestIsLoadedOrNot() {
		assertThat(claimStatusAndDetails).isNotNull();
	}

	@DisplayName("Checking if Get Bills Method is working or not")
	@Test
	void testGetBillsMethod() {
		Date d1 = Date.valueOf("2021-05-04");
		Date d2 = Date.valueOf("2020-06-04");

		Bills bill = new Bills("221", "P2314", d1, 65600, 1200, d2);
		billsRepo.save(bill);
		assertThat(billsRepo.findById("221")).isNotNull();
	}

	@Test
	@DisplayName("Checking if get Bills method is working or not with Valid Id")
	void testGetBillsWithValidDetails() {
		Date d1 = Date.valueOf("2021-05-04");
		Date d2 = Date.valueOf("2020-06-04");
		Optional<Bills> bill = Optional.of(new Bills("221", "P2314", d1, 65600, 1200, d2));
		when(billsRepo.findById("221")).thenReturn(bill);
		assertEquals(bill.get(), claimStatusAndDetails.fetchBills("221"));
	}

	@Test
	@DisplayName("Checking if getClaimStatus method is working or not with Invalid Id")
	void testGetBillsWithInvalidId() {

		assertThrows(InvalidMemberIdException.class, () -> claimStatusAndDetails.fetchBills("M10114"));
	}

	@DisplayName("Checking if Get Claim Status Method is working or not")
	@Test
	void testGetClaimStatusWithValidId() {
		String C101 = UUID.randomUUID().toString();
		ClaimStatusDTO claimStatusDTO = new ClaimStatusDTO(C101, "Pending Action", "Waiting");
		String token = "AAA";
		
		when(claimStatusAndDetails.fetchClaimStatus(C101, token)).thenReturn(claimStatusDTO);

		assertEquals(C101, claimStatusAndDetails.fetchClaimStatus(C101, token).getClaimId());
		assertEquals("Pending Action", claimStatusAndDetails.fetchClaimStatus(C101, token).getClaimStatus());
		assertEquals("Waiting", claimStatusAndDetails.fetchClaimStatus(C101, token).getClaimDescription());

	}

	@DisplayName("Checking if Get Claim Status Method is working or not")
	@Test
	void testGetClaimDetailsWithValidDetails() {
		String C101 = UUID.randomUUID().toString();
		ClaimDetails claimDetails = new ClaimDetails(C101, "Pending Action", "Waiting", "Nothing", 10000, "H1", "B101", "P1001", "M101");

		ClaimStatusDTO claimStatusDTO = new ClaimStatusDTO(C101, "Pending Action", "Waiting");
		String token = "AAA";
		when(claimStatusAndDetails.fetchClaimDetails(claimDetails, token)).thenReturn(claimStatusDTO);

		assertEquals(C101, claimStatusAndDetails.fetchClaimDetails(claimDetails, token).getClaimId());
		assertEquals(claimDetails.getStatus(), claimStatusAndDetails.fetchClaimDetails(claimDetails, token).getClaimStatus());
		assertEquals(claimDetails.getDescription(), claimStatusAndDetails.fetchClaimDetails(claimDetails, token).getClaimDescription());
	}

	@Test
	@DisplayName("Checking if Generate Claim Id method is working or not")
	void testGenerateClaimId() {

		String uuid = UUID.randomUUID().toString();
		String convertToUUID = claimStatusAndDetails.generateClaimId();
		assertNotEquals(uuid, convertToUUID);

	}

}
