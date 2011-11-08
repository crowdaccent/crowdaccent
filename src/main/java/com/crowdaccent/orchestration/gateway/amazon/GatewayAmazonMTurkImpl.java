package com.crowdaccent.orchestration.gateway.amazon;

import com.amazonaws.mturk.util.PropertiesClientConfig;
import com.amazonaws.mturk.requester.HIT;

import com.crowdaccent.orchestration.Requester;
import com.crowdaccent.orchestration.gateway.Gateway;
import com.crowdaccent.orchestration.gateway.HITRequest;
import com.crowdaccent.orchestration.gateway.HITResponse;

public class GatewayAmazonMTurkImpl implements Gateway {

	private Requester service;
	
	public GatewayAmazonMTurkImpl() {
		service = new Requester(new PropertiesClientConfig("props/mturk.properties"));
	}
	
	public HITResponse createProductCategorizationHIT(HITRequest hRequest) {
		
		HIT hit = this.createBasicFreeTextHIT(hRequest);
		HITResponse response = new HITResponse();
		response.setSyncResponse(hit);
		return response;
	}

	/**
	 * 
	 * @param hRequest
	 * @return
	 */
	private HIT createBasicFreeTextHIT(HITRequest hRequest) {

		return this.service.createHIT(hRequest.getHITTypeId(), // HITTypeId 
	    		hRequest.getTitle(), 
	    		hRequest.getDescription(), 
	    		hRequest.getKeywords(), // keywords 
	    		this.service.getBasicFreeTextQuestion(hRequest.getQuestion()), 
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

	/* (non-Javadoc)
	 * @see com.crowdaccent.orchestration.gateway.Gateway#getWebsiteURL()
	 */
	@Override
	public String getWebsiteURL() {
		return this.service.getWebsiteURL();
	}
}
