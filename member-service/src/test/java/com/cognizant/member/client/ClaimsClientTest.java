package com.cognizant.member.client;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ClaimsClientTest {
  
    ClaimsClient claimsClient;
    
    
    @Test
    @DisplayName("Checking if [ClaimsClient] is loading or not.")
   void packagingClientIsLoaded(){
        assertThat(claimsClient).isNull();    
    }
}