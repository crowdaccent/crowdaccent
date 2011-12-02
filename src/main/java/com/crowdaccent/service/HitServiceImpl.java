/**
 * 
 */
package com.crowdaccent.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.amazonaws.mturk.requester.HIT;
import com.amazonaws.mturk.requester.HITStatus;
import com.crowdaccent.entity.Hit;
import com.crowdaccent.orchestration.gateway.Gateway;
import com.crowdaccent.repository.HitDAO;

/**
 * @author kbhalla
 * 
 */
@Service
public class HitServiceImpl implements HitService {

	private HitDAO hitDAO;

	private AssignmentService assignmentService;

	private Gateway gateway;
	
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.crowdaccent.service.HitService#getByHitId(java.lang.String)
	 */
	@Override
	public Hit getByHitId(String string) {
		return hitDAO.getByHitId(string);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.crowdaccent.service.HitService#getAsyncResultsForHIT(java.lang.String
	 * )
	 */
	@Override
	@Async
	public void getAsyncResultsForHIT(String hitId) {
		this.getResultsForHIT(hitId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.crowdaccent.service.HitService#getResultsForHIT(java.lang.String)
	 */
	@Override
	public Hit getResultsForHIT(String hitId) {
		Hit databaseHit = this.getByHitId(hitId);
		return this.getResultsForHIT(databaseHit);
	}
	/* (non-Javadoc)
	 * @see com.crowdaccent.service.HitService#getResultsForHIT(com.crowdaccent.entity.Hit)
	 */
	@Override
	public Hit getResultsForHIT(Hit hit) {
		HIT remoteHit = gateway.getHIT(hit.getHit_id());
		if (remoteHit != null
				&& remoteHit.getHITStatus() == HITStatus.Reviewable) {
			updateDatabaseHit(hit, remoteHit);
			assignmentService.updateAssignments(hit);
		}
		return hit;
	}

	/**
	 * @param databaseHit
	 * @param remoteHit
	 */
	private void updateDatabaseHit(Hit databaseHit, HIT remoteHit) {
		if (remoteHit.getHITStatus() != null) {
			databaseHit.setHit_status(remoteHit.getHITStatus().getValue());
		}
		if (remoteHit.getCreationTime() != null) {
			databaseHit.setCreation_time(remoteHit.getCreationTime().getTime());
		}
		databaseHit.setTitle(remoteHit.getTitle());
		databaseHit.setDescription(remoteHit.getDescription());
		databaseHit.setKeywords(remoteHit.getKeywords());
		databaseHit.setMax_assignments(remoteHit.getMaxAssignments());
		databaseHit.setNum_similar_hits(remoteHit.getNumberOfSimilarHITs());

		if (remoteHit.getReward() != null) {
			databaseHit.setReward(remoteHit.getReward().getAmount()
					.doubleValue());
		}

		if (remoteHit.getAutoApprovalDelayInSeconds() != null) {
			databaseHit.setAuto_approval_delay_in_secs(remoteHit
					.getAutoApprovalDelayInSeconds().intValue());
		}
		if (remoteHit.getHITReviewStatus() != null) {
			databaseHit.setHit_review_status(remoteHit.getHITReviewStatus()
					.getValue());
		}
		databaseHit.setNumber_of_assignments_available(remoteHit
				.getNumberOfAssignmentsAvailable());
		databaseHit.setNumber_of_assignments_completed(remoteHit
				.getNumberOfAssignmentsCompleted());
		databaseHit.setNumber_of_assignments_pending(remoteHit
				.getNumberOfAssignmentsPending());
		this.save(databaseHit);
	}

	/**
	 * @param assignmentService the assignmentService to set
	 */
	@Autowired
	public void setAssignmentService(AssignmentService assignmentService) {
		this.assignmentService = assignmentService;
	}

	/**
	 * @param gateway the gateway to set
	 */
	@Autowired
	public void setGateway(Gateway gateway) {
		this.gateway = gateway;
	}


	/*
	 * @Scheduled(cron = "05 * * * * ?") public void runHourly() {
	 * _log.info("Running Hourly Task : it is " + new Date()); }
	 */

}
