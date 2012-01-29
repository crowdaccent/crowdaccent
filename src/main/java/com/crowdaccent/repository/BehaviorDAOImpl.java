package com.crowdaccent.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.crowdaccent.entity.Behavior;

/**
 * @author mkutare
 * 
 */
@Repository
@Transactional
public class BehaviorDAOImpl implements BehaviorDAO {

	private @Autowired SessionFactory sessionFactory;

	@Override
	public void save(Behavior behavior) {
		Session s = this.sessionFactory.getCurrentSession();
		s.saveOrUpdate(behavior);
	}

}
