/**
 * 
 */
package com.crowdaccent.orchestration.gateway.amazon.test;

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
import com.crowdaccent.repository.ProductDAO;

/**
 * @author kbhalla
 * 
 */
@TransactionConfiguration(defaultRollback = false, transactionManager = "transactionManager")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:**/applicationContext.xml" })
public class TestProductDAO extends
		AbstractTransactionalJUnit4SpringContextTests {
	private ProductDAO productDAO;
	private Long id;
	private static final Logger _log = LoggerFactory
			.getLogger(TestProductDAO.class);

	@Autowired
	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	@Test
	public void createProduct() {
		Product p = new Product();
		p.setCategory("apparal");
		productDAO.save(p);
		id = p.getId();
		assertNotNull(p.getId());
	}

	@Test
	public void listProduct() {
		List<Product> products;
		products = productDAO.getAll();
		assertNotNull(products);
		_log.info("Products = " + products.size());
	}

	@Test
	public void delete() {
		Product p = new Product();
		p.setId(id);
		productDAO.delete(p);
	}
}
