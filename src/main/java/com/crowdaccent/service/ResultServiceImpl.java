/**
 * 
 */
package com.crowdaccent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.amazonaws.mturk.requester.HIT;
import com.crowdaccent.entity.Assignment;
import com.crowdaccent.entity.Hit;
import com.crowdaccent.entity.Result;
import com.crowdaccent.orchestration.gateway.Gateway;
import com.crowdaccent.orchestration.gateway.amazon.GatewayAmazonMTurkImpl;
import com.crowdaccent.repository.AssignmentDAO;
import com.crowdaccent.repository.HitDAO;
import com.crowdaccent.repository.ResultDAO;

/**
 * @author mkutare
 *
 */
@Service
public class ResultServiceImpl implements ResultService {
	private ResultDAO resultDAO;
	private HitDAO hitDAO;
	private AssignmentDAO assignmentDAO;
	
	/* (non-Javadoc)
	 * @see com.crowdaccent.service.ResultService#save(com.crowdaccent.entity.Result)
	 */
	@Override
	public void save(Result result) {
	    resultDAO.save(result);
	}

	/* (non-Javadoc)
	 * @see com.crowdaccent.service.ResultService#delete(com.crowdaccent.entity.Result)
	 */
	@Override
	public void delete(Result result) {
	    resultDAO.delete(result);
	}

	/* (non-Javadoc)
	 * @see com.crowdaccent.service.ResultService#getById(java.lang.String)
	 */
	@Override
	public Result getById(String id) {
		return resultDAO.getById(id);
	}
	
	/* (non-Javadoc)
	 * @see com.crowdaccent.service.ResultService#getAll()
	 */
	@Override
	public List<Result> getAll() {
		return resultDAO.getAll();
	}

	/* (non-Javadoc)
	 * @see com.crowdaccent.service.ResultService#getNumResults(int)
	 */
	@Override
	public List<Result> getNumResults(int number) {
		return resultDAO.getNumResults(number);
	}

