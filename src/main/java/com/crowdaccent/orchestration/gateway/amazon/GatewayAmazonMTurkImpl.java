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
	
	public HITResponse createBasicFreeTextHIT(HITRequest hRequest) {
		
		HIT hit = this.createBasicFreeTextTask(hRequest);
		HITResponse response = new HITResponse();
		response.setSyncResponse(hit);
		return response;
	}

	public HITResponse createComplexFreeTextHIT(HITRequest hRequest) {
		
		HIT hit = this.createComplexFreeTextTask(hRequest);
		HITResponse response = new HITResponse();
		response.setSyncResponse(hit);
		return response;
	}

	public HITResponse createIntroductionHIT(HITRequest hRequest) {
        
        HIT hit = this.createIntroductionTask(hRequest);
        HITResponse response = new HITResponse();
        response.setSyncResponse(hit);
        return response;
    }

	public HITResponse createIntroductionHITWithImage(HITRequest hRequest) {
        
        HIT hit = this.createIntroductionTaskWithImage(hRequest);
        HITResponse response = new HITResponse();
        response.setSyncResponse(hit);
        return response;
    }

	/**
	 * 
	 * @param hRequest
	 * @return
	 */
	private HIT createBasicFreeTextTask(HITRequest hRequest) {

		return this.service.createBasicFreeTextHIT(hRequest.getHITTypeId(), // HITTypeId 
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

	private HIT createComplexFreeTextTask(HITRequest hRequest) {

		return this.service.createComplexFreeTextHIT(hRequest.getHITTypeId(), // HITTypeId 
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
	            hRequest.getResponseGroup(),  // responseGroup
	            hRequest.getDisplayName(),
				hRequest.getListItems()
	          );		
	}

	private HIT createIntroductionTask(HITRequest hRequest) {

        return this.service.createIntroductionHIT(hRequest.getHITTypeId(), // HITTypeId 
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
            hRequest.getResponseGroup(),  // responseGroup
            hRequest.getDisplayName(),
            hRequest.getListItems(),
            hRequest.getOverviewContent(),
            hRequest.getQuestionContent()
          );        
	}

	private HIT createIntroductionTaskWithImage(HITRequest hRequest) {

        return this.service.createIntroductionHITWithImage(hRequest.getHITTypeId(), // HITTypeId 
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
        hRequest.getResponseGroup(),  // responseGroup
        hRequest.getDisplayName(),
        hRequest.getListItems(),
        hRequest.getOverviewContent(),
        hRequest.getQuestionContent()
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
