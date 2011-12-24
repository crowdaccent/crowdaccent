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
	 * @param hit
	 */
	public void save(Hit hit);
	/**
	 * @param hit
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
	/**
	 * 
	 * @param hit_id
	 * @return
	 */
	public Hit getByHitId(String hit_id);
	/**
	 * @return
	 */
	public Float countHits();
	/**
	 * @param firstResult
	 * @param sizeNo
	 * @return
	 */
	public List<Hit> findHitEntries(int firstResult, int sizeNo);
	/**
	 * @param id
	 * @param firstResult
	 * @param sizeNo
	 * @return
	 */
	public List<Hit> findHitEntriesByTask(Long id, int firstResult,
			int sizeNo);
	/**
	 * @param id
	 * @return
	 */
	public Float countHitsByTask(Long id);
	/**
	 * @param id
	 * @return
	 */
	public List<Hit> getAllByTask(Long id);
	/**
	 * @return
	 */
	public List<Hit> getUpdateableHITs();

	
}