	/* (non-Javadoc)
	 * @see com.crowdaccent.service.ResultService#getResultForHIT(java.lang.String)
	 */
	@Override
	public Result getResultsForHIT(String hit_id) {
	    Gateway gw = new GatewayAmazonMTurkImpl();
	    Result results = null;
		Hit phit = hitDAO.getByHitId(hit_id);
        
		HIT[] dhit = gw.getAllReviewableHITs(phit.getHit_type_id());
		//TODO - All the hits that are reviewable and those that are already in the database are overlapping set.
		//TODO - Take the difference of the once we got now and those that we have in db before updating the database
		for (int i = 0; i < dhit.length; i++) {
		    Hit rhit = new Hit();
	        results = new Result();
		    rhit.setHit_id(dhit[i].getHITId());
            results.setHitID(dhit[i].getHITId());

		    rhit.setHit_type_id(dhit[i].getHITTypeId());
	        results.setHit_type_id(dhit[i].getHITTypeId());
		    
		    if(dhit[i].getHITStatus() != null) {
		        rhit.setHit_status(dhit[i].getHITStatus().getValue());
		        results.setHit_status(dhit[i].getHITStatus().getValue());
		    }
		    
		    if(dhit[i].getCreationTime() != null) {
		        rhit.setCreation_time(dhit[i].getCreationTime().getTime());
                results.setCreation_time(dhit[i].getCreationTime().getTime());
		    }
		    
		    rhit.setTitle(dhit[i].getTitle());
            results.setTitle(dhit[i].getTitle());
		    rhit.setDescription(dhit[i].getDescription());
            results.setDescription(dhit[i].getDescription());
		    rhit.setKeywords(dhit[i].getTitle());
            results.setKeywords(dhit[i].getKeywords());

		    rhit.setMax_assignments(dhit[i].getMaxAssignments());
            results.setMax_assignments(dhit[i].getMaxAssignments());

		    rhit.setNum_similar_hits(dhit[i].getNumberOfSimilarHITs());
            results.setNum_similar_hits(dhit[i].getNumberOfSimilarHITs());

		    if(dhit[i].getReward() != null) {
		        rhit.setReward(dhit[i].getReward().getAmount().doubleValue());
	            results.setReward(dhit[i].getReward().getAmount().doubleValue());
		    }
		    
		    if(dhit[i].getAutoApprovalDelayInSeconds() != null) {
		        rhit.setAuto_approval_delay_in_secs(dhit[i].getAutoApprovalDelayInSeconds().intValue());
	            results.setAuto_approval_delay_in_secs(dhit[i].getAutoApprovalDelayInSeconds().intValue());
		    }
		    if(dhit[i].getHITReviewStatus() != null) {
		        rhit.setHit_review_status(dhit[i].getHITReviewStatus().getValue());
	            results.setHit_review_status(dhit[i].getHITReviewStatus().getValue());
		    }
		    if(dhit[i].getExpiration() != null) {
		        results.setExpiration_time(dhit[i].getExpiration().getTime());
		    }
		    if(dhit[i].getAssignmentDurationInSeconds() != null) {
	            results.setAssignment_duration_in_secs(dhit[i].getAssignmentDurationInSeconds().intValue());
		    }    
		    rhit.setNumber_of_assignments_available(dhit[i].getNumberOfAssignmentsAvailable());
            results.setNumber_of_assignments_pending(dhit[i].getNumberOfAssignmentsPending());
		    rhit.setNumber_of_assignments_completed(dhit[i].getNumberOfAssignmentsCompleted());
            results.setNumber_of_assignments_available(dhit[i].getNumberOfAssignmentsAvailable());
		    rhit.setNumber_of_assignments_pending(dhit[i].getNumberOfAssignmentsPending());
            results.setNumber_of_assignments_completed(dhit[i].getNumberOfAssignmentsCompleted());

            results.setRequester_annotation(dhit[i].getRequesterAnnotation());

		    //hitDAO.save(rhit);

		    com.amazonaws.mturk.requester.Assignment[] assign = gw.getAllAssignmentsForHIT(dhit[i].getHITId());
		    //TODO - All the assignments we got a hit and those that are already in the database are overlapping set.
	        //TODO - Take the difference of the once we got now and those that we have in db before updating the database

		    if(assign != null) {
                for(int j = 0; j < assign.length; j++) {
                    Assignment judgement = new Assignment(); 
                    judgement.setHitID(assign[j].getHITId());
                    judgement.setAssignment_id(assign[j].getAssignmentId());
                    judgement.setWorker_id(assign[j].getWorkerId());
                    judgement.setAccept_time(assign[j].getAcceptTime().getTime());
                    if (assign[j].getApprovalTime() != null){
                    	judgement.setApproval_time(assign[j].getApprovalTime().getTime());
                    }
                    if (assign[j].getRejectionTime() != null){
                    	judgement.setRejection_time(assign[j].getRejectionTime().getTime());
                    }
                    judgement.setAssignment_status(assign[j].getAssignmentStatus().getValue());
                    if (assign[j].getAutoApprovalTime() != null){
                        judgement.setAuto_approval_time(assign[j].getAutoApprovalTime().getTime());
                    }
                    judgement.setHit(phit);
                    assignmentDAO.save(judgement);
                    
                    results.setAnswers(assign[i].getAnswer());
                    results.setAccept_time(assign[i].getAcceptTime().getTime());
                    if (assign[i].getApprovalTime() != null){
                    	results.setApproval_time(assign[i].getApprovalTime().getTime());
                    }
                    results.setAssignment_id(assign[i].getAssignmentId());
                    results.setAssignment_status(assign[i].getAssignmentStatus().getValue());
                    results.setAuto_approval_time(assign[i].getAutoApprovalTime().getTime());
                    if (assign[i].getRejectionTime() != null){
                        results.setRejection_time(assign[i].getRejectionTime().getTime());
                    }
                    results.setRequester_feedback(assign[i].getRequesterFeedback());
                    results.setWorker_id(assign[i].getWorkerId());
                }
		   }
           resultDAO.save(results);
		}
        return results;
	}

	/**
	 * @param resultDAO the resultDAO to set
	 */
    @Autowired
    public void setResultDAO(ResultDAO resultDAO) {
		this.resultDAO = resultDAO;
	}
    /**
     * @param HitDAO the hitDAO to set
     */
    @Autowired
    public void setResultDAO(HitDAO hitDAO) {
        this.hitDAO = hitDAO;
    }
    /**
     * @param assignmentDAO the assignmentDAO to set
     */
    @Autowired
    public void setAssignmenttDAO(AssignmentDAO assignmentDAO) {
        this.assignmentDAO = assignmentDAO;
    }

	/* (non-Javadoc)
	 * @see com.crowdaccent.service.ResultService#getAsyncResultsForHIT(java.lang.String)
	 */
	@Override
	@Async
	public void getAsyncResultsForHIT(String id) {
		this.getResultsForHIT(id);
	}
}