package com.cognizant.policy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.policy.entity.Benefits;

@Repository
public interface BenefitsRepo extends JpaRepository<Benefits,String>{
	
}
