/**
 * 
 */
package com.crowdaccent.orchestration.gateway.amazon.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.crowdaccent.entity.Product;
import com.crowdaccent.repository.ProductDAO;

/**
 * @author kbhalla
 *
 */
@TransactionConfiguration
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:**/applicationContext.xml"})
public class TestProductDAO extends
		AbstractTransactionalJUnit4SpringContextTests {
	private ProductDAO productDAO;
	
	@Autowired
	public void setProductDAO(ProductDAO productDAO){
		this.productDAO = productDAO;
	}
	@Test
	public void createProduct(){
		Product p = new Product();
		p.setCategory("apparal");
		productDAO.save(p);
	}
	@Test
	public void listProduct(){
		List<Product> products;
		products = productDAO.getAll();
		System.out.println("Fetched " + products.size());
		for(Product p: products){
			System.out.println("ID " + p.getId() + " Cat = " + p.getCategory());
		}
	}
}
