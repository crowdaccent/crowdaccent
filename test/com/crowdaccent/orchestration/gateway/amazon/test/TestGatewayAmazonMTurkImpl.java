package com.crowdaccent.orchestration.gateway.amazon.test;

import junit.textui.TestRunner;

import com.amazonaws.mturk.service.exception.ServiceException;
import com.amazonaws.mturk.test.TestRequesterServiceRaw;

public class TestGatewayAmazonMTurkImpl extends TestCase {

	public static void main(String[] args) {
		    TestRunner.run(TestGatewayAmazonMTurkImpl.class);
	}

	public TestGatewayAmazonMTurkImpl(String arg0) {
		super(arg0);
	}

	public void testCreateProductCategorizationHIT() throws ServiceException {
		GatewayAmazonMTurkImpl gwMturk = GatewayAmazonMTurkImpl();
		
		HRequest request = new HRequest();
		
		hRequest.setHitTypeId(null);
	    hRequest.setTitle("Product Categorization"); 
	    hRequest.setDescription("Provide possible categories of the products"), 
	    hRequest.getKeywords("women,fashion,garment"), // keywords 
	    hRequest.setQuestion("Select all the possible categories that describes the given product"), 
	    hRequest.setReward(reward), 
	    hRequest.setAssignmentDurationInSecs(3*60*60), 
	    hRequest.setAutoApprovalDelaySecs(72*60*60), 
	    hRequest.setLifeTimeInSeconds(24*60*60), 
	    hRequest.setMaxAssignments(10), 
	    hRequest.setRequestorAnnotation(null);  
	    hRequest.setQualificationRequirement(null);
	    hRequest.setResponseGroup(null);
		
		HIT hit = gwMTurk.createProductCategorizationHIT(hRequest);
		
		assertNotNull(hit);
		assertNotNull(hit.getHITId());
		
	}
}
