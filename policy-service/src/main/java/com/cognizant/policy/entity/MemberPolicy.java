package com.cognizant.policy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="member_policy")
public class MemberPolicy {
	
	@Id
	@Column(name="m_id")
	private String memberId;
	
	@Column(name="p_id")
	private String policyId;
	
	@Column(name="tenure")
	private int tenure;
	
	@Column(name="premium_last_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private String premiumLastDate;
	
	@Column(name="subscription_date")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private String subscriptionDate;
}
