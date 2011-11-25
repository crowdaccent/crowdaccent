/**
 * 
 */
package com.crowdaccent.repository;

import java.util.List;

import com.crowdaccent.entity.Hit;
import com.crowdaccent.entity.Product;

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
	public List<Hit> findHitEntriesByProduct(Long id, int firstResult,
			int sizeNo);
	/**
	 * @param id
	 * @return
	 */
	public Float countHitsByProduct(Long id);
	/**
	 * @param id
	 * @return
	 */
	public List<Hit> getAllByProduct(Long id);

	
}
