/**
 * 
 */
package com.crowdaccent.repository;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.crowdaccent.entity.Product;

/**
 * @author kbhalla
 * 
 */
@Repository
public class ProductDAOImpl implements ProductDAO {

	private SessionFactory sessionFactory;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.crowdaccent.repository.ProductDAO#save(com.crowdaccent.entity.Product
	 * )
	 */
	@Override
	public void save(Product product) {
		Session s = this.sessionFactory.getCurrentSession();
		s.saveOrUpdate(product);
		s.flush();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.crowdaccent.repository.ProductDAO#delete(com.crowdaccent.entity.Product
	 * )
	 */
	@Override
	public void delete(Product product) {
		Session s = this.sessionFactory.getCurrentSession();
		s.delete(product);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.crowdaccent.repository.ProductDAO#getById(java.lang.String)
	 */
	@Override
	public Product getById(String id) {
		Session s = this.sessionFactory.getCurrentSession();
		return (Product)s.load(Product.class, new Long(id));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.crowdaccent.repository.ProductDAO#getAll()
	 */
	@Override
	public List<Product> getAll() {
		Session s = this.sessionFactory.getCurrentSession();
		Query query = s.createQuery("from Product product");
		return query.list();
	}

}
