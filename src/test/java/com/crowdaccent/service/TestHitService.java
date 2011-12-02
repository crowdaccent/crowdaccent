/**
 * 
 */
package com.crowdaccent.service;

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

import com.crowdaccent.entity.Hit;

/**
 * @author kbhalla
 * 
 */
@TransactionConfiguration(defaultRollback = false, transactionManager = "transactionManager")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:**/applicationContext.xml" })
public class TestHitService extends
		AbstractTransactionalJUnit4SpringContextTests {
	private @Autowired HitService hitService;
	private static final Logger _log = LoggerFactory
			.getLogger(TestHitService.class);

	@Test
	public void listHits() {
		List<Hit> hits = hitService.getAllByProduct(new Long(2));
		int number = 0;
		for (Hit h : hits) {
			_log.info("Hit info " + h.getHit_id());
			number++;
		}
		_log.info("Total number of hits for this product in the database " + number);
	}
	@Test
	public void getHIT(){
		Hit hit = hitService.getByHitId("2X7GSNTL0XULSMA45EUMPDX5JWAM0O");
	    _log.info("Hit info: " + hit.getHit_id());
	}
	@Test
	public void getResults(){
		Hit hit = hitService.getResultsForHIT("2X7GSNTL0XULSMA45EUMPDX5JWAM0O");
		_log.info("Hit info " + hit.getHit_id());
	}

}
