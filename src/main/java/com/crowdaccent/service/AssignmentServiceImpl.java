/**
 * 
 */
package com.crowdaccent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crowdaccent.entity.Assignment;
import com.crowdaccent.repository.AssignmentDAO;

/**
 * @author kbhalla
 *
 */
@Service
public class AssignmentServiceImpl implements AssignmentService {
	private AssignmentDAO assignmentDAO;

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
	
}
