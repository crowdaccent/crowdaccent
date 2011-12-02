/**
 * 
 */
package com.crowdaccent.service;

import com.crowdaccent.entity.Assignment;
import com.crowdaccent.entity.Hit;

/**
 * @author kbhalla
 *
 */
public interface AssignmentService {

	/**
	 * @param assignment
	 */
	void save(Assignment assignment);

	/**
	 * @param assignmentId
	 * @return
	 */
	Assignment getAssignmentById(String assignmentId);

	/**
	 * @param hit
	 */
	void updateAssignments(Hit hit);

	/**
	 * @param hitId
	 * @param assignmentId
	 */
	void updateAssignment(String hitId, String assignmentId);

	/**
	 * @param hitId
	 * @param assignmentId
	 */
	void updateAsyncAssignment(String hitId, String assignmentId);

}
