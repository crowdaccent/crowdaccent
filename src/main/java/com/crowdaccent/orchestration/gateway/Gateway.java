package com.crowdaccent.orchestration.gateway;

import com.amazonaws.mturk.requester.Assignment;
import com.amazonaws.mturk.requester.GetAssignmentsForHITResult;
import com.amazonaws.mturk.requester.GetReviewableHITsResult;
import com.amazonaws.mturk.requester.HIT;

public interface Gateway {

	/**
	 * 
	 * @param hrequest
	 * @return
	 */
	public HITResponse createBasicFreeTextHIT(HITRequest hrequest);
	
	/**
	 * 
	 * @param hrequest
	 * @return
	 */
	public HITResponse createComplexFreeTextHIT(HITRequest hrequest);

	 /**
     * 
     * @param hrequest
     * @return
     */
    public HITResponse createIntroductionHIT(HITRequest hrequest);

    /** 
    * @param hrequest
    * @return
    */
   public HITResponse createIntroductionHITWithImage(HITRequest hrequest);
   
   /**
    * 
    * @param hitTypeId
    * @param pageNum
    * @param pageSize
    * @return
    */
   public GetReviewableHITsResult getReviewableHITsWithCreationTimeOrderAndPageDetails(String hitTypeId, Integer pageNum, Integer pageSize);
   
   /**
    * 
    * @param hitTypeId
    * @param pageNum
    * @param pageSize
    * @return
    */
   public HIT[] getReviewableHITsDetailsWithCreationTimeSortOrderAndPageDetails(String hitTypeId, Integer pageNum, Integer pageSize);

   /**
    * 
    * @param hitId
    * @param pageNumber
    * @param pageSize
    * @param getFullResponse
    * @return
    */
   public GetAssignmentsForHITResult getAllAssignmentsHITResults(String hitId, Integer pageNumber, Integer pageSize, boolean getFullResponse);

   /**
    * 
    * @param hitId
    * @param pageNumber
    * @param pageSize
    * @param getFullResponse
    * @return
    */
   public Assignment[] getAllAssignmentsForHIT(String hitId, Integer pageNumber, Integer pageSize, boolean getFullResponse);

   /**
    * 
    * @param hitId
    * @param pageNumber
    * @param pageSize
    * @param getFullResponse
    * @return
    */
   public GetAssignmentsForHITResult getSubmittedAssignmentsForHITResults(String hitId, Integer pageNumber, Integer pageSize, boolean getFullResponse);

   /**
    * 
    * @param hitId
    * @param pageNumber
    * @param pageSize
    * @param getFullResponse
    * @return
    */
   public Assignment[] getSubmittedAssignmentsResultsForHIT(String hitId, Integer pageNumber, Integer pageSize, boolean getFullResponse);

       
   /**
    * 
    * @param subject
    * @param messageText
    * @param workerId
    */
   public void notifyWorkers(String subject, String messageText, String[] workerId); 

    
	/** Returns websiteURL
	 * @return String website URL
	 */
	public String getWebsiteURL();
	
}
