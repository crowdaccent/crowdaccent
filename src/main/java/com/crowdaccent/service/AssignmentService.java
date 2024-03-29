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
	 * @param hit
	 * @param assignmentId
	 */
	void updateAssignment(Hit hit, String assignmentId);

	/**
	 * @param hit
	 * @param assignmentId
	 */
	void updateAsyncAssignment(Hit hit, String assignmentId);

}
