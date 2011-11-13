/**
 * 
 */
package com.crowdaccent.repository;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.crowdaccent.entity.Product;

/**
 * @author kbhalla
 * 
 */
@Repository
@Transactional
public class ProductDAOImpl implements ProductDAO {

	static Property IMAGE = Property.forName("imageURL");
	static Property CATEGORY = Property.forName("category");

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
		//s.close();
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
		Product p = (Product)s.get(Product.class, new Long(id));
		return p;
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
		return (List<Product>)query.list();
	}

	/* (non-Javadoc)
	 * @see com.crowdaccent.repository.ProductDAO#getNumProducts(java.lang.Long)
	 */
	@Override
	public List<Product> getNumProducts(int number) {
		Session s = this.sessionFactory.getCurrentSession();
		Query query = s.createQuery("from Product product");
		query.setMaxResults(number);
		return (List<Product>)query.list();
	}

	/* (non-Javadoc)
	 * @see com.crowdaccent.repository.ProductDAO#getNumValidProducts(int)
	 */
	@Override
	public List<Product> getNumValidProducts(int number) {
		Session s = this.sessionFactory.getCurrentSession();
		return (List<Product>)s.createCriteria(Product.class).
				add(IMAGE.isNotNull()).
				add(CATEGORY.isNotNull()).
				setMaxResults(number).list();
	}

}
