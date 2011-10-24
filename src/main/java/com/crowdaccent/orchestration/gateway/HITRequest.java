package com.crowdaccent.orchestration.gateway;

import com.crowdaccent.orchestration.gateway.amazon.QualificationRequirement;
import com.crowdaccent.orchestration.gateway.amazon.Reward;

public class HITRequest {

	private String operation = null;
	private String title = null;
	private String description = null;
	private String question = null;
	private Reward reward = null;
	private int assignmentDurationInSecs = 10;
	private int lifeTimeInSeconds = 60; 
	private String keywords = "categorization";
	private int maxAssignments = 100;
	private int autoApprovalDelaySecs = 60;  
	private QualificationRequirement qualificationRequirement = null; 
	private String requestorAnnotation = "experimental";
	
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public Reward getReward() {
		return reward;
	}
	public void setReward(Reward reward) {
		this.reward = reward;
	}
	public int getAssignmentDurationInSecs() {
		return assignmentDurationInSecs;
	}
	public void setAssignmentDurationInSecs(int assignmentDurationInSecs) {
		this.assignmentDurationInSecs = assignmentDurationInSecs;
	}
	public int getLifeTimeInSeconds() {
		return lifeTimeInSeconds;
	}
	public void setLifeTimeInSeconds(int lifeTimeInSeconds) {
		this.lifeTimeInSeconds = lifeTimeInSeconds;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public int getMaxAssignments() {
		return maxAssignments;
	}
	public void setMaxAssignments(int maxAssignments) {
		this.maxAssignments = maxAssignments;
	}
	public int getAutoApprovalDelaySecs() {
		return autoApprovalDelaySecs;
	}
	public void setAutoApprovalDelaySecs(int autoApprovalDelaySecs) {
		this.autoApprovalDelaySecs = autoApprovalDelaySecs;
	}
	public QualificationRequirement getQualificationRequirement() {
		return qualificationRequirement;
	}
	public void setQualificationRequirement(
			QualificationRequirement qualificationRequirement) {
		this.qualificationRequirement = qualificationRequirement;
	}
	public String getRequestorAnnotation() {
		return requestorAnnotation;
	}
	public void setRequestorAnnotation(String requestorAnnotation) {
		this.requestorAnnotation = requestorAnnotation;
	}

}
