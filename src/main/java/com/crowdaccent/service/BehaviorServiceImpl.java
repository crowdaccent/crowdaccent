package com.crowdaccent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crowdaccent.entity.Behavior;
import com.crowdaccent.repository.BehaviorDAO;

/**
 * @author mkutare
 * 
 */
@Service
public class BehaviorServiceImpl implements BehaviorService {

  private @Autowired BehaviorDAO behaviorDAO;
  
	@Override
	public void submit(Behavior behavior) {
		behaviorDAO.save(behavior);
	}
}
