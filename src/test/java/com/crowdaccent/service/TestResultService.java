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

import com.crowdaccent.entity.Result;

/**
 * @author mkutare
 * 
 */
@TransactionConfiguration(defaultRollback = false, transactionManager = "transactionManager")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:**/applicationContext.xml" })
public class TestResultService extends
		AbstractTransactionalJUnit4SpringContextTests {
	private ResultService resultService;
	private static final Logger _log = LoggerFactory
			.getLogger(TestResultService.class);

	/**
	 * @param resultService  the resultService to set
	 */
	@Autowired
	public void setResultService(ResultService resultService) {
		this.resultService = resultService;
	}

	@Test
	public void listResults() {
		List<Result> results = resultService.getNumResults(1);
		int number = 0;
		for (Result r : results) {
			_log.info("Result info " + r.getAnswers());
	        if(r.getAssignment_id() != null) {
	            assertNotNull(r.getAssignment_id());
	        }
			assertNotNull(r.getHitID());
			number++;
		}
		_log.info("Total number of answers in the database " + number);
	}

	@Test
	public void getNumResults() {
		List<Result> results = resultService.getNumResults(5);
		for (Result r : results) {
			assertNotNull(r.getHitID());
		}
	}
	
	@Test
    public void getResultsForHIT() {
        Result results = resultService.getResultsForHIT("2UVP8PJWKUDD939MXLU920B6YZCJ8N");
        _log.info("Result info " + results.getAnswers());
        if(results.getAssignment_id() != null) {
            assertNotNull(results.getAssignment_id());
        }
        assertNotNull(results.getHitID());
        _log.info("Total number of answers available for the HIT " + results.getNumber_of_assignments_available());
        _log.info("Total number of answers complete for the HIT " + results.getNumber_of_assignments_completed());
        _log.info("Total number of answers pending for the HIT " + results.getNumber_of_assignments_pending());
	}
}
