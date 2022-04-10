package com.cognizant.claim;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ClaimServiceApplicationTests {

	ClaimServiceApplication claimServiceApplication;

	@Test
	void contextLoads() {
		assertTrue(true);
	}

	@Test
	void testComponentProcessingMicroserviceApplication() {
		assertThat(claimServiceApplication).isNull();
	}

	@Test
	public void applicationStarts() {
		ClaimServiceApplication.main(new String[] {});
	}
}
