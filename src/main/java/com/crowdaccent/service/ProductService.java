/**
 * 
 */
package com.crowdaccent.service;

import java.util.List;

import com.crowdaccent.entity.Product;

/**
 * @author kbhalla
 *
 */
public interface ProductService {
	/**
	 * @param product
	 */
	public void save(Product product);
	/**
	 * @param product
	 */
	public void delete(Product product);
	/**
	 * @param id
	 * @return
	 */
	public Product getById(String id);
	/**
	 * @return
	 */
	public List<Product> getAll();
	/**
	 * @return
	 */
	public List<Product> getNumProducts(int number);

}
