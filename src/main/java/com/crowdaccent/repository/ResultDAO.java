/**
 * 
 */
package com.crowdaccent.repository;

import java.util.List;

import com.crowdaccent.entity.Result;

/**
 * @author mkutare
 *
 */
public interface ResultDAO {
	/**
	 * @param result
	 */
	public void save(Result result);
	/**
	 * @param result
	 */
	public void delete(Result result);
	/**
	 * @param id
	 * @return
	 */
	public Result getById(String id);
	/**
	 * @return
	 */
	public List<Result> getAll();

	/** Returns limited number of assignments.
	 * @param number number of assignments to return.
	 * @return List of Assignments.
	 */
	public List<Result> getNumResults(int number);
	
}
