package com.cognizant.member.service;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.member.client.ClaimsClient;
import com.cognizant.member.dto.ClaimStatusDTO;
import com.cognizant.member.entity.Bills;
import com.cognizant.member.exception.InvalidClaimIdException;
import com.cognizant.member.exception.InvalidMemberIdException;
import com.cognizant.member.model.ClaimDetails;
import com.cognizant.member.repository.MemberServiceRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberServiceImpl implements MemberService{

        @Autowired
        private MemberServiceRepository billsRepo;
        
        @Autowired
        private ClaimsClient claimsClient;
        
        public  ClaimStatusDTO fetchClaimStatus(String claimId, String token)
        {
            log.info("Inside the fetch Claim Status method : Begin");
            ClaimStatusDTO claimStatusDTO  = null;
            
            log.info("Claims Client Called to get Claim Status");
            try {
            claimStatusDTO = claimsClient.statusDetails(claimId,token);
            }
            catch(Exception e)
            {
                throw new InvalidClaimIdException("The Claim Id is Invalid");
            }
            
            return claimStatusDTO;
        }
        
        
        public  Bills fetchBills(String memberId)
        {
            log.info("Inside the fetch Bills method : Begin");
            
            Optional<Bills> bill = billsRepo.findById(memberId);
            if(!bill.isPresent() )
    		{
    			throw new InvalidMemberIdException("The Member Id is Invalid");
    		}
            
            return bill.get();
           
        }
        
        
        public  ClaimStatusDTO fetchClaimDetails( ClaimDetails claimDetails,String token)
        {
            log.info("Inside the fetch Claim Status method : Begin");
            ClaimStatusDTO claimStatusDTO  = null;
            claimDetails.setClaimId(generateClaimId());
            try {
            claimStatusDTO = claimsClient.onSbmitStatusDetails(claimDetails,token);
            }
            catch(Exception e)
            {
            	throw e;
            }
            return claimStatusDTO;
        }
        
        public String generateClaimId()
        {
        	return UUID.randomUUID().toString();
        }
        
}