package com.cognizant.policy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.policy.entity.MemberPolicy;

@Repository
public interface MemberPolicyRepo extends JpaRepository<MemberPolicy,String>{	

}
