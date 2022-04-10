package com.cognizant.portal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.cognizant.portal.MemberPortalApplication;

public class MemberPortalApplicationTest {

	@Autowired
	MemberPortalApplication returnOrderPortalApplication;
	
	@Test
	void contextLoads() {
		assertTrue(true);
	}
	
	@Test
	void mainTest()
	{
		returnOrderPortalApplication.main(new String[] {});
	}
}
