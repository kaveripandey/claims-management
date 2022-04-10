package com.cognizant.member.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;

import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "bills")

@Getter
@Setter 
@NoArgsConstructor 
@AllArgsConstructor
public class Bills {
	
	@Id
    @Column(name="m_id")
	private String memberId;
	
    @Column(name="p_id")	
	private String policyId;
    
    @Column(name="last_paid_date")	
	private Date  lastPaidDate;
    
    @Column(name="due_amount")	
	private int dueAmount;
    
    @Column(name="late_charge")	
	private int  lateCharge;
    
    @Column(name="due_date")	
	private Date  dueDate;
	
	
}