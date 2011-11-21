/**
 * 
 */
package com.crowdaccent.repository;

import java.util.List;

import com.crowdaccent.entity.Assignment;

/**
 * @author mkutare
 *
 */
public interface AssignmentDAO {
	/**
	 * @param product
	 */
	public void save(Assignment assignment);
	/**
	 * @param product
	 */
	public void delete(Assignment assignment);
	/**
	 * @param id
	 * @return
	 */
	public Assignment getById(String id);
	/**
	 * @return
	 */
	public List<Assignment> getAll();
	/** Returns limited number of assignments.
	 * @param number number of assignments to return.
	 * @return List of Assignments.
	 */
	public List<Assignment> getNumAssignments(int number);

}
