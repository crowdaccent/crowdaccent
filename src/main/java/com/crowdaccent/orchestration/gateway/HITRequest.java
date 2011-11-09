package com.crowdaccent.orchestration.gateway;

import java.util.List;

import com.amazonaws.mturk.requester.QualificationRequirement;

public class HITRequest {

	private String operation = null;
	private String hitTypeId = null;
	private String title = null;
	private String description = null;
	private String question = null;
	private Double reward = null;
	private Long assignmentDurationInSecs = 10L;
	private Long lifeTimeInSeconds = 60L; 
	private String keywords = null;
	private Integer maxAssignments = 100;
	private Long autoApprovalDelaySecs = 60L;  
	private QualificationRequirement[] qualificationRequirements = null; 
	private String requestorAnnotation = "experimental";
	private String[] responseGroup = null; 
	private String displayName = null;
	private String[] items = null;
	
	public String getHITTypeId() {
		return this.hitTypeId;
	}
	
	public void setHITTypeId(String hitTypeId) {
		this.hitTypeId = hitTypeId;
	}
	
	public String getOperation() {
		return this.operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	public String getTitle() {
		return this.title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getQuestion() {
		return this.question;
	}
	
	public void setQuestion(String question) {
		this.question = question;
	}
	
	public Double getReward() {
		return this.reward;
	}
	
	public void setReward(Double reward) {
		this.reward = reward;
	}
	
	public Long getAssignmentDurationInSecs() {
		return this.assignmentDurationInSecs;
	}
	
	public void setAssignmentDurationInSecs(Long assignmentDurationInSecs) {
		this.assignmentDurationInSecs = assignmentDurationInSecs;
	}
	
	public Long getLifeTimeInSeconds() {
		return this.lifeTimeInSeconds;
	}
	
	public void setLifeTimeInSeconds(Long lifeTimeInSeconds) {
		this.lifeTimeInSeconds = lifeTimeInSeconds;
	}
	
	public String getKeywords() {
		return this.keywords;
	}
	
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
	public Integer getMaxAssignments() {
		return this.maxAssignments;
	}
	
	public void setMaxAssignments(Integer maxAssignments) {
		this.maxAssignments = maxAssignments;
	}
	
	public Long getAutoApprovalDelaySecs() {
		return this.autoApprovalDelaySecs;
	}
	
	public void setAutoApprovalDelaySecs(Long autoApprovalDelaySecs) {
		this.autoApprovalDelaySecs = autoApprovalDelaySecs;
	}
	
	public QualificationRequirement[] getQualificationRequirement() {
		return this.qualificationRequirements;
	}
	public void setQualificationRequirement(
			QualificationRequirement[] qualificationRequirement) {
		this.qualificationRequirements = qualificationRequirement;
	}

	public String getRequestorAnnotation() {
		return this.requestorAnnotation;
	}
	public void setRequestorAnnotation(String requestorAnnotation) {
		this.requestorAnnotation = requestorAnnotation;
	}

	public String[] getResponseGroup() {
		return this.responseGroup;
	}

	public void setResponseGroup(String[] responseGroup) {
		this.responseGroup = responseGroup;
	}

	public String getDisplayName() {
		return this.displayName;
	}
	
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String[] getListItems() {
		return this.items;
	}
	
	public void setListItems(String[] items) {
		this.items = items;
	}
}
