package com.cognizant.member.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.member.entity.Bills;

@Repository
public interface MemberServiceRepository extends JpaRepository<Bills, String> {

}