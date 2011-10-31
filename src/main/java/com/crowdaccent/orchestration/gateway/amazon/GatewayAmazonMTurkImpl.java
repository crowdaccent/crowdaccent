package com.crowdaccent.orchestration.gateway.amazon;

import com.crowdaccent.orchestration.gateway.Gateway;

import com.crowdaccent.orchestration.gateway.HITRequest;
import com.crowdaccent.orchestration.gateway.HITResponse;

public class GatewayAmazonMTurkImpl implements Gateway {

	public HITResponse createHIT(HITRequest hRequest) {
		String operation = hRequest.getOperation();
		String title = hRequest.getTitle();
		String description = hRequest.getDescription();
		String question = hRequest.getQuestion();
		Reward reward = hRequest.getReward();
		int assignmentDurationInSecs = hRequest.getAssignmentDurationInSecs();
		int lifeTimeInSeconds = hRequest.getLifeTimeInSeconds(); 
		String keywords = hRequest.getKeywords();
		int maxAssignments = hRequest.getMaxAssignments();
		int autoApprovalDelaySecs = hRequest.getAutoApprovalDelaySecs();  
		QualificationRequirement qualificationRequirement = hRequest.getQualificationRequirement(); 
		String requestorAnnotation = hRequest.getRequestorAnnotation();
		
		String ret = createHIT(operation, title, description, question, reward, assignmentDurationInSecs, lifeTimeInSeconds, keywords, maxAssignments, autoApprovalDelaySecs,  qualificationRequirement, requestorAnnotation);
		// TODO Auto-generated method stub
		HITResponse resp = new HITResponse();
		resp.setAsyncResp(ret);
		return resp;
	}

	/**
	 * 
	 * @param operation
	 * @param hitTypeId
	 * @param question
	 * @param lifeTimeInSeconds
	 * @param maxAssignments
	 * @param requesterAnnotation
	 * @return
	 */
	private String createHIT(String operation, String hitTypeId, String question, int lifeTimeInSeconds, int maxAssignments, String requesterAnnotation ) {
		
		return null;
	}

	/**
	 * 
	 * @param operation
	 * @param title
	 * @param description
	 * @param question
	 * @param reward
	 * @param assignmentDurationInSecs
	 * @param lifeTimeInSeconds
	 * @param keywords
	 * @param maxAssignments
	 * @param autoApprovalDelaySecs
	 * @param qualificationRequirement
	 * @param requestorAnnotation
	 * @return
	 */
	private String createHIT(String operation, String title, String description, String question, Reward reward, int assignmentDurationInSecs, int lifeTimeInSeconds, String keywords, int maxAssignments, int autoApprovalDelaySecs,  QualificationRequirement qualificationRequirement, String requestorAnnotation) {
		
		return null;
	}
	
}
