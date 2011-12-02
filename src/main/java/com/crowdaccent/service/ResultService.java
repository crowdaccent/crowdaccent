/**
 * 
 */
package com.crowdaccent.service;

import java.util.List;
import com.crowdaccent.entity.Result;

/**
 * @author mkutare
 *
 */
public interface ResultService {
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
	/**
	 * @return
	 */
	public List<Result> getNumResults(int number);
}
