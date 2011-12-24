/**
 * 
 */
package com.crowdaccent.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.amazonaws.mturk.requester.HITStatus;
import com.crowdaccent.entity.Hit;

/**
 * @author mkutare
 * 
 */
@Repository
@Transactional
public class HitDAOImpl implements HitDAO {

    /**
	 * 
	 */
	private static final String TASK_ID = "task.id";
	/**
	 * 
	 */
	private static final String HIT_STATUS = "hit_status";
	static Property HIT_ID = Property.forName("hit_id");
    static Property CREATION_TIME = Property.forName("creation_time");
    
	private @Autowired SessionFactory sessionFactory;

	@Override
    public void save(Hit hit) {
        Session s = this.sessionFactory.getCurrentSession();
        s.saveOrUpdate(hit);
    }

    @Override
    public void delete(Hit hit) {
        Session s = this.sessionFactory.getCurrentSession();
        s.delete(hit);
    }

    @Override
    public List<Hit> getNumHits(int number) {
        Session s = this.sessionFactory.getCurrentSession();
        Query query = s.createQuery("from Hit hit");
        query.setMaxResults(number);
        return (List<Hit>)query.list();
    }

    @Override
    public Hit getById(String id) {
        Session s = this.sessionFactory.getCurrentSession();
        Hit a = (Hit)s.get(Hit.class, new Long(id));
        return a;
    }

    @Override
    public List<Hit> getAll() {
        Session s = this.sessionFactory.getCurrentSession();
        Query query = s.createQuery("from Hit hit");
        return (List<Hit>)query.list();
    }

    @Override
    public Hit getByHitId(String hit_id) {
        Session s = this.sessionFactory.getCurrentSession();
        Criteria crt = s.createCriteria(Hit.class);
        Hit hit = (Hit)crt.add(Restrictions.eq(HIT_ID.getPropertyName(), (Object)(hit_id))).uniqueResult();
        return hit;
    }

	/* (non-Javadoc)
	 * @see com.crowdaccent.repository.HitDAO#countHits()
	 */
	@Override
	public Float countHits() {
		Session s = this.sessionFactory.getCurrentSession();
		return new Float((Long)s.createQuery("SELECT COUNT(o) FROM Hit o").list().get(0));
	}

	/* (non-Javadoc)
	 * @see com.crowdaccent.repository.HitDAO#findHitEntries(int, int)
	 */
	@Override
	public List<Hit> findHitEntries(int firstResult, int sizeNo) {
		Session s = this.sessionFactory.getCurrentSession();
		return (List<Hit>)s.createCriteria(Hit.class).setFirstResult(firstResult).
				setMaxResults(sizeNo).addOrder(CREATION_TIME.desc()).list();
	}

	/* (non-Javadoc)
	 * @see com.crowdaccent.repository.HitDAO#findHitEntriesByTask(java.lang.Long, int, int)
	 */
	@Override
	public List<Hit> findHitEntriesByTask(Long id, int firstResult,
			int sizeNo) {
		Session s = this.sessionFactory.getCurrentSession();
		return (List<Hit>)s.createCriteria(Hit.class).add(Restrictions.eq(TASK_ID, id)).setFirstResult(firstResult).
				setMaxResults(sizeNo).addOrder(CREATION_TIME.desc()).list();
	}

	/* (non-Javadoc)
	 * @see com.crowdaccent.repository.HitDAO#countHitsByTask(java.lang.Long)
	 */
	@Override
	public Float countHitsByTask(Long id) {
		Session s = this.sessionFactory.getCurrentSession();
		return new Float((Integer)s.createCriteria(Hit.class).add(Restrictions.eq(TASK_ID, id)).addOrder(CREATION_TIME.desc()).setProjection(Projections.rowCount()).uniqueResult());
	}

	/* (non-Javadoc)
	 * @see com.crowdaccent.repository.HitDAO#getAllByTask(java.lang.Long)
	 */
	@Override
	public List<Hit> getAllByTask(Long id) {
        Session s = this.sessionFactory.getCurrentSession();
       return (List<Hit>)s.createCriteria(Hit.class).add(Restrictions.eq(TASK_ID, id)).addOrder(CREATION_TIME.desc()).list();
	}

	/* (non-Javadoc)
	 * @see com.crowdaccent.repository.HitDAO#getUpdateableHITs()
	 */
	@Override
	public List<Hit> getUpdateableHITs() {
		Session s = this.sessionFactory.getCurrentSession();
		List<String> status = new ArrayList<String>();
		status.add(HITStatus.Assignable.toString());
		status.add(HITStatus.Unassignable.toString());
		
		return (List<Hit>) s.createCriteria(Hit.class).add(Restrictions.disjunction()
				.add(Property.forName(HIT_STATUS).in(status))
				.add(Property.forName(HIT_STATUS).isNull()))
				.addOrder(CREATION_TIME.asc()).list();
	}
}
