package com.cognizant.claim.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClaimStatusDTO {
	private String claimId;
	private String claimStatus;
	
	private String claimDescription;

}