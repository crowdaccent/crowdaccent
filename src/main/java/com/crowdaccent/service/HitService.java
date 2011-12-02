/**
 * 
 */
package com.crowdaccent.service;

import java.util.List;

import com.crowdaccent.entity.Hit;

/**
 * @author kbhalla
 *
 */
public interface HitService {
	/**
	 * @param Hit
	 */
	public void save(Hit hit);
	/**
	 * @param Hit
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
	/**
	 * @param firstResult
	 * @param sizeNo
	 * @return
	 */
	public List<Hit> findHitEntries(int firstResult, int sizeNo);
	/**
	 * @return
	 */
	public Float countHits();
	/**
	 * @param id
	 * @param firstResult
	 * @param sizeNo
	 * @return
	 */
	public List<Hit> findHitEntriesByProduct(Long id, int firstResult, int sizeNo);
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
	/**
	 * @param string
	 * @return
	 */
	public Hit getByHitId(String string);
	/**
	 * @param hitId
	 */
	public void getAsyncResultsForHIT(String hitId);
	/**
	 * @param hitId
	 */
	public Hit getResultsForHIT(String hitId);

	/**
	 * @param hit
	 */
	public Hit getResultsForHIT(Hit hit);
	/**
	 * @return
	 */
	public List<Hit> getUpdateableHITs();
	/**
	 * @param hit
	 */
	public void getAsyncResultsForHIT(Hit hit);
}
