# claims-management
###Authorization


###For Token Generation
POST http://localhost:8008/authorization/login
Content-Type: application/json
{
    "username" : "kaveri",
    "password" : "kaveri01"
}

###For Token validation
GET http://localhost:8008/authorization/validate
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJrYXZlcmkiLCJleHAiOjE2NDY3NTUxMDYsImlhdCI6MTY0Njc1MzMwNn0.EweBJUn4YSXVo4cVGg9bvQFRhT77QfB2-p9JrVPXiU4


### Member-Service


###For View Bills 
POST http://localhost:8081/memberModule/viewBills/M101
Content-Type: application/json
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJrYXZlcmkiLCJleHAiOjE2NDY3NTUxMDYsImlhdCI6MTY0Njc1MzMwNn0.EweBJUn4YSXVo4cVGg9bvQFRhT77QfB2-p9JrVPXiU4

###For Submit Claim Status
POST http://localhost:8081/memberModule/submitClaim
Content-Type: application/json
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJrYXZlcmkiLCJleHAiOjE2NDY3NTUxMDYsImlhdCI6MTY0Njc1MzMwNn0.EweBJUn4YSXVo4cVGg9bvQFRhT77QfB2-p9JrVPXiU4
{
    "claimId" : "C101",
    "status" : "Pending Action",
    "description" : "Verified",
    "remarks" : "Urgent",
    "claimAmount" : 500000,
    "hospitalId" : "H1",
    "benefitId" : "B102",
    "policyId" : "P1002",
    "memberId" : "M101" 
}

###For View Claim Status 
POST http://localhost:8081/memberModule/getClaimStatus/7209ba6a-3393-48d3-bbcc-40800c7b8703
Content-Type: application/json
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJrYXZlcmkiLCJleHAiOjE2NDY3NTUxMDYsImlhdCI6MTY0Njc1MzMwNn0.EweBJUn4YSXVo4cVGg9bvQFRhT77QfB2-p9JrVPXiU4


### Claim-Service


###For Submit Claim Status
POST http://localhost:8082/memberModule/submitClaim
Content-Type: application/json
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJrYXZlcmkiLCJleHAiOjE2NDY3NTUxMDYsImlhdCI6MTY0Njc1MzMwNn0.EweBJUn4YSXVo4cVGg9bvQFRhT77QfB2-p9JrVPXiU4
{
    "claimId" : "C101",
    "status" : "Pending Action",
    "description" : "Verified",
    "remarks" : "Urgent",
    "claimAmount" : 500000,
    "hospitalId" : "H1",
    "benefitId" : "B102",
    "policyId" : "P1002",
    "memberId" : "M101" 
}

###For View Claim Status 
POST http://localhost:8082/memberModule/getClaimStatus/7209ba6a-3393-48d3-bbcc-40800c7b8703
Content-Type: application/json
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJrYXZlcmkiLCJleHAiOjE2NDY3NTUxMDYsImlhdCI6MTY0Njc1MzMwNn0.EweBJUn4YSXVo4cVGg9bvQFRhT77QfB2-p9JrVPXiU4


###Policy-Service


###For Get Chain Of Providers
POST http://localhost:8083/policy/getChainOfProviders/P1001
Content-Type: application/json
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJrYXZlcmkiLCJleHAiOjE2NDY3NTUxMDYsImlhdCI6MTY0Njc1MzMwNn0.EweBJUn4YSXVo4cVGg9bvQFRhT77QfB2-p9JrVPXiU4

###For Get Eligible Benefits
POST http://localhost:8083/policy/getEligibleBenefits/P1001/M101
Content-Type: application/json
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJrYXZlcmkiLCJleHAiOjE2NDY3NTUxMDYsImlhdCI6MTY0Njc1MzMwNn0.EweBJUn4YSXVo4cVGg9bvQFRhT77QfB2-p9JrVPXiU4

###For Get Eligible Amount
POST http://localhost:8083/policy/getEligibleAmount/P1001/M101
Content-Type: application/json
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJrYXZlcmkiLCJleHAiOjE2NDY3NTUxMDYsImlhdCI6MTY0Njc1MzMwNn0.EweBJUn4YSXVo4cVGg9bvQFRhT77QfB2-p9JrVPXiU4



#############################AWS#######################
###Authorization


###For Token Generation
POST http://authorization-lbalancer-246348272.ap-south-1.elb.amazonaws.com/authorization/login
Content-Type: application/json
{
    "username" : "kaveri",
    "password" : "kaveri01"
}

