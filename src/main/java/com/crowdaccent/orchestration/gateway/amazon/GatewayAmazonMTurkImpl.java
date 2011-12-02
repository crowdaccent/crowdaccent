package com.crowdaccent.orchestration.gateway.amazon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.amazonaws.mturk.addon.HITDataInput;
import com.amazonaws.mturk.addon.HITProperties;
import com.amazonaws.mturk.addon.HITQuestion;
import com.amazonaws.mturk.requester.Assignment;
import com.amazonaws.mturk.requester.AssignmentStatus;
import com.amazonaws.mturk.requester.EventType;
import com.amazonaws.mturk.requester.GetAssignmentsForHITResult;
import com.amazonaws.mturk.requester.GetAssignmentsForHITSortProperty;
import com.amazonaws.mturk.requester.GetReviewableHITsResult;
import com.amazonaws.mturk.requester.GetReviewableHITsSortProperty;
import com.amazonaws.mturk.requester.HIT;
import com.amazonaws.mturk.requester.NotificationSpecification;
import com.amazonaws.mturk.requester.NotificationTransport;
import com.amazonaws.mturk.requester.ReviewableHITStatus;
import com.amazonaws.mturk.requester.SortDirection;
import com.amazonaws.mturk.service.exception.ServiceException;
import com.crowdaccent.config.AppConfiguration;
import com.crowdaccent.orchestration.Requester;
import com.crowdaccent.orchestration.gateway.Gateway;
import com.crowdaccent.orchestration.gateway.HITRequest;

/**
 * 
 * @author mkutare
 *
 */
@Component
public class GatewayAmazonMTurkImpl implements Gateway {

	private @Autowired Requester service;
	private @Autowired AppConfiguration appConfiguration;
	public static final int DEFAULT_PAGE_NUM = 1;
	public static final int DEFAULT_PAGE_SIZE = 10;
	public static final SortDirection DEFAULT_SORT_DIRECTION = SortDirection.Ascending;
	private static final String[] DEFAULT_ASSIGNMENT_RESPONSE_GROUP = new String [] { 
      "Minimal", 
      "AssignmentFeedback" 
    };

	private static final AssignmentStatus[] DEFAULT_ASSIGNMENT_STATUS = new AssignmentStatus[] { 
      AssignmentStatus.Approved, 
      AssignmentStatus.Rejected,
      AssignmentStatus.Submitted
    };

    private static final AssignmentStatus[] SUBMITTED_ASSIGNMENT_STATUS = new AssignmentStatus[] { 
      AssignmentStatus.Submitted
    };
	
	public HIT createBasicFreeTextHIT(HITRequest hRequest) {
    	
    	return this.createBasicFreeTextTask(hRequest);
    }
    
    public HIT createComplexFreeTextHIT(HITRequest hRequest) {
    	
    	return this.createComplexFreeTextTask(hRequest);
    }
    
    public HIT createIntroductionHIT(HITRequest hRequest) {
        
        return this.createIntroductionTask(hRequest);
    }
    
    public HIT createIntroductionHITWithImage(HITRequest hRequest) {
        
        return this.createIntroductionTaskWithImage(hRequest);
    }

    public GetReviewableHITsResult getReviewableHITsWithCreationTimeOrderAndPageDetails(String hitTypeId, Integer pageNumber) {
        
        return this.getReviewableHITsWithCreationSortOrderAndPageDetails(hitTypeId, pageNumber);
    }
    
    public HIT[] getReviewableHITsDetailsWithCreationTimeSortOrderAndPageDetails(String hitTypeId, Integer pageNumber) {
   
        return this.getReviewableHITsDetailsWithCreationSortOrderAndPageDetails(hitTypeId, pageNumber);
    }

    
    public HIT[] getAllReviewableHITs(String hitTypeId) {

        List<HIT> results = new ArrayList<HIT>();
        int pageNumber = 1;
        do {
            
            HIT[] hit = this.getReviewableHITsDetailsWithCreationTimeSortOrderAndPageDetails(hitTypeId, pageNumber);
            if (hit != null) {
                // Add the results
                Collections.addAll(results, hit);
            }
            // Check if we're on the last page or not
            if (hit == null || hit.length < DEFAULT_PAGE_SIZE)
                break;

            pageNumber++;

        } while (true);

        return (HIT[]) results.toArray(new HIT[results.size()]);
    }
    
