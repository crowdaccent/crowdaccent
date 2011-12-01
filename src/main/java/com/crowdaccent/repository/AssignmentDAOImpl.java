/**
 * 
 */
package com.crowdaccent.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.crowdaccent.entity.Assignment;

/**
 * @author mkutare
 * 
 */
@Repository
@Transactional
public class AssignmentDAOImpl implements AssignmentDAO {

	 static Property ASSIGNMENT_ID = Property.forName("assignment_id");
	 private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
    public void save(Assignment assignment) {
        Session s = this.sessionFactory.getCurrentSession();
        s.saveOrUpdate(assignment);
    }

    @Override
    public void delete(Assignment assignment) {
        Session s = this.sessionFactory.getCurrentSession();
        s.delete(assignment);
    }

    @Override
    public List<Assignment> getNumAssignments(int number) {
        Session s = this.sessionFactory.getCurrentSession();
        Query query = s.createQuery("from Assignment assignment");
        query.setMaxResults(number);
        return (List<Assignment>)query.list();
    }

    @Override
    public Assignment getById(String id) {
        Session s = this.sessionFactory.getCurrentSession();
        Assignment a = (Assignment)s.get(Assignment.class, new Long(id));
        return a;
    }

    @Override
    public List<Assignment> getAll() {
        Session s = this.sessionFactory.getCurrentSession();
        Query query = s.createQuery("from Assignment assignment");
        return (List<Assignment>)query.list();
    }

	/* (non-Javadoc)
	 * @see com.crowdaccent.repository.AssignmentDAO#getAssignmentById(java.lang.String)
	 */
	@Override
	public Assignment getAssignmentById(String assignmentId) {
        Session s = this.sessionFactory.getCurrentSession();
        Criteria crt = s.createCriteria(Assignment.class);
        Assignment assignment = (Assignment)crt.add(Restrictions.eq(ASSIGNMENT_ID.getPropertyName(), (Object)(assignmentId))).uniqueResult();
        return assignment;
	}

}