###For Token validation
GET http://authorization-lbalancer-246348272.ap-south-1.elb.amazonaws.com/authorization/validate
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJrYXZlcmkiLCJleHAiOjE2NDY3NTUxMDYsImlhdCI6MTY0Njc1MzMwNn0.EweBJUn4YSXVo4cVGg9bvQFRhT77QfB2-p9JrVPXiU4


### Member-Service


###For View Bills 
POST http://member-lbalancer-1844630793.ap-south-1.elb.amazonaws.com/memberModule/viewBills/M101
Content-Type: application/json
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJrYXZlcmkiLCJleHAiOjE2NDY3NTUxMDYsImlhdCI6MTY0Njc1MzMwNn0.EweBJUn4YSXVo4cVGg9bvQFRhT77QfB2-p9JrVPXiU4

###For Submit Claim Status
POST http://member-lbalancer-1844630793.ap-south-1.elb.amazonaws.com/memberModule/submitClaim
Content-Type: application/json
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJrYXZlcmkiLCJleHAiOjE2NDY3NTUxMDYsImlhdCI6MTY0Njc1MzMwNn0.EweBJUn4YSXVo4cVGg9bvQFRhT77QfB2-p9JrVPXiU4
{
    "claimId" : "C101",
    "status" : "Pending Action",
    "description" : "Verified",
    "remarks" : "Urgent",
    "claimAmount" : 500000,
    "hospitalId" : "H1",
    "benefitId" : "B102",
    "policyId" : "P1002",
    "memberId" : "M101" 
}

###For View Claim Status 
POST http://member-lbalancer-1844630793.ap-south-1.elb.amazonaws.com/memberModule/getClaimStatus/7209ba6a-3393-48d3-bbcc-40800c7b8703
Content-Type: application/json
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJrYXZlcmkiLCJleHAiOjE2NDY3NTUxMDYsImlhdCI6MTY0Njc1MzMwNn0.EweBJUn4YSXVo4cVGg9bvQFRhT77QfB2-p9JrVPXiU4


### Claim-Service


###For Submit Claim Status
POST http://claim-lbalancer-684710123.ap-south-1.elb.amazonaws.com/memberModule/submitClaim
Content-Type: application/json
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJrYXZlcmkiLCJleHAiOjE2NDY3NTUxMDYsImlhdCI6MTY0Njc1MzMwNn0.EweBJUn4YSXVo4cVGg9bvQFRhT77QfB2-p9JrVPXiU4
{
    "claimId" : "C101",
    "status" : "Pending Action",
    "description" : "Verified",
    "remarks" : "Urgent",
    "claimAmount" : 500000,
    "hospitalId" : "H1",
    "benefitId" : "B102",
    "policyId" : "P1002",
    "memberId" : "M101" 
}

###For View Claim Status 
POST http://claim-lbalancer-684710123.ap-south-1.elb.amazonaws.com/memberModule/getClaimStatus/7209ba6a-3393-48d3-bbcc-40800c7b8703
Content-Type: application/json
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJrYXZlcmkiLCJleHAiOjE2NDY3NTUxMDYsImlhdCI6MTY0Njc1MzMwNn0.EweBJUn4YSXVo4cVGg9bvQFRhT77QfB2-p9JrVPXiU4


###Policy-Service


###For Get Chain Of Providers
POST http://policy-lbalancer-1038866339.ap-south-1.elb.amazonaws.com/policy/getChainOfProviders/P1001
Content-Type: application/json
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJrYXZlcmkiLCJleHAiOjE2NDY3NTUxMDYsImlhdCI6MTY0Njc1MzMwNn0.EweBJUn4YSXVo4cVGg9bvQFRhT77QfB2-p9JrVPXiU4

###For Get Eligible Benefits
POST http://policy-lbalancer-1038866339.ap-south-1.elb.amazonaws.com/policy/getEligibleBenefits/P1001/M101
Content-Type: application/json
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJrYXZlcmkiLCJleHAiOjE2NDY3NTUxMDYsImlhdCI6MTY0Njc1MzMwNn0.EweBJUn4YSXVo4cVGg9bvQFRhT77QfB2-p9JrVPXiU4

###For Get Eligible Amount
POST http://policy-lbalancer-1038866339.ap-south-1.elb.amazonaws.com/policy/getEligibleAmount/P1001/M101
Content-Type: application/json
Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJrYXZlcmkiLCJleHAiOjE2NDY3NTUxMDYsImlhdCI6MTY0Njc1MzMwNn0.EweBJUn4YSXVo4cVGg9bvQFRhT77QfB2-p9JrVPXiU4