    public GetAssignmentsForHITResult getAllAssignmentsHITResults(String hitId, Integer pageNumber, boolean getFullResponse) {
        
        return this.getAssignmentsForHITWithResponseGroup(hitId, pageNumber, getFullResponse);
    }

    public Assignment[] getAllAssignmentsForHIT(String hitId, Integer pageNumber, boolean getFullResponse) {
        
        return this.getAssignmentsResultsForHITWithResponseGroup(hitId, pageNumber, getFullResponse);
    }
    
    public Assignment[] getAllAssignmentsForHIT(String hitId) throws ServiceException {      

        List<Assignment> results = new ArrayList<Assignment>();
        int pageNumber = 1;

        do {
          GetAssignmentsForHITResult result = this.getAllAssignmentsHITResults(hitId, pageNumber, true);
          Assignment[] assignment = result.getAssignment();

          if (assignment != null) {
            // Add the results
            Collections.addAll(results, assignment);
          }

          // Check if we're on the last page or not
          if (assignment == null || assignment.length < DEFAULT_PAGE_SIZE)
            break;

          pageNumber++;

        } while (true);
        return (Assignment[]) results.toArray(new Assignment[results.size()]);    
    }

    public GetAssignmentsForHITResult getSubmittedAssignmentsForHITResults(String hitId, Integer pageNumber, Integer pageSize, boolean getFullResponse) { 
        
        return this.getAssignmentsResultsForSubmittedHITWithResponseGroup(hitId, pageNumber, pageSize, getFullResponse);
    }
    
    public Assignment[] getSubmittedAssignmentsResultsForHIT(String hitId, Integer pageNumber, Integer pageSize, boolean getFullResponse) { 
        
        return this.getSubmittedAssignmentsResultsForHITWithResponseGroup(hitId, pageNumber, pageSize, getFullResponse);
    }

