package com.cognizant.policy.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="benefits")
public class Benefits {

	@Id
	@Column(name="b_id")
	private String benefitId;
	
	@Column(name="name")
	private String benefitName;

	public Benefits(String benefitId, String benefitName) {
		super();
		this.benefitId = benefitId;
		this.benefitName = benefitName;
	}
	
	@JsonIgnore	
	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL,mappedBy = "benefits")
	private Set<Policy> policyBenefits = new HashSet<>();
	
	
}
