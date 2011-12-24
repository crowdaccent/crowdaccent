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
import com.crowdaccent.entity.Hit;
import com.crowdaccent.entity.Task;
import com.crowdaccent.orchestration.gateway.Gateway;
import com.crowdaccent.repository.HitDAO;

/**
 * @author kbhalla
 * 
 */
@Service
public class HitServiceImpl implements HitService {

	private @Autowired
	HitDAO hitDAO;

	private @Autowired
	AssignmentService assignmentService;

	private @Autowired
	Gateway gateway;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.crowdaccent.service.HitService#findHitEntriesByTask(java.lang.
	 * Long, int, int)
	 */
	@Override
	public List<Hit> findHitEntriesByTask(Long id, int firstResult, int sizeNo) {
		return hitDAO.findHitEntriesByTask(id, firstResult, sizeNo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.crowdaccent.service.HitService#countHitsByTask(java.lang.Long)
	 */
	@Override
	public Float countHitsByTask(Long id) {
		return hitDAO.countHitsByTask(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.crowdaccent.service.HitService#getAllByTask(java.lang.Long)
	 */
	@Override
	public List<Hit> getAllByTask(Long id) {
		return hitDAO.getAllByTask(id);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.crowdaccent.service.HitService#getResultsForHIT(com.crowdaccent.entity
	 * .Hit)
	 */
	@Override
	public Hit getResultsForHIT(Hit hit) {
		HIT remoteHit = gateway.getHIT(hit.getHit_id());
		if (remoteHit != null) {
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.crowdaccent.service.HitService#getUpdateableHITs()
	 */
	@Override
	public List<Hit> getUpdateableHITs() {
		return hitDAO.getUpdateableHITs();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.crowdaccent.service.HitService#getAsyncResultsForHIT(com.crowdaccent
	 * .entity.Hit)
	 */
	@Override
	@Async
	public void getAsyncResultsForHIT(Hit hit) {
		this.getResultsForHIT(hit);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.crowdaccent.service.HitService#persistHITData(com.crowdaccent.entity
	 * .Task, java.lang.String, com.amazonaws.mturk.requester.HIT)
	 */
	@Override
	public void persistHITData(Task t, String websiteURL, HIT hit) {
		if (hit == null)
			return;
		Hit h = new Hit();
		h.setTask(t);
		h.setHit_id(hit.getHITId());
		h.setHit_type_id(hit.getHITTypeId());
		h.setTitle(hit.getTitle());
		h.setDescription(hit.getDescription());
		h.setKeywords(hit.getKeywords());
		h.setHit_url(websiteURL);

		if (hit.getHITStatus() != null) {
			h.setHit_status(hit.getHITStatus().getValue());
		}
		if (hit.getHITReviewStatus() != null) {
			h.setHit_review_status(hit.getHITReviewStatus().getValue());
		}
		if (hit.getCreationTime() != null) {
			h.setCreation_time(hit.getCreationTime().getTime());
		}
		if (hit.getAutoApprovalDelayInSeconds() != null) {
			h.setAuto_approval_delay_in_secs(hit
					.getAutoApprovalDelayInSeconds().intValue());
		}
		if (hit.getMaxAssignments() != null) {
			h.setMax_assignments(hit.getMaxAssignments());
		}
		if (hit.getReward() != null) {
			h.setReward(hit.getReward().getAmount().doubleValue());
		}
		if (hit.getNumberOfSimilarHITs() != null) {
			h.setNum_similar_hits(hit.getNumberOfSimilarHITs());
		}
		if (hit.getNumberOfAssignmentsAvailable() != null) {
			h.setNumber_of_assignments_available(hit
					.getNumberOfAssignmentsAvailable());
		}
		if (hit.getNumberOfAssignmentsAvailable() != null) {
			h.setNumber_of_assignments_available(hit
					.getNumberOfAssignmentsAvailable());
		}
		if (hit.getNumberOfAssignmentsCompleted() != null) {
			h.setNumber_of_assignments_completed(hit
					.getNumberOfAssignmentsCompleted());
		}
		if (hit.getNumberOfAssignmentsPending() != null) {
			h.setNumber_of_assignments_pending(hit
					.getNumberOfAssignmentsPending());
		}

		this.save(h);
	}
}
