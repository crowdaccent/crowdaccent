/**
 * 
 */
package com.crowdaccent.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crowdaccent.entity.Hit;
import com.crowdaccent.repository.HitDAO;

/**
 * @author kbhalla
 * 
 */
@Service
public class HitServiceImpl implements HitService {

	private HitDAO hitDAO;

	public static final Logger _log = LoggerFactory
			.getLogger(HitServiceImpl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.crowdaccent.service.HitService#save(com.crowdaccent.entity.Hit)
	 */
	@Override
	public void save(Hit hit) {
		hitDAO.save(hit);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.crowdaccent.service.HitService#delete(com.crowdaccent.entity.Hit)
	 */
	@Override
	public void delete(Hit hit) {
		hitDAO.delete(hit);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.crowdaccent.service.HitService#getById(java.lang.String)
	 */
	@Override
	public Hit getById(String id) {
		return hitDAO.getById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.crowdaccent.service.HitService#getAll()
	 */
	@Override
	public List<Hit> getAll() {
		return hitDAO.getAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.crowdaccent.service.HitService#findHitEntries(int, int)
	 */
	@Override
	public List<Hit> findHitEntries(int firstResult, int sizeNo) {
		return hitDAO.findHitEntries(firstResult, sizeNo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.crowdaccent.service.HitService#countHits()
	 */
	@Override
	public Float countHits() {
		return hitDAO.countHits();
	}

	/**
	 * @param hitdao
	 *            the hitdao to set
	 */
	@Autowired
	public void setHitDAO(HitDAO hitDAO) {
		this.hitDAO = hitDAO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.crowdaccent.service.HitService#findHitEntriesByProduct(java.lang.
	 * Long, int, int)
	 */
	@Override
	public List<Hit> findHitEntriesByProduct(Long id, int firstResult,
			int sizeNo) {
		return hitDAO.findHitEntriesByProduct(id, firstResult, sizeNo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.crowdaccent.service.HitService#countHitsByProduct(java.lang.Long)
	 */
	@Override
	public Float countHitsByProduct(Long id) {
		return hitDAO.countHitsByProduct(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.crowdaccent.service.HitService#getAllByProduct(java.lang.Long)
	 */
	@Override
	public List<Hit> getAllByProduct(Long id) {
		return hitDAO.getAllByProduct(id);
	}

	/* (non-Javadoc)
	 * @see com.crowdaccent.service.HitService#getByHitId(java.lang.String)
	 */
	@Override
	public Hit getByHitId(String string) {
		return hitDAO.getByHitId(string);
	}

	/*@Scheduled(cron = "05 * * * * ?")
	public void runHourly() {
		_log.info("Running Hourly Task : it is " + new Date());
	}*/

}
