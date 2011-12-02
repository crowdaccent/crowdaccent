/**
 * 
 */
package com.crowdaccent.service;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * @author kbhalla
 * 
 */
@TransactionConfiguration(defaultRollback = false, transactionManager = "transactionManager")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:**/applicationContext.xml" })
public class TestScheduledTasks extends
		AbstractTransactionalJUnit4SpringContextTests {
	private @Autowired ScheduledTasks scheduledTasks;
	private static final Logger _log = LoggerFactory
			.getLogger(TestScheduledTasks.class);
	
	@Test
	public void testgetAndUpdateActiveAndExpiredHITs(){
		_log.info("Up and running at " + new Date());
		try {
			Thread.currentThread().sleep(1L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
