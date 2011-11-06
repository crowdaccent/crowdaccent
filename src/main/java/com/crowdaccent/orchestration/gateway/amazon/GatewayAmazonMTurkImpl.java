package com.crowdaccent.orchestration.gateway.amazon;

import com.amazonaws.mturk.service.axis.RequesterService;
import com.amazonaws.mturk.util.PropertiesClientConfig;
import com.amazonaws.mturk.requester.HIT;

import com.crowdaccent.orchestration.gateway.Gateway;
import com.crowdaccent.orchestration.gateway.HITRequest;
import com.crowdaccent.orchestration.gateway.HITResponse;

public class GatewayAmazonMTurkImpl implements Gateway {

	private RequesterService service;
	
	public GatewayAmazonMTurkImpl() {
		service = new RequesterService(new PropertiesClientConfig("properties/mturk.properties"));
	}
	
	public HITResponse createProductCategorizationHIT(HITRequest hRequest) {
		
		HIT hit = this.createHIT(hRequest);
		HITResponse response = new HITResponse();
		response.setSyncResponse(hit);
		return response;
	}

	/**
	 * 
	 * @param hRequest
	 * @return
	 */
	private HIT createHIT(HITRequest hRequest) {

		return service.createHIT(hRequest.getHITTypeId(), // HITTypeId 
	    		hRequest.getTitle(), 
	    		hRequest.getDescription(), 
	    		hRequest.getKeywords(), // keywords 
	            hRequest.getQuestion(), 
	            hRequest.getReward(), 
	            hRequest.getAssignmentDurationInSecs(), 
	            hRequest.getAutoApprovalDelaySecs(), 
	            hRequest.getLifeTimeInSeconds(), 
	            hRequest.getMaxAssignments(), 
	            hRequest.getRequestorAnnotation(), // requesterAnnotation 
	            hRequest.getQualificationRequirement(),
	            hRequest.getResponseGroup()  // responseGroup
	          );		
	}
}
