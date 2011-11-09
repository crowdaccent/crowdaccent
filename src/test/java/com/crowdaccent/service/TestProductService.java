/**
 * 
 */
package com.crowdaccent.service;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.crowdaccent.entity.Product;
import com.crowdaccent.orchestration.gateway.Gateway;
import com.crowdaccent.orchestration.gateway.HITRequest;
import com.crowdaccent.orchestration.gateway.HITResponse;
import com.crowdaccent.orchestration.gateway.amazon.GatewayAmazonMTurkImpl;
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
	 * @param productService
	 *            the productService to set
	 */
	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@Test
	public void listProducts() {
		List<Product> products = productService.getNumProducts(5);
		for (Product p : products) {
			System.out.println("Product " + p.getId() + p.getImageURL());
		}
	}

	@Test
	public void createHIT() {
		Gateway gateway = new GatewayAmazonMTurkImpl();
		List<Product> products = productService.getNumProducts(5);
		for (Product p : products) {
			if (p.getImageURL() != null) {

				HITRequest hRequest = new HITRequest();

				hRequest.setTitle(p.getSubject());
				hRequest.setDescription(p.getSummary());
				hRequest.setKeywords(p.getCategory()); // keywords
				hRequest.setQuestion("Check if this product belongs to " + p.getCategory());
				hRequest.setReward(.1);
				hRequest.setAssignmentDurationInSecs((long) (3 * 60 * 60));
				hRequest.setAutoApprovalDelaySecs((long) (72 * 60 * 60));
				hRequest.setLifeTimeInSeconds(new Long(24 * 60 * 60));
				hRequest.setMaxAssignments(10);
				hRequest.setRequestorAnnotation(null);
				hRequest.setQualificationRequirement(null);
				hRequest.setResponseGroup(null);

				HITResponse hit = gateway.createBasicFreeTextHIT(hRequest);

				assertNotNull(hit);
				assertNotNull(hit.getSyncResponse().getHITId());

				System.out.println(gateway.getWebsiteURL()
						+ "/mturk/preview?groupId="
						+ hit.getSyncResponse().getHITTypeId());

				break;
			}
		}

	}

}
