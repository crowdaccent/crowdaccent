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

import com.crowdaccent.entity.Result;

/**
 * @author mkutare
 * 
 */
@Repository
@Transactional
public class ResultDAOImpl implements ResultDAO {

	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
    public void save(Result result) {
        Session s = this.sessionFactory.getCurrentSession();
        s.saveOrUpdate(result);
    }

    @Override
    public void delete(Result result) {
        Session s = this.sessionFactory.getCurrentSession();
        s.delete(result);
    }

    @Override
    public List<Result> getNumResults(int number) {
        Session s = this.sessionFactory.getCurrentSession();
        Query query = s.createQuery("from Result result");
        query.setMaxResults(number);
        return (List<Result>)query.list();
    }

    @Override
    public Result getById(String id) {
        Session s = this.sessionFactory.getCurrentSession();
        Result a = (Result)s.get(Result.class, new Long(id));
        return a;
    }

    @Override
    public List<Result> getAll() {
        Session s = this.sessionFactory.getCurrentSession();
        Query query = s.createQuery("from Result result");
        return (List<Result>)query.list();
    }

}
