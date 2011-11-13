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
	/**
	 * @param id
	 * @return
	 */
	public Product createHIT(String id);
    /**
     * @param id
     * @return
     */
    public Product createIntroductionHIT(String id);
    /**
     * @param id
     * @return
     */
    public Product createIntroductionHITWithImage(String id);

    /** Returns number of products with valid image and URL.
	 * @param number number of products to return.
	 * @return List of Products.
	 */
	public List<Product> getNumValidProducts(int number);
	
}
