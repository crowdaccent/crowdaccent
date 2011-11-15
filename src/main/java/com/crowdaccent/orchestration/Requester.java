package com.crowdaccent.orchestration;

import org.apache.axis.utils.XMLUtils;

import com.amazonaws.mturk.requester.AssignmentStatus;
import com.amazonaws.mturk.requester.GetAssignmentsForHITResult;
import com.amazonaws.mturk.requester.GetAssignmentsForHITSortProperty;
import com.amazonaws.mturk.requester.GetReviewableHITsResult;
import com.amazonaws.mturk.requester.GetReviewableHITsSortProperty;
import com.amazonaws.mturk.requester.HIT;
import com.amazonaws.mturk.requester.QualificationRequirement;
import com.amazonaws.mturk.requester.QualificationType;
import com.amazonaws.mturk.requester.QualificationTypeStatus;
import com.amazonaws.mturk.requester.ReviewableHITStatus;
import com.amazonaws.mturk.requester.SortDirection;
import com.amazonaws.mturk.service.axis.RequesterServiceRaw;
import com.amazonaws.mturk.service.exception.ServiceException;
import com.amazonaws.mturk.util.ClientConfig;
import com.crowdaccent.orchestration.gateway.amazon.Overview;
import com.crowdaccent.orchestration.gateway.amazon.Question;

public class Requester extends RequesterServiceRaw {

    //-------------------------------------------------------------
    // Constants
    //-------------------------------------------------------------

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

