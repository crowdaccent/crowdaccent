package com.crowdaccent.orchestration;

import java.util.List;

import com.amazonaws.mturk.requester.HIT;
import com.amazonaws.mturk.requester.QualificationRequirement;
import com.amazonaws.mturk.service.axis.RequesterServiceRaw;
import com.amazonaws.mturk.service.exception.ServiceException;
import com.amazonaws.mturk.util.ClientConfig;

public class Requester extends RequesterServiceRaw {

	public Requester(ClientConfig config) {
		super(config);
	}
	
	public HIT createBasicFreeTextHIT(String hitTypeId, String title, String description, String keywords, String question, Double reward, 
			Long assignmentDurationInSeconds, Long autoApprovalDelayInSeconds, Long lifetimeInSeconds, Integer maxAssignments, 
			String requesterAnnotation, QualificationRequirement[] qualificationRequirements, String[] responseGroup)
					throws ServiceException {		
		
		return super.createHIT(hitTypeId, // HITTypeId 
	    		title, 
	    		description, 
	    		keywords, // keywords 
	    		this.getBasicFreeTextQuestion(question), 
	    		reward, 
	            assignmentDurationInSeconds, 
	            autoApprovalDelayInSeconds, 
	            lifetimeInSeconds, 
	            maxAssignments, 
	            requesterAnnotation, // requesterAnnotation 
	            qualificationRequirements,
	            responseGroup  // responseGroup
	          );
		
	}

	public HIT createComplexFreeTextHIT(String hitTypeId, String title, String description, String keywords, String question, Double reward, 
			Long assignmentDurationInSeconds, Long autoApprovalDelayInSeconds, Long lifetimeInSeconds, Integer maxAssignments, 
			String requesterAnnotation, QualificationRequirement[] qualificationRequirements, String[] responseGroup,
			String displayName, String[] items)
					throws ServiceException {		
		return super.createHIT(hitTypeId, // HITTypeId 
	    		title, 
	    		description, 
	    		keywords, // keywords 
	    		this.getComplexFreeTextQuestion(question, displayName, title, items), 
	    		reward, 
	            assignmentDurationInSeconds, 
	            autoApprovalDelayInSeconds, 
	            lifetimeInSeconds, 
	            maxAssignments, 
	            requesterAnnotation, // requesterAnnotation 
	            qualificationRequirements,
	            responseGroup  // responseGroup
	          );
		
	}
	public String getBasicFreeTextQuestion(String question) {
		String q = "";
		q += "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		q += "<QuestionForm xmlns=\"http://mechanicalturk.amazonaws.com/AWSMechanicalTurkDataSchemas/2005-10-01/QuestionForm.xsd\">";
		q += "  <Question>";
		q += "    <QuestionIdentifier>1</QuestionIdentifier>";
		q += "    <QuestionContent>";
		q += "      <Text>" + question + "</Text>";
		q += "    </QuestionContent>";
		q += "    <AnswerSpecification>";
		q += "      <FreeTextAnswer/>";
		q += "    </AnswerSpecification>";
		q += "  </Question>";
		q += "</QuestionForm>";
		return q;
	}

	public String getComplexFreeTextQuestion(String question, String displayName, String title, String[] items) {
		String q = "";
		q += "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		q += "<QuestionForm xmlns=\"http://mechanicalturk.amazonaws.com/AWSMechanicalTurkDataSchemas/2005-10-01/QuestionForm.xsd\">";
		q += "  <Question>"; 
		q += "    <QuestionIdentifier>1</QuestionIdentifier>";
		q += "    <DisplayName> " + displayName + "</DisplayName>";
		q += "    <IsRequired>1</IsRequired>";
		q += "    <QuestionContent>";
		q += "      <Title>" + title + "</Title>";
		q += "      <Text>" + question + "</Text>";
		q += "      <List>";
		for (int i = 0; i < items.length; i++) {
	        q += "      <ListItem>"+ items[i]+ "</ListItem>";
		} 
		q += "      </List>";
		q += "    </QuestionContent>"; 
		q += "    <AnswerSpecification>";
		q += "      <FreeTextAnswer/>";
		q += "    </AnswerSpecification>"; 
		q += "  </Question>";
		q += "</QuestionForm>";
		return q;
	}

	public String getWebsiteURL() {
		// TODO Auto-generated method stub
	    return this.config.getWorkerWebsiteURL();
	}
	
}
