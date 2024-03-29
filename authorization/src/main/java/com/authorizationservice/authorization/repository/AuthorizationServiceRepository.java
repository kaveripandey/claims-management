package com.authorizationservice.authorization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.authorizationservice.authorization.model.AuthenticationRequest;

@Repository
public interface AuthorizationServiceRepository extends JpaRepository<AuthenticationRequest, String> {
	
}
 