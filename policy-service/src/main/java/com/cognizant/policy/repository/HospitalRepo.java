package com.cognizant.policy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.policy.entity.Hospital;

@Repository
public interface HospitalRepo extends JpaRepository<Hospital,String>{
	
}
