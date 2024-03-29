/**
 * 
 */
package com.crowdaccent.service;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.crowdaccent.entity.Product;

/**
 * @author kbhalla
 * 
 */
@TransactionConfiguration(defaultRollback = false, transactionManager = "transactionManager")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:**/applicationContext.xml" })
public class TestProductService extends
		AbstractTransactionalJUnit4SpringContextTests {
	private @Autowired ProductService productService;
	private static final Logger _log = LoggerFactory
			.getLogger(TestProductService.class);

	@Test
	public void listProducts() {
		List<Product> products = productService.getNumValidProducts(10000);
		int number = 0;
		for (Product p : products) {
			assertNotNull(p.getCategory());
			assertNotNull(p.getImageURL());
			number++;
		}
		_log.info("Total number of valid products in database " + number);
	}

	@Test
	public void createHIT() {
		List<Product> products = productService.getNumProducts(5);
		for (Product p : products) {
			if (p.getImageURL() != null) {
				// productService.createHIT(p.getId()+"");
				break;
			}
		}
	}

	@Test
	public void createIntroductionHIT() {
		List<Product> products = productService.getNumProducts(5);
		for (Product p : products) {
			if (p.getImageURL() != null) {
				// productService.createIntroductionHIT(p.getId()+"");
				break;
			}
		}
	}

	@Test
	public void createIntroductionHITWithImage() {
		List<Product> products = productService.getNumValidProducts(1);
		int num = 0;
		for (Product p : products) {
			_log.info("Creating HIT for product id " + p.getId() + " and category : " + p.getCategory());
			this.productService.createIntroductionHITWithImage(p.getId() + "");
			assertNotNull(p.getImageURL());
			num++;
		}
		_log.info("Total hits created = " + num);
	}
}
