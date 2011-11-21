/**
 * 
 */
package com.crowdaccent.repository;

import java.util.List;

import com.crowdaccent.entity.Hit;

/**
 * @author mkutare
 *
 */
public interface HitDAO {
	/**
	 * @param product
	 */
	public void save(Hit hit);
	/**
	 * @param product
	 */
	public void delete(Hit hit);
	/**
	 * @param id
	 * @return
	 */
	public Hit getById(String id);
	/**
	 * @return
	 */
	public List<Hit> getAll();

	/** Returns limited number of hits.
	 * @param number number of hits to return.
	 * @return List of Assignments.
	 */
	public List<Hit> getNumHits(int number);
	
}
