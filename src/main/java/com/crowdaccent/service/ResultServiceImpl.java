/**
 * 
 */
package com.crowdaccent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crowdaccent.entity.Result;
import com.crowdaccent.repository.ResultDAO;

/**
 * @author mkutare
 *
 */
@Service
public class ResultServiceImpl implements ResultService {
	private ResultDAO resultDAO;
	private HitService hitService;
	private AssignmentService assignmentService;
	
	/* (non-Javadoc)
	 * @see com.crowdaccent.service.ResultService#save(com.crowdaccent.entity.Result)
	 */
	@Override
	public void save(Result result) {
	    resultDAO.save(result);
	}

	/* (non-Javadoc)
	 * @see com.crowdaccent.service.ResultService#delete(com.crowdaccent.entity.Result)
	 */
	@Override
	public void delete(Result result) {
	    resultDAO.delete(result);
	}

	/* (non-Javadoc)
	 * @see com.crowdaccent.service.ResultService#getById(java.lang.String)
	 */
	@Override
	public Result getById(String id) {
		return resultDAO.getById(id);
	}
	
	/* (non-Javadoc)
	 * @see com.crowdaccent.service.ResultService#getAll()
	 */
	@Override
	public List<Result> getAll() {
		return resultDAO.getAll();
	}

	/* (non-Javadoc)
	 * @see com.crowdaccent.service.ResultService#getNumResults(int)
	 */
	@Override
	public List<Result> getNumResults(int number) {
		return resultDAO.getNumResults(number);
	}

		/**
	 * @param resultDAO the resultDAO to set
	 */
    @Autowired
    public void setResultDAO(ResultDAO resultDAO) {
		this.resultDAO = resultDAO;
	}
   

		/**
	 * @param hitService the hitService to set
	 */
	@Autowired
	public void setHitService(HitService hitService) {
		this.hitService = hitService;
	}

	/**
	 * @param assignmentService the assignmentService to set
	 */
	@Autowired
	public void setAssignmentService(AssignmentService assignmentService) {
		this.assignmentService = assignmentService;
	}
}