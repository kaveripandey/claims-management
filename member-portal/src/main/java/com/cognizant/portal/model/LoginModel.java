package com.cognizant.portal.model;


import javax.persistence.Column;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@Entity
//@Table(name = "LoginDetails")

@Getter
@Setter
@ToString
@Component
@AllArgsConstructor
@NoArgsConstructor
public class LoginModel {
	
	@Id  
    @Column(name="Username")
	private String username;
	
	@Column(name="password")
	private String password;
}
