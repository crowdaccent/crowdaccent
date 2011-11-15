package com.crowdaccent.orchestration.gateway.amazon;

import com.amazonaws.mturk.util.PropertiesClientConfig;
import com.amazonaws.mturk.requester.Assignment;
import com.amazonaws.mturk.requester.AssignmentStatus;
import com.amazonaws.mturk.requester.GetAssignmentsForHITResult;
import com.amazonaws.mturk.requester.GetAssignmentsForHITSortProperty;
import com.amazonaws.mturk.requester.GetReviewableHITsResult;
import com.amazonaws.mturk.requester.GetReviewableHITsSortProperty;
import com.amazonaws.mturk.requester.HIT;
import com.amazonaws.mturk.requester.ReviewableHITStatus;
import com.amazonaws.mturk.requester.SortDirection;

import com.crowdaccent.orchestration.Requester;
import com.crowdaccent.orchestration.gateway.Gateway;
import com.crowdaccent.orchestration.gateway.HITRequest;
import com.crowdaccent.orchestration.gateway.HITResponse;

public class GatewayAmazonMTurkImpl implements Gateway {

	private Requester service;
	
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

    public GetReviewableHITsResult getReviewableHITsWithCreationTimeOrderAndPageDetails(String hitTypeId, Integer pageNum, Integer pageSize) {
        
        return this.getReviewableHITsWithCreationSortOrderAndPageDetails(hitTypeId, pageNum, pageSize);
    }
    
    public HIT[] getReviewableHITsDetailsWithCreationTimeSortOrderAndPageDetails(String hitTypeId, Integer pageNum, Integer pageSize) {
   
        return this.getReviewableHITsDetailsWithCreationSortOrderAndPageDetails(hitTypeId, pageNum, pageSize);
    }

    public GetAssignmentsForHITResult getAllAssignmentsHITResults(String hitId, Integer pageNumber, Integer pageSize, boolean getFullResponse) {
        
        return this.getAssignmentsForHITWithResponseGroup(hitId, pageNumber, pageSize, getFullResponse);
    }

    public Assignment[] getAllAssignmentsForHIT(String hitId, Integer pageNumber, Integer pageSize, boolean getFullResponse) {
        
        return this.getAssignmentsResultsForHITWithResponseGroup(hitId, pageNumber, pageSize, getFullResponse);
    }

    public GetAssignmentsForHITResult getSubmittedAssignmentsForHITResults(String hitId, Integer pageNumber, Integer pageSize, boolean getFullResponse) { 
        
        return this.getAssignmentsResultsForSubmittedHITWithResponseGroup(hitId, pageNumber, pageSize, getFullResponse);
    }
    
    public Assignment[] getSubmittedAssignmentsResultsForHIT(String hitId, Integer pageNumber, Integer pageSize, boolean getFullResponse) { 
        
        return this.getSubmittedAssignmentsResultsForHITWithResponseGroup(hitId, pageNumber, pageSize, getFullResponse);
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
   
    private GetReviewableHITsResult getReviewableHITsWithCreationSortOrderAndPageDetails(String hitTypeId, Integer pageNum, Integer pageSize) {
         
        return this.service.getReviewableHITs(hitTypeId, // HITTypeId
            ReviewableHITStatus.Reviewable,
            SortDirection.Ascending,
            GetReviewableHITsSortProperty.CreationTime,
            pageNum,
            pageSize
        );
    }   

    private HIT[] getReviewableHITsDetailsWithCreationSortOrderAndPageDetails(String hitTypeId, Integer pageNum, Integer pageSize) {
        
        GetReviewableHITsResult result = this.getReviewableHITsWithCreationSortOrderAndPageDetails(hitTypeId, pageNum, pageSize);
        return result.getHIT();
    } 
    
    private GetAssignmentsForHITResult getAssignmentsForHITWithResponseGroup(String hitId, Integer pageNumber, Integer pageSize, 
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
        return this.service.getAssignmentsForHIT(hitId, SortDirection.Ascending, DEFAULT_ASSIGNMENT_STATUS, GetAssignmentsForHITSortProperty.AcceptTime, 
                pageNumber, pageSize, responseGroup);
    }
    
    private Assignment[] getAssignmentsResultsForHITWithResponseGroup(String hitId, Integer pageNumber, Integer pageSize, boolean getFullResponse) {
    
        GetAssignmentsForHITResult result = this.getAssignmentsForHITWithResponseGroup(hitId, pageNumber, pageSize, getFullResponse);
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
        return this.service.getAssignmentsForHIT(hitId, SortDirection.Ascending, SUBMITTED_ASSIGNMENT_STATUS, GetAssignmentsForHITSortProperty.AcceptTime, 
                pageNumber, pageSize, responseGroup);
    }
    
    private Assignment[] getSubmittedAssignmentsResultsForHITWithResponseGroup(String hitId, Integer pageNumber, Integer pageSize, boolean getFullResponse) {
    
        GetAssignmentsForHITResult result = this.getAssignmentsResultsForSubmittedHITWithResponseGroup(hitId, pageNumber, pageSize, getFullResponse);
        return result.getAssignment();
    }   
    
       
    /* (non-Javadoc)
     * @see com.crowdaccent.orchestration.gateway.Gateway#getWebsiteURL()
     */
    @Override
    public String getWebsiteURL() {
    	return this.service.getWebsiteURL();
    }
}
