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

	/** Returns limited number of products.
	 * @param number number of products to return.
	 * @return List of Products.
	 */
	public List<Product> getNumProducts(int number);
	
	/** Returns number of products with valid image and URL.
	 * @param number number of products to return.
	 * @return List of Products.
	 */
	public List<Product> getNumValidProducts(int number);

}
