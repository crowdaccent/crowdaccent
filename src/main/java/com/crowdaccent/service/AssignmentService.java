/**
 * 
 */
package com.crowdaccent.service;

import com.crowdaccent.entity.Assignment;

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

}