	public HIT createIntroductionHIT(String hitTypeId, String title, String description, String keywords, String question, Double reward, 
            Long assignmentDurationInSeconds, Long autoApprovalDelayInSeconds, Long lifetimeInSeconds, Integer maxAssignments, 
            String requesterAnnotation, QualificationRequirement[] qualificationRequirements, String[] responseGroup,
            String displayName, String[] items, Overview oContent, Question[] qContent)
                    throws ServiceException {       
        return super.createHIT(hitTypeId, // HITTypeId 
            title, 
            description, 
            keywords, // keywords 
            this.getIntroductionHITQuestion(oContent, qContent), 
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

	public HIT createIntroductionHITWithImage(String hitTypeId, String title, String description, String keywords, String question, Double reward, 
            Long assignmentDurationInSeconds, Long autoApprovalDelayInSeconds, Long lifetimeInSeconds, Integer maxAssignments, 
            String requesterAnnotation, QualificationRequirement[] qualificationRequirements, String[] responseGroup,
            String displayName, String[] items, Overview oContent, Question[] qContent)
                    throws ServiceException {       
        return super.createHIT(hitTypeId, // HITTypeId 
            title, 
            description, 
            keywords, // keywords 
            this.getIntroductionHITQuestionWithImage(oContent, qContent), 
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

    public GetReviewableHITsResult getReviewableHITs(String hitTypeId, ReviewableHITStatus status, 
            SortDirection sortDirection, GetReviewableHITsSortProperty sortProperty, 
            Integer pageNumber, Integer pageSize)
          throws ServiceException { 
        return super.getReviewableHITs(hitTypeId, status, sortDirection, sortProperty, pageNumber, pageSize);
    }

    public GetAssignmentsForHITResult getAssignmentsForHIT(String hitId, SortDirection sortDirection, AssignmentStatus[] status, 
            GetAssignmentsForHITSortProperty sortProperty, Integer pageNumber, Integer pageSize, String[] responseGroup)
          throws ServiceException { 
        return super.getAssignmentsForHIT(hitId, sortDirection, status, sortProperty, pageNumber, pageSize, responseGroup);
    }
    
	public QualificationType createQualificationType(String name, String keywords, String description,
      QualificationTypeStatus status, Long retryDelayInSeconds, String test, String answerKey,
      Long testDurationInSeconds, Boolean autoGranted, Integer autoGrantedValue) 
    throws ServiceException { 
    
    return super.createQualificationType(name, 
        keywords, 
        description, 
        status, 
        retryDelayInSeconds, 
        test, 
        answerKey, 
        testDurationInSeconds, 
        autoGranted, 
        autoGrantedValue);
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

    public String getIntroductionHITQuestion(Overview oContent, Question[] qContent) {
        String q = "";
        q += "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
        q += "<QuestionForm xmlns=\"http://mechanicalturk.amazonaws.com/AWSMechanicalTurkDataSchemas/2005-10-01/QuestionForm.xsd\">";
        q += "  <Overview>";
        q += "      <Title>" + oContent.getTitle() + "</Title>"; //Product Image and Category Validation
        q += "      <Text>" + oContent.getQuestion() + "</Text>"; //Instructions:
        q += "      <List>";
        for (int i = 0; i < oContent.getInstructions().length; i++) {
            q += "      <ListItem>"+  oContent.getInstructions()[i]+ "</ListItem>";
        } 
        q += "      </List>";        
        q += "  </Overview>"; 
        q += "  <Question>"; 
        q += "    <QuestionIdentifier>"+ qContent[0].getQuestionId()+"</QuestionIdentifier>";
        q += "    <DisplayName> " + qContent[0].getDisplayName() + "</DisplayName>";
        q += "    <IsRequired>" + qContent[0].isRequired() + "</IsRequired>";
        q += "    <QuestionContent>";
        q += "      <Title>" + qContent[0].getTitle() + "</Title>";
        q += "      <Text>" + qContent[0].getQuestion() + "</Text>";
        q += "    </QuestionContent>"; 
        q += "    <AnswerSpecification>";
        q += "      <SelectionAnswer>";
        q += "      <MinSelectionCount>3</MinSelectionCount>";
        q += "      <StyleSuggestion>checkbox</StyleSuggestion>";
        q += "      <Selections>";
        q += "          <Selection>";
        q += "              <SelectionIdentifier>1</SelectionIdentifier>";
        q += "              <Text>"+qContent[0].getItems()[0]+"</Text>";
        q += "          </Selection>";
        q += "          <Selection>";
        q += "              <SelectionIdentifier>2</SelectionIdentifier>";
        q += "              <Text>"+qContent[0].getItems()[1]+"</Text>";
        q += "          </Selection>";
        q += "          <Selection>";
        q += "              <SelectionIdentifier>3</SelectionIdentifier>";
        q += "              <Text>"+qContent[0].getItems()[2]+"</Text>";
        q += "          </Selection>";       
        q += "      </Selections>";
        q += "      </SelectionAnswer>";
        q += "    </AnswerSpecification>"; 
        q += "  </Question>";
        q += "  <Question>"; 
        q += "    <QuestionIdentifier>"+ qContent[1].getQuestionId()+"</QuestionIdentifier>";
        q += "    <DisplayName> " + qContent[1].getDisplayName() + "</DisplayName>";
        q += "    <IsRequired>" + qContent[1].isRequired() + "</IsRequired>";
        q += "    <QuestionContent>";
        q += "      <Title>" + qContent[1].getTitle() + "</Title>";
        q += "      <Text>" + qContent[1].getQuestion() + "</Text>";
        q += "    </QuestionContent>"; 
        q += "    <AnswerSpecification>";
        q += "      <SelectionAnswer>";
        q += "      <MinSelectionCount>1</MinSelectionCount>";
        q += "      <StyleSuggestion>radiobutton</StyleSuggestion>";
        q += "      <Selections>";
        
        for (int i = 0; i < qContent[1].getItems().length; i++){
        	q += "          <Selection>";
        	q += "              <SelectionIdentifier>Level"+ i +"</SelectionIdentifier>";
        	q += "              <Text>"+ XMLUtils.xmlEncodeString(qContent[1].getItems()[i])+"</Text>";
        	q += "          </Selection>";
        }
        
        q += "      </Selections>";
        q += "      </SelectionAnswer>";
        q += "    </AnswerSpecification>"; 
        q += "  </Question>";
        q += "  <Question>";
        q += "    <QuestionIdentifier>"+ qContent[2].getQuestionId()+ "</QuestionIdentifier>";
        q += "    <QuestionContent>";
        q += "      <Text>" + qContent[2].getQuestion() + "</Text>";
        q += "    </QuestionContent>";
        q += "    <AnswerSpecification>";
        q += "      <FreeTextAnswer/>";
        q += "    </AnswerSpecification>";
        q += "  </Question>";
        q += "</QuestionForm>";
        return q;
    }

    public String getIntroductionHITQuestionWithImage(Overview oContent, Question[] qContent) {
        String q = "";
        q += "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
        q += "<QuestionForm xmlns=\"http://mechanicalturk.amazonaws.com/AWSMechanicalTurkDataSchemas/2005-10-01/QuestionForm.xsd\">";
        q += "  <Overview>";
        q += "      <Title>" + oContent.getTitle() + "</Title>"; //Product Image and Category Validation
        q += "      <Text>" + oContent.getInformation()[0] + "</Text>"; //Image URL:
        q += "      <EmbeddedBinary>";
        q += "          <EmbeddedMimeType>";
        q += "              <Type>" + "image" + "</Type>"; //Image :
        q += "              <SubType>" + "gif" + "</SubType>"; //Image :
        q += "          </EmbeddedMimeType>";
        q += "          <DataURL>" + oContent.getInformation()[1] + "</DataURL>"; //Data URL
        q += "          <AltText>" + oContent.getInformation()[2] + "</AltText>"; //Text
        q += "          <Width>" + oContent.getInformation()[3] + "</Width>"; //300
        q += "          <Height>" + oContent.getInformation()[4] + "</Height>"; //400
        q += "          <ApplicationParameter>";
        q += "              <Name>" + oContent.getInformation()[5] + "</Name>"; //image-id
        q += "              <Value>" + oContent.getInformation()[6] + "</Value>"; //1
        q += "          </ApplicationParameter>";
        q += "      </EmbeddedBinary>";
        q += "      <Text>" + "Title :" + oContent.getInformation()[7] + "</Text>"; //Title:
        q += "      <Text>" + "Description :" + oContent.getInformation()[8] + "</Text>"; //Description:
        q += "  </Overview>"; 
        q += "  <Question>"; 
        q += "    <QuestionIdentifier>"+ qContent[0].getQuestionId()+"</QuestionIdentifier>";
        q += "    <DisplayName> " + qContent[0].getDisplayName() + "</DisplayName>";
        q += "    <IsRequired>" + qContent[0].isRequired() + "</IsRequired>";
        q += "    <QuestionContent>";
        q += "      <Text>" + qContent[0].getQuestion() + "</Text>";
        q += "    </QuestionContent>"; 
        q += "    <AnswerSpecification>";
        q += "      <SelectionAnswer>";
        q += "      <MinSelectionCount>2</MinSelectionCount>";
        q += "      <StyleSuggestion>checkbox</StyleSuggestion>";
        q += "      <Selections>";
        q += "          <Selection>";
        q += "              <SelectionIdentifier>1</SelectionIdentifier>";
        q += "              <Text>"+qContent[0].getItems()[0]+"</Text>";
        q += "          </Selection>";
        q += "          <Selection>";
        q += "              <SelectionIdentifier>2</SelectionIdentifier>";
        q += "              <Text>"+qContent[0].getItems()[1]+"</Text>";
        q += "          </Selection>";
        q += "      </Selections>";
        q += "      </SelectionAnswer>";
        q += "    </AnswerSpecification>"; 
        q += "  </Question>";
        q += "  <Question>"; 
        q += "    <QuestionIdentifier>"+ qContent[1].getQuestionId()+"</QuestionIdentifier>";
        q += "    <DisplayName> " + qContent[1].getDisplayName() + "</DisplayName>";
        q += "    <IsRequired>" + qContent[1].isRequired() + "</IsRequired>";
        q += "    <QuestionContent>";
        q += "      <Text>" + qContent[1].getQuestion() + "</Text>";
        q += "    </QuestionContent>"; 
        q += "    <AnswerSpecification>";
        q += "      <SelectionAnswer>";
        q += "      <MinSelectionCount>1</MinSelectionCount>";
        q += "      <StyleSuggestion>radiobutton</StyleSuggestion>";
        q += "      <Selections>";

        for (int i = 0; i < qContent[1].getItems().length; i++){
			q += "          <Selection>";
			q += "              <SelectionIdentifier>Level"+ i +"</SelectionIdentifier>";
			q += "              <Text>" + XMLUtils.xmlEncodeString(qContent[1].getItems()[i]) + "</Text>";
			q += "          </Selection>";
		}
        
        q += "      </Selections>";
        q += "      </SelectionAnswer>";
        q += "    </AnswerSpecification>"; 
        q += "  </Question>";
        q += "  <Question>";
        q += "    <QuestionIdentifier>"+ qContent[2].getQuestionId()+ "</QuestionIdentifier>";
        q += "    <QuestionContent>";
        q += "      <Text>" + qContent[2].getQuestion() + "</Text>";
        q += "    </QuestionContent>";
        q += "    <AnswerSpecification>";
        q += "      <FreeTextAnswer/>";
        q += "    </AnswerSpecification>";
        q += "  </Question>";
        q += "</QuestionForm>";
        return q;
    }

	public String getWebsiteURL() {
	    return this.config.getWorkerWebsiteURL();
	}
	
}
