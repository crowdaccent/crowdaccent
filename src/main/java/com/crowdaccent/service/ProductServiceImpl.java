/**
 * 
 */
package com.crowdaccent.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crowdaccent.entity.Product;
import com.crowdaccent.repository.ProductDAO;

/**
 * @author kbhalla
 *
 */
@Service
public class ProductServiceImpl implements ProductService {
	private ProductDAO productDAO;
	
	/* (non-Javadoc)
	 * @see com.crowdaccent.service.ProductService#save(com.crowdaccent.entity.Product)
	 */
	@Override
	public void save(Product product) {
		productDAO.save(product);
	}

	/* (non-Javadoc)
	 * @see com.crowdaccent.service.ProductService#delete(com.crowdaccent.entity.Product)
	 */
	@Override
	public void delete(Product product) {
		productDAO.delete(product);
	}

	/* (non-Javadoc)
	 * @see com.crowdaccent.service.ProductService#getById(java.lang.String)
	 */
	@Override
	public Product getById(String id) {
		return productDAO.getById(id);
	}

	/* (non-Javadoc)
	 * @see com.crowdaccent.service.ProductService#getAll()
	 */
	@Override
	public List<Product> getAll() {
		return productDAO.getAll();
	}

	/**
	 * @param productDAO the productDAO to set
	 */
	@Autowired
	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	/* (non-Javadoc)
	 * @see com.crowdaccent.service.ProductService#getNumProducts(int)
	 */
	@Override
	public List<Product> getNumProducts(int number) {
		return productDAO.getNumProducts(number);
	}

}
