/**
 * 
 */
package com.crowdaccent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crowdaccent.entity.Assignment;
import com.crowdaccent.entity.Hit;
import com.crowdaccent.orchestration.gateway.Gateway;
import com.crowdaccent.repository.AssignmentDAO;

/**
 * @author kbhalla
 *
 */
@Service
public class AssignmentServiceImpl implements AssignmentService {
	private AssignmentDAO assignmentDAO;

	private Gateway gateway;
	/**
	 * @param assignmentDAO the assignmentDAO to set
	 */
	@Autowired
	public void setAssignmentDAO(AssignmentDAO assignmentDAO) {
		this.assignmentDAO = assignmentDAO;
	}

	/* (non-Javadoc)
	 * @see com.crowdaccent.service.AssignmentService#save(com.crowdaccent.entity.Assignment)
	 */
	@Override
	public void save(Assignment assignment) {
		assignmentDAO.save(assignment);
	}

	/* (non-Javadoc)
	 * @see com.crowdaccent.service.AssignmentService#getAssignmentById(java.lang.String)
	 */
	@Override
	public Assignment getAssignmentById(String assignmentId) {
		return assignmentDAO.getAssignmentById(assignmentId);
	}

	/* (non-Javadoc)
	 * @see com.crowdaccent.service.AssignmentService#updateAssignments(com.crowdaccent.entity.Hit)
	 */
	@Override
	public void updateAssignments(Hit hit) {
		com.amazonaws.mturk.requester.Assignment[] assignments = gateway
				.getAllAssignmentsForHIT(hit.getHit_id());
		updateAssignments(hit, assignments);
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
	
		Assignment databaseAssignment = this.getAssignmentById(assignment.getAssignmentId());
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
		this.save(databaseAssignment);
	}

	/**
	 * @param gateway the gateway to set
	 */
	@Autowired
	public void setGateway(Gateway gateway) {
		this.gateway = gateway;
	}
}