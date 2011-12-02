/**
 * 
 */
package com.crowdaccent.repository;

import static org.junit.Assert.assertNotNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

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
public class TestResultDAO extends
		AbstractTransactionalJUnit4SpringContextTests {
	private @Autowired ResultDAO resultDAO;
	private Long id;
	private static final Logger _log = LoggerFactory
			.getLogger(TestResultDAO.class);

	@Test
	public void createResult() throws ParseException {
		Result r = new Result();
		r.setHit_type_id("2NDW4ZMAZHHRSR21UJEN2RH1KH2AJ7");
		r.setHitID("244HGYQ4J3NACCYQGA6O5YIT14AOBN");
		r.setTitle("Product Image and Category Validation");
		r.setDescription("Answer questions about the product image, title, description and validate the existing categorization of the product");
		r.setKeywords("product, image, url, validation, categorization, crowdaccent");
		r.setReward(0.02);
		DateFormat df = new SimpleDateFormat("E MMM dd HH:mm:ss z y");
		Date creation_time = df.parse("Sun Nov 21 10:03:46 GMT 2011");
		r.setCreation_time(creation_time);
		r.setMax_assignments(101);
		r.setRequester_annotation("crowdaccent?hit=1");
		r.setAuto_approval_delay_in_secs(180);
		DateFormat exp = new SimpleDateFormat("E MMM dd HH:mm:ss z y");
        Date expiration_time = exp.parse("Sun Nov 21 10:03:46 GMT 2011");
        r.setExpiration_time(expiration_time);
		r.setNum_similar_hits(1);
		r.setLife_time_in_secs(180);
		r.setAssignment_id("258ULF395SW6OMOQMYEYUJBYFOKRD4");
		r.setWorker_id("AQT9CATZVWFWK");
		r.setAssignment_status("Submitted");
		DateFormat acc = new SimpleDateFormat("E MMM dd HH:mm:ss z y");
        Date accept_time = acc.parse("Sun Nov 21 10:03:46 GMT 2011");
        r.setAccept_time(accept_time);
        DateFormat sub = new SimpleDateFormat("E MMM dd HH:mm:ss z y");
        Date submit_time = sub.parse("Sun Nov 21 10:03:46 GMT 2011");
        r.setAccept_time(submit_time);
        DateFormat auto = new SimpleDateFormat("E MMM dd HH:mm:ss z y");
        Date auto_approval_time = auto.parse("Sun Nov 21 10:03:46 GMT 2011");
        r.setAccept_time(auto_approval_time);
        DateFormat app = new SimpleDateFormat("E MMM dd HH:mm:ss z y");
        Date approval_time = app.parse("Sun Nov 21 00:00:00 GMT 2011");
        r.setApproval_time(approval_time);
		DateFormat rej = new SimpleDateFormat("E MMM dd HH:mm:ss z y");
        Date rejection_time = rej.parse("Sun Nov 21 10:03:46 GMT 2011");
        r.setRejection_time(rejection_time);
        r.setRequester_feedback("Testing");
        r.setWork_time_in_secs(120);
        r.setLife_time_approval_rate("100%(1/1)");
        r.setLast_30_days_approval_rate("30");
        r.setLast_7_days_approval_rate("7");
        r.setAnswers("A1:1,A2:2,A3:3");
        
		resultDAO.save(r);
		id = r.getId();
		assertNotNull(r.getId());
	}

	@Test
	public void listResult() {
		List<Result> results;
		results = resultDAO.getAll();
		assertNotNull(results);
		for (Result r: results){
			SimpleDateFormat sdf = new SimpleDateFormat("E MMM dd HH:mm:ss z y");
			TimeZone tz = TimeZone.getTimeZone("GMT");
			sdf.setTimeZone(tz);
			_log.info("Approval date " + sdf.format(r.getApproval_time()));
		}
		_log.info("Results = " + results.size());
	}

    @Test
    public void getNumResults() {
        List<Result> results;
        results = resultDAO.getNumResults(2);
        assertNotNull(results);
        for (Result r: results){
            _log.info("Results : " + r.getAnswers());
        }
        _log.info("Results = " + results.size());
    }
	
	@Test
	public void delete() {
		Result r = new Result();
		r.setId(id);
		resultDAO.delete(r);
	}
}