    public void notifyWorkers(String subject, String messageText, String[] workerId) {
        
        this.sendNotificationWorkers(subject, messageText, workerId);
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
   
    private GetReviewableHITsResult getReviewableHITsWithCreationSortOrderAndPageDetails(String hitTypeId, Integer pageNumber) {
         
        return this.service.getReviewableHITs(hitTypeId, // HITTypeId
            ReviewableHITStatus.Reviewable,
            SortDirection.Ascending,
            GetReviewableHITsSortProperty.CreationTime,
            pageNumber);
    }   

    private HIT[] getReviewableHITsDetailsWithCreationSortOrderAndPageDetails(String hitTypeId, Integer pageNumber) {
        
        GetReviewableHITsResult result = this.getReviewableHITsWithCreationSortOrderAndPageDetails(hitTypeId, pageNumber);
        return result.getHIT();
    } 
    
    private GetAssignmentsForHITResult getAssignmentsForHITWithResponseGroup(String hitId, Integer pageNumber,
            boolean getFullResponse) {
        
        // Include AssignmentFeedback in response
        String[] responseGroup = null;
        if (getFullResponse == true) {
          responseGroup = DEFAULT_ASSIGNMENT_RESPONSE_GROUP;
        } else {
            responseGroup = new String [] { 
                "Minimal"
            };
        }
        return this.service.getAssignmentsForHIT(hitId, SortDirection.Ascending, DEFAULT_ASSIGNMENT_STATUS, GetAssignmentsForHITSortProperty.SubmitTime, 
                pageNumber, DEFAULT_PAGE_NUM, responseGroup);
    }
    
    private Assignment[] getAssignmentsResultsForHITWithResponseGroup(String hitId, Integer pageNumber, boolean getFullResponse) {
    
        GetAssignmentsForHITResult result = this.getAssignmentsForHITWithResponseGroup(hitId, pageNumber, getFullResponse);
        return result.getAssignment();
    }   

    private GetAssignmentsForHITResult getAssignmentsResultsForSubmittedHITWithResponseGroup(String hitId, Integer pageNumber, Integer pageSize, 
            boolean getFullResponse) {
        
        // Include AssignmentFeedback in response
        String[] responseGroup = null;
        if (getFullResponse == true) {
          responseGroup = DEFAULT_ASSIGNMENT_RESPONSE_GROUP;
        } else {
            responseGroup = new String [] { 
                "Minimal"
            };
        }
        return this.service.getAssignmentsForHIT(hitId, SortDirection.Ascending, SUBMITTED_ASSIGNMENT_STATUS, GetAssignmentsForHITSortProperty.SubmitTime, 
                pageNumber, pageSize, responseGroup);
    }
    
    private Assignment[] getSubmittedAssignmentsResultsForHITWithResponseGroup(String hitId, Integer pageNumber, Integer pageSize, boolean getFullResponse) {
    
        GetAssignmentsForHITResult result = this.getAssignmentsResultsForSubmittedHITWithResponseGroup(hitId, pageNumber, pageSize, getFullResponse);
        return result.getAssignment();
    }   
    
    private void sendNotificationWorkers(String subject, String messageText, String[] workerId) {
        
        this.service.notifyWorkers(subject, messageText, workerId);
    }

    /* (non-Javadoc)
     * @see com.crowdaccent.orchestration.gateway.Gateway#getWebsiteURL()
     */
    @Override
    public String getWebsiteURL() {
    	return this.service.getWebsiteURL();
    }

	/* (non-Javadoc)
	 * @see com.crowdaccent.orchestration.gateway.Gateway#createIntroductionHITWithImage(com.amazonaws.mturk.addon.HITProperties, com.amazonaws.mturk.addon.HITDataInput, com.amazonaws.mturk.addon.HITQuestion)
	 */
	@Override
	public HIT createIntroductionHITWithImage(HITProperties hitProperties,
			HITDataInput hitDataInputReader, HITQuestion hitQuestion) {
		return this.service.createHIT(hitProperties, hitDataInputReader, hitQuestion);
	}

	/* (non-Javadoc)
	 * @see com.crowdaccent.orchestration.gateway.Gateway#setNotificationEmail(java.lang.String, java.lang.String)
	 */
	@Override
	public void setNotificationEmail(String hitTypeId, String destination) {
		Boolean active = true;
		String version= "2006-05-05";
		
		NotificationSpecification notification = new NotificationSpecification();
		notification.setTransport(NotificationTransport.Email);
		notification.setDestination(destination);
		notification.setVersion(version);
		List<EventType> events = new ArrayList<EventType>();
		events.add(EventType.HITReviewable);
		
		notification.setEventType(events.toArray(new EventType[0]));
		
		this.service.setHITTypeNotification(hitTypeId, notification, active);
	}

	/* (non-Javadoc)
	 * @see com.crowdaccent.orchestration.gateway.Gateway#setNotificationURL(java.lang.String, java.lang.String)
	 */
	@Override
	public void setNotificationURL(String hitTypeId, String destination) {
		Boolean active = true;
		
		NotificationSpecification notification = new NotificationSpecification();
		notification.setTransport(NotificationTransport.REST);
		notification.setDestination(destination);
		notification.setVersion(appConfiguration.getRESTVersion());
		List<EventType> events = new ArrayList<EventType>();
		events.add(EventType.HITReviewable);
		
		notification.setEventType(events.toArray(new EventType[0]));
		
		this.service.setHITTypeNotification(hitTypeId, notification, active);
		
	}

	/* (non-Javadoc)
	 * @see com.crowdaccent.orchestration.gateway.Gateway#getHIT(java.lang.String)
	 */
	@Override
	public HIT getHIT(String hit_id) {
		return this.service.getHIT(hit_id);
	}

	/**
	 * @param service the service to set
	 */
}
