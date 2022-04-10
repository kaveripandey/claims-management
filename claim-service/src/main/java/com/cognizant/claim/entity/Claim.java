package com.cognizant.claim.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "claims")

@Getter
@Setter 
@NoArgsConstructor 
@AllArgsConstructor
public class Claim {

	@Id
    @Column(name="c_id")
	private String claimId;
	
    @Column(name="status")	
	private String status;
    
    @Column(name="description")	
	private String description;
    
    @Column(name="remarks")	
	private String remarks;
    
    @Column(name="claim_amount")	
	private double claimAmount;

   
    @Column(name="hospital_id")
    private String hospitalId;
    
  
    @Column(name="benefit_id")
    private String benefitId;
    
    @Column(name="policy_id")
    private String policyId;
    
    @Column(name="member_id")
    private String memberId;
    
    
}