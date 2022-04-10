package com.cognizant.policy.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.JoinColumn;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="policy")
@Getter
@Setter
@NoArgsConstructor
public class Policy{
	
	@Id
	@Column(name="p_id")
	private String policyId;
	
	@Column(name="policy_type")
	private String policyType;
	
	@Column(name="cap_amount")
	private double sumInsured;
	
	@Column(name="premium")
	private double premium;
	
	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(name="provider_policy", joinColumns= @JoinColumn(name="policy_id"),
								  inverseJoinColumns= @JoinColumn(name="hospital_id"))
	private Set<Hospital> hospitals = new HashSet<>();
	
	@JsonIgnore	
	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(name="policy_benefits", joinColumns= @JoinColumn(name="policy_id"),
					  inverseJoinColumns= @JoinColumn(name="benefit_id"))
	private Set<Benefits> benefits = new HashSet<>();
	
	public Policy(String policyId, String policyType, double sumInsured, double premium) {
		super();
		this.policyId = policyId;
		this.policyType = policyType;
		this.sumInsured = sumInsured;
		this.premium = premium;
	}
}