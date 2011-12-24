/**
 * 
 */
package com.crowdaccent.service;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.crowdaccent.entity.ContentModeration;

/**
 * @author kbhalla
 * 
 */
@TransactionConfiguration(defaultRollback = false, transactionManager = "transactionManager")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:**/applicationContext.xml" })
public class TestContentModerationService extends
		AbstractTransactionalJUnit4SpringContextTests {
	private @Autowired ContentModerationService contentModerationService;
	private static final Logger _log = LoggerFactory
			.getLogger(TestContentModerationService.class);

	@Test
	public void createHIT() {
		Collection<ContentModeration> contentModerations = contentModerationService.findContentModerationEntries(0, 1);
		int num = 0;
		for (ContentModeration c : contentModerations) {
			this.contentModerationService.createHIT(c.getId()+"");
			num++;
		}
		_log.info("Total hits created = " + num);
	}
}
