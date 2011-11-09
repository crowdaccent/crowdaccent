package com.crowdaccent.orchestration.gateway.amazon.test;

import junit.framework.TestCase;

import org.junit.Test;

import com.crowdaccent.orchestration.gateway.Gateway;
import com.crowdaccent.orchestration.gateway.HITRequest;
import com.crowdaccent.orchestration.gateway.HITResponse;
import com.crowdaccent.orchestration.gateway.amazon.GatewayAmazonMTurkImpl;

/** JUnit test case to test HIT creation using API.
 * @author kbhalla
 *
 */
public class TestGatewayAmazonMTurkImpl extends TestCase {
	
	/** Test to check HIT creation.
	 * @throws Exception
	 */
	@Test
	public void testCreateBasicFreeTextHIT() throws Exception {
		Gateway gateway = new GatewayAmazonMTurkImpl();
		
		HITRequest hRequest = new HITRequest();
		
		//hRequest.setHITTypeId("");
	    hRequest.setTitle("Product Categorization"); 
	    hRequest.setDescription("Provide possible categories of the products");
	    hRequest.setKeywords("women,fashion,garment"); // keywords 
	    hRequest.setQuestion("Select all the possible categories that describes the given product"); 
	    hRequest.setReward(.1);
	    hRequest.setAssignmentDurationInSecs((long) (3*60*60));
	    hRequest.setAutoApprovalDelaySecs((long) (72*60*60));
	    hRequest.setLifeTimeInSeconds(new Long(24*60*60)); 
	    hRequest.setMaxAssignments(10); 
	    hRequest.setRequestorAnnotation(null);  
	    hRequest.setQualificationRequirement(null);
	    hRequest.setResponseGroup(null);
		
		HITResponse hit = gateway.createBasicFreeTextHIT(hRequest);
		
		assertNotNull(hit);
		assertNotNull(hit.getSyncResponse().getHITId());

		System.out.println("Created HIT: " + hit.getSyncResponse().getHITId());
		System.out.println("HIT location: ");
		System.out.println(gateway.getWebsiteURL() + "/mturk/preview?groupId=" + hit.getSyncResponse().getHITTypeId());
		
	}
	
	public void testCreateComplexFreeTextHIT() throws Exception {
		Gateway gateway = new GatewayAmazonMTurkImpl();
		
		HITRequest hRequest = new HITRequest();
		
		//hRequest.setHITTypeId("");
	    hRequest.setTitle("Product Categorization"); 
	    hRequest.setDescription("Provide possible categories of the products");
	    hRequest.setKeywords("women,fashion,garment"); // keywords 
	    hRequest.setQuestion("Select all the possible categories that describes the given product"); 
	    hRequest.setReward(.1);
	    hRequest.setAssignmentDurationInSecs((long) (3*60*60));
	    hRequest.setAutoApprovalDelaySecs((long) (72*60*60));
	    hRequest.setLifeTimeInSeconds(new Long(24*60*60)); 
	    hRequest.setMaxAssignments(10); 
	    hRequest.setRequestorAnnotation(null);  
	    hRequest.setQualificationRequirement(null);
	    hRequest.setResponseGroup(null);
	    hRequest.setDisplayName("Testing Products");
	    String items[] = new String[5];
	    for (int i = 0; i < items.length; i++) {
	    	items[i]="Category-"+i;
	    }
	    hRequest.setListItems(items);
		
		HITResponse hit = gateway.createComplexFreeTextHIT(hRequest);
		
		assertNotNull(hit);
		assertNotNull(hit.getSyncResponse().getHITId());

		System.out.println("Created HIT: " + hit.getSyncResponse().getHITId());
		System.out.println("HIT location: ");
		System.out.println(gateway.getWebsiteURL() + "/mturk/preview?groupId=" + hit.getSyncResponse().getHITTypeId());
		
	}
	
}
