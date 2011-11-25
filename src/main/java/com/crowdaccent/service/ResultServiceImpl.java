/**
 * 
 */
package com.crowdaccent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.mturk.requester.GetReviewableHITsResult;
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
        
		GetReviewableHITsResult reviewableHit = gw.getReviewableHITsWithCreationTimeOrderAndPageDetails(phit.getHit_type_id());
        
		HIT hit[] = reviewableHit.getHIT();
		for (int i = 0; i < hit.length; i++) {
		    Hit rhit = new Hit();
	        results = new Result();
		    rhit.setHit_id(hit[i].getHITId());
            results.setHit_id(hit[i].getHITId());

		    rhit.setHit_type_id(hit[i].getHITTypeId());
	        results.setHit_type_id(hit[i].getHITTypeId());
		    
		    if(hit[i].getHITStatus() != null) {
		        rhit.setHit_status(hit[i].getHITStatus().getValue());
		        results.setHit_status(hit[i].getHITStatus().getValue());
		    }
		    
		    if(hit[i].getCreationTime() != null) {
		        rhit.setCreation_time(hit[i].getCreationTime().getTime());
                results.setCreation_time(hit[i].getCreationTime().getTime());
		    }
		    
		    rhit.setTitle(hit[i].getTitle());
            results.setTitle(hit[i].getTitle());
		    rhit.setDescription(hit[i].getDescription());
            results.setDescription(hit[i].getDescription());
		    rhit.setKeywords(hit[i].getTitle());
            results.setKeywords(hit[i].getKeywords());

		    rhit.setMax_assignments(hit[i].getMaxAssignments());
            results.setMax_assignments(hit[i].getMaxAssignments());

		    rhit.setNum_similar_hits(hit[i].getNumberOfSimilarHITs());
            results.setNum_similar_hits(hit[i].getNumberOfSimilarHITs());

		    if(hit[i].getReward() != null) {
		        rhit.setReward(hit[i].getReward().getAmount().doubleValue());
	            results.setReward(hit[i].getReward().getAmount().doubleValue());
		    }
		    
		    if(hit[i].getAutoApprovalDelayInSeconds() != null) {
		        rhit.setAuto_approval_delay_in_secs(hit[i].getAutoApprovalDelayInSeconds().intValue());
	            results.setAuto_approval_delay_in_secs(hit[i].getAutoApprovalDelayInSeconds().intValue());
		    }
		    if(hit[i].getHITReviewStatus() != null) {
		        rhit.setHit_review_status(hit[i].getHITReviewStatus().getValue());
	            results.setHit_review_status(hit[i].getHITReviewStatus().getValue());
		    }
		    if(hit[i].getExpiration() != null) {
		        results.setExpiration_time(hit[i].getExpiration().getTime());
		    }
		    if(hit[i].getAssignmentDurationInSeconds() != null) {
	            results.setAssignment_duration_in_secs(hit[i].getAssignmentDurationInSeconds().intValue());
		    }    
		    rhit.setNumber_of_assignments_available(hit[i].getNumberOfAssignmentsAvailable());
            results.setNumber_of_assignments_pending(hit[i].getNumberOfAssignmentsPending());
		    rhit.setNumber_of_assignments_completed(hit[i].getNumberOfAssignmentsCompleted());
            results.setNumber_of_assignments_available(hit[i].getNumberOfAssignmentsAvailable());
		    rhit.setNumber_of_assignments_pending(hit[i].getNumberOfAssignmentsPending());
            results.setNumber_of_assignments_completed(hit[i].getNumberOfAssignmentsCompleted());

            results.setRequester_annotation(hit[i].getRequesterAnnotation());

		    hitDAO.save(rhit);

		    com.amazonaws.mturk.requester.Assignment[] assign = gw.getAllAssignmentsForHIT(hit[i].getHITId(), reviewableHit.getPageNumber(), true);
		    if(assign != null) {
                for(int j = 0; j < assign.length; j++) {
                    Assignment judgement = new Assignment(); 
                    judgement.setHit_id(assign[j].getHITId());
                    judgement.setAssignment_id(assign[j].getAssignmentId());
                    judgement.setWorker_id(assign[j].getWorkerId());
                    judgement.setAccept_time(assign[j].getAcceptTime().getTime());
                    judgement.setApproval_time(assign[j].getApprovalTime().getTime());
                    judgement.setRejection_time(assign[j].getRejectionTime().getTime());
                    judgement.setAssignment_status(assign[j].getAssignmentStatus().getValue());
                    judgement.setAuto_approval_time(assign[j].getAutoApprovalTime().getTime());
                    assignmentDAO.save(judgement);
                    
                    results.setAnswers(assign[i].getAnswer());
                    results.setAccept_time(assign[i].getAcceptTime().getTime());
                    results.setApproval_time(assign[i].getApprovalTime().getTime());
                    results.setAssignment_id(assign[i].getAssignmentId());
                    results.setAssignment_status(assign[i].getAssignmentStatus().getValue());
                    results.setAuto_approval_time(assign[i].getAutoApprovalTime().getTime());
                    results.setRejection_time(assign[i].getRejectionTime().getTime());
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
}