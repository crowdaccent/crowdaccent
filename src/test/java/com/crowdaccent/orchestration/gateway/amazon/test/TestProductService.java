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

import com.crowdaccent.entity.Product;
import com.crowdaccent.service.ProductService;

/**
 * @author kbhalla
 * 
 */
@TransactionConfiguration(defaultRollback = false, transactionManager = "transactionManager")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:**/applicationContext.xml" })
public class TestProductService extends
		AbstractTransactionalJUnit4SpringContextTests {
	private ProductService productService;

	/**
	 * @param productService the productService to set
	 */
	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	@Test
	public void listProducts(){
		List<Product> products = productService.getNumProducts(5);
		for(Product p:products){
			System.out.println("Product " + p.getId() + p.getImageURL());
		}
	}

}
