package com.cognizant.policy.dto;
import java.util.Set;

import com.cognizant.policy.entity.Hospital;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ProviderDTO {
	
	@JsonProperty
	private Set<Hospital> providers;
		@JsonIgnore
		public ProviderDTO(Set<Hospital> providers) {
			this.providers=providers;
		}
		@JsonIgnore
		public Set<Hospital> getProviders() {
			return providers;
		}
		@JsonIgnore
		public void setProviders(Set<Hospital> providers) {
			this.providers = providers;
		}
		
		
		

}