/**
 * 
 */
package com.crowdaccent.repository;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.crowdaccent.entity.Hit;

/**
 * @author mkutare
 * 
 */
@Repository
@Transactional
public class HitDAOImpl implements HitDAO {

	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
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

}
