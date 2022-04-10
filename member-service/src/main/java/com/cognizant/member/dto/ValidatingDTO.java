package com.cognizant.member.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ValidatingDTO {

	@JsonProperty
	private boolean validStatus;

}