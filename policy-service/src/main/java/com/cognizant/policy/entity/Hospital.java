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
@Entity
@NoArgsConstructor
@Table(name="hospital")
public class Hospital {
	
	@Id
	@Column(name="h_id")
	private String hospitalId;
	
	@Column(name="name")
	private String name;
	
	@Column(name="location")
	private String location;
	
	@JsonIgnore
	@ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL, mappedBy="hospitals")
	private Set<Policy> policies = new HashSet<>();

	public Hospital(String hospitalId, String name, String location) {
		super();
		this.hospitalId = hospitalId;
		this.name = name;
		this.location = location;
	}
	
}
