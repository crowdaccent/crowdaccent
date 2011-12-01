/**
 * 
 */
package com.crowdaccent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.amazonaws.mturk.requester.HIT;
import com.amazonaws.mturk.requester.HITStatus;
import com.crowdaccent.entity.Assignment;
import com.crowdaccent.entity.Hit;
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
	private HitService hitService;
	private AssignmentService assignmentService;
	
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
	    Hit databaseHit = hitService.getByHitId(hit_id);
        
	    Gateway gw = new GatewayAmazonMTurkImpl();
	    HIT remoteHit = gw.getHIT(hit_id);
	    if (remoteHit != null && remoteHit.getHITStatus() == HITStatus.Reviewable){
	    	updateDatabaseHit(databaseHit, remoteHit);
	    	com.amazonaws.mturk.requester.Assignment[] assignments = gw
	    			.getAllAssignmentsForHIT(databaseHit.getHit_id());
	    	updateAssignments(databaseHit, assignments);
	    }
        return null;
	}

	/**
	 * @param databaseHit
	 * @param assignments
	 */
	private void updateAssignments(Hit databaseHit,
			com.amazonaws.mturk.requester.Assignment[] assignments) {
		if (assignments != null) {
			for (int j = 0; j < assignments.length; j++) {
				com.amazonaws.mturk.requester.Assignment assignment = assignments[j];
				updateAssignment(databaseHit, assignment);
			}
		}
	}

	/**
	 * @param databaseHit
	 * @param assignment
	 */
	private void updateAssignment(Hit databaseHit,
			com.amazonaws.mturk.requester.Assignment assignment) {
		//TODO: Get the assignment if exists in database and update.
		
		Assignment databaseAssignment = assignmentService.getAssignmentById(assignment.getAssignmentId());
		if (databaseAssignment == null){
			databaseAssignment = new Assignment();
		}
		databaseAssignment.setHitID(assignment.getHITId());
		databaseAssignment.setAssignment_id(assignment.getAssignmentId());
		databaseAssignment.setWorker_id(assignment.getWorkerId());
		databaseAssignment.setAccept_time(assignment.getAcceptTime().getTime());
		if (assignment.getApprovalTime() != null){
			databaseAssignment.setApproval_time(assignment.getApprovalTime().getTime());
		}
		if (assignment.getRejectionTime() != null){
			databaseAssignment.setRejection_time(assignment.getRejectionTime().getTime());
		}
		databaseAssignment.setAssignment_status(assignment.getAssignmentStatus().getValue());
		if (assignment.getAutoApprovalTime() != null){
		    databaseAssignment.setAuto_approval_time(assignment.getAutoApprovalTime().getTime());
		}
		databaseAssignment.setSubmit_time(assignment.getSubmitTime().getTime());
		databaseAssignment.setHit(databaseHit);
		assignmentService.save(databaseAssignment);
	}

	/**
	 * @param databaseHit
	 * @param remoteHit
	 */
	private void updateDatabaseHit(Hit databaseHit, HIT remoteHit) {
		if(remoteHit.getHITStatus() != null) {
		    databaseHit.setHit_status(remoteHit.getHITStatus().getValue());
		}
		if(remoteHit.getCreationTime() != null) {
		    databaseHit.setCreation_time(remoteHit.getCreationTime().getTime());
		}
		databaseHit.setTitle(remoteHit.getTitle());
		databaseHit.setDescription(remoteHit.getDescription());
		databaseHit.setKeywords(remoteHit.getKeywords());
		databaseHit.setMax_assignments(remoteHit.getMaxAssignments());
		databaseHit.setNum_similar_hits(remoteHit.getNumberOfSimilarHITs());
		
		if(remoteHit.getReward() != null) {
		    databaseHit.setReward(remoteHit.getReward().getAmount().doubleValue());
		}
		
		if(remoteHit.getAutoApprovalDelayInSeconds() != null) {
		    databaseHit.setAuto_approval_delay_in_secs(remoteHit.getAutoApprovalDelayInSeconds().intValue());
		}
		if(remoteHit.getHITReviewStatus() != null) {
		    databaseHit.setHit_review_status(remoteHit.getHITReviewStatus().getValue());
		}
		databaseHit.setNumber_of_assignments_available(remoteHit.getNumberOfAssignmentsAvailable());
		databaseHit.setNumber_of_assignments_completed(remoteHit.getNumberOfAssignmentsCompleted());
		databaseHit.setNumber_of_assignments_pending(remoteHit.getNumberOfAssignmentsPending());
		hitService.save(databaseHit);
	}

	/**
	 * @param resultDAO the resultDAO to set
	 */
    @Autowired
    public void setResultDAO(ResultDAO resultDAO) {
		this.resultDAO = resultDAO;
	}
   

	/* (non-Javadoc)
	 * @see com.crowdaccent.service.ResultService#getAsyncResultsForHIT(java.lang.String)
	 */
	@Override
	@Async
	public void getAsyncResultsForHIT(String id) {
		this.getResultsForHIT(id);
	}

	/**
	 * @param hitService the hitService to set
	 */
	@Autowired
	public void setHitService(HitService hitService) {
		this.hitService = hitService;
	}

	/**
	 * @param assignmentService the assignmentService to set
	 */
	@Autowired
	public void setAssignmentService(AssignmentService assignmentService) {
		this.assignmentService = assignmentService;
	}
}