package com.cognizant.claim.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClaimDTO {

	private String claimId;

	private String status;

	private String description;

	private String remarks;

	private double claimAmount;

	private String hospitalId;

	private String benefitId;

	private String policyId;

	private String memberId;
}