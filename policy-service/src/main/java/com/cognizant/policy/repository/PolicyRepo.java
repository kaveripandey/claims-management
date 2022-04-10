package com.cognizant.policy.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.policy.entity.Policy;

@Repository
public interface PolicyRepo extends JpaRepository<Policy,String>{
	
}
