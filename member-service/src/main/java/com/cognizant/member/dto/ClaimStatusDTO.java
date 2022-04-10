package com.cognizant.member.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClaimStatusDTO {
	private String claimId;
	private String claimStatus;
	private String claimDescription;
	

}