/**
 * 
 */
package com.crowdaccent.repository;

import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.crowdaccent.entity.ContentModeration;

/**
 * @author kbhalla
 *
 */
@Repository
@Transactional
public class ContentModerationDAOImpl implements ContentModerationDAO {

	private @Autowired SessionFactory sessionFactory;

	/* (non-Javadoc)
	 * @see com.crowdaccent.repository.ContentModerationDAO#save(com.crowdaccent.entity.ContentModeration)
	 */
	@Override
	public void save(ContentModeration contentModeration) {
		Session s = this.sessionFactory.getCurrentSession();
		s.saveOrUpdate(contentModeration);
	}

	/* (non-Javadoc)
	 * @see com.crowdaccent.repository.ContentModerationDAO#delete(com.crowdaccent.entity.ContentModeration)
	 */
	@Override
	public void delete(ContentModeration contentModeration) {
		Session s = this.sessionFactory.getCurrentSession();
		s.delete(contentModeration);
	}

	/* (non-Javadoc)
	 * @see com.crowdaccent.repository.ContentModerationDAO#countContentModerations()
	 */
	@Override
	public Float countContentModerations() {
		Session s = this.sessionFactory.getCurrentSession();
		return new Float((Long)s.createQuery("SELECT COUNT(o) FROM ContentModeration o").list().get(0));
	}

	/* (non-Javadoc)
	 * @see com.crowdaccent.repository.ContentModerationDAO#findAllContentModerations()
	 */
	@Override
	public Collection<ContentModeration> findAllContentModerations() {
		Session s = this.sessionFactory.getCurrentSession();
		Query query = s.createQuery("from ContentModeration contentModeration");
		return (List<ContentModeration>)query.list();
	}

	/* (non-Javadoc)
	 * @see com.crowdaccent.repository.ContentModerationDAO#findContentModerationEntries(int, int)
	 */
	@Override
	public List<ContentModeration> findContentModerationEntries(
			int firstResult, int sizeNo) {
		Session s = this.sessionFactory.getCurrentSession();
		return (List<ContentModeration>)s.createCriteria(ContentModeration.class).setFirstResult(firstResult).
				setMaxResults(sizeNo).list();
	}

	/* (non-Javadoc)
	 * @see com.crowdaccent.repository.ContentModerationDAO#findContentModeration(java.lang.Long)
	 */
	@Override
	public ContentModeration findContentModeration(Long id) {
		Session s = this.sessionFactory.getCurrentSession();
		ContentModeration c = (ContentModeration)s.get(ContentModeration.class, new Long(id));
		return c;
	}

}
