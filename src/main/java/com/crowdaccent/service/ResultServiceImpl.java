/**
 * 
 */
package com.crowdaccent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.mturk.requester.Assignment;
import com.amazonaws.mturk.requester.GetReviewableHITsResult;
import com.amazonaws.mturk.requester.HIT;
import com.crowdaccent.entity.Result;
import com.crowdaccent.orchestration.gateway.Gateway;
import com.crowdaccent.orchestration.gateway.amazon.GatewayAmazonMTurkImpl;
import com.crowdaccent.repository.ResultDAO;

/**
 * @author mkutare
 *
 */
@Service
public class ResultServiceImpl implements ResultService {
	private ResultDAO resultDAO;
	
	
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
	public Result getResultForHIT(String id) {
		Result r = this.getById(id);
		Gateway gw = new GatewayAmazonMTurkImpl();
		
		String hitTypeId = "2NDW4ZMAZHHRSR21UJEN2RH1KH2AJ7";
		Integer pageNum = 1;
		Integer pageSize = 1;
		
		GetReviewableHITsResult reviewableHit = gw.getReviewableHITsWithCreationTimeOrderAndPageDetails(hitTypeId, pageNum, pageSize);
		
		for (int i = 0; i < reviewableHit.getHIT().length; i++) {
		    HIT hit[] = reviewableHit.getHIT();
		    r.setHit_id(hit[i].getHITId());
		    r.setHit_type_id(hit[i].getHITTypeId());
		    r.setCreation_time(hit[i].getCreationTime().getTime());
		    r.setTitle(hit[i].getTitle());
		    r.setDescription(hit[i].getDescription());
		    r.setKeywords(hit[i].getKeywords());
		    r.setHit_status(hit[i].getHITStatus().getValue());
		    r.setMax_assignments(hit[i].getMaxAssignments());
		    r.setReward(hit[i].getReward().getAmount().doubleValue());
		    r.setAuto_approval_delay_in_secs(hit[i].getAutoApprovalDelayInSeconds().intValue());
		    r.setExpiration_time(hit[i].getExpiration().getTime());
		    r.setAssignment_duration_in_secs(hit[i].getAssignmentDurationInSeconds().intValue());
		    r.setExpiration_time(hit[i].getExpiration().getTime());
		    r.setNum_similar_hits(hit[i].getNumberOfSimilarHITs());
		    r.setRequester_annotation(hit[i].getRequesterAnnotation());
		    //hit[i].getQualificationRequirement());
		    r.setHit_review_status(hit[i].getHITReviewStatus().getValue());
		    r.setNumber_of_assignments_pending(hit[i].getNumberOfAssignmentsPending());
		    r.setNumber_of_assignments_available(hit[i].getNumberOfAssignmentsAvailable());
		    r.setNumber_of_assignments_completed(hit[i].getNumberOfAssignmentsCompleted());
		    
		    Assignment assign[] = gw.getAllAssignmentsForHIT(hit[i].getHITId(), reviewableHit.getPageNumber(), reviewableHit.getPageNumber(), true);
		    for(int j = 0; j < assign.length; j++) {
		        r.setAnswers(assign[i].getAnswer());
		        r.setAccept_time(assign[i].getAcceptTime().getTime());
		        r.setApproval_time(assign[i].getApprovalTime().getTime());
		        r.setAssignment_id(assign[i].getAssignmentId());
		        r.setAssignment_status(assign[i].getAssignmentStatus().getValue());
		        r.setAuto_approval_time(assign[i].getAutoApprovalTime().getTime());
		        r.setRejection_time(assign[i].getRejectionTime().getTime());
		        //assign[i].getDeadline().getTime());
		        r.setRequester_feedback(assign[i].getRequesterFeedback());
		        r.setWorker_id(assign[i].getWorkerId());
		    }
		}
		
		this.save(r);
		return r;
	}

    @Override
    public Result[] getResultsForHIT(String id) {
        // TODO Auto-generated method stub
        return null;
    }

	/**
	 * @param resultDAO the resultDAO to set
	 */
    @Autowired
    public void setResultDAO(ResultDAO resultDAO) {
		this.resultDAO = resultDAO;
	}
}