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
}
