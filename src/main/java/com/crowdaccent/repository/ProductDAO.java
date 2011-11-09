/**
 * 
 */
package com.crowdaccent.repository;

import java.util.List;

import com.crowdaccent.entity.Product;

/**
 * @author kbhalla
 *
 */
public interface ProductDAO {
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
}
