package com.cognizant.member;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class MemberServiceApplicationTests {

	MemberServiceApplication memberServiceApplication;
	@Test
	void contextLoads() {
		assertTrue(true);
	}
	
	@Test
	void testMemberServiceApplication() {
		assertThat(memberServiceApplication).isNull();
	}

}
