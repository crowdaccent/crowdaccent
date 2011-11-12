package com.crowdaccent.orchestration.gateway.amazon.test;

import junit.framework.TestCase;

import org.junit.Test;

import com.crowdaccent.orchestration.gateway.Gateway;
import com.crowdaccent.orchestration.gateway.HITRequest;
import com.crowdaccent.orchestration.gateway.HITResponse;
import com.crowdaccent.orchestration.gateway.amazon.GatewayAmazonMTurkImpl;
import com.crowdaccent.orchestration.gateway.amazon.Overview;
import com.crowdaccent.orchestration.gateway.amazon.Question;

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

	public void testCreateIntroductionHIT() throws Exception {
        Gateway gateway = new GatewayAmazonMTurkImpl();
        
        HITRequest hRequest = new HITRequest();
        
        //hRequest.setHITTypeId("");
        hRequest.setTitle("Product Image and Category Validation"); 
        hRequest.setDescription("Provide possible categories of the products");
        hRequest.setKeywords("women,fashion,garment"); // keywords 
        //hRequest.setQuestion("Select all the possible categories that describes the given product"); 
        hRequest.setReward(.1);
        hRequest.setAssignmentDurationInSecs((long) (3*60*60));
        hRequest.setAutoApprovalDelaySecs((long) (72*60*60));
        hRequest.setLifeTimeInSeconds(new Long(24*60*60)); 
        hRequest.setMaxAssignments(10); 
        hRequest.setRequestorAnnotation(null);  
        hRequest.setQualificationRequirement(null);
        hRequest.setResponseGroup(null);
        hRequest.setDisplayName("Testing Products");
        
        Overview overview = new Overview();
        overview.setTitle("Product Image URL and Category Validation");
        overview.setQuestion("Instructions:");
        String instructions[] = new String[1];
        instructions[0] = "Please check if the javascript is enabled on your browser.";
        overview.setInstructions(instructions);
        hRequest.setOverviewContent(overview);
        
        Question question[] = new Question[3];
        question[0] = new Question();
        question[1] = new Question();
        question[2] = new Question();

        System.out.println("Question Before ::" + question.length + " : " + question[0]);
        question[0].setQuestionId(1);
        question[0].setDisplayName("Image URL");
        question[0].setRequired(true);
        question[0].setTitle("Image URL");
        question[0].setQuestion("Please copy and paste the url : ${image_url} on your browser and press enter. Then answer the following");

        String items1[] = new String[3];
        items1[0] = "Does the image downloads on Firefox browser without javascript and page not found error?";
        items1[1] = "Does the image downloads on IE browser without javascript and page not found error?";
        items1[2] = "Does the image downloads on Chrome browser without javascript and page not found error?";
        question[0].setItems(items1);

        question[1].setQuestionId(2);
        question[1].setDisplayName("Category Validation");
        question[1].setRequired(true);
        question[1].setTitle("Category Validation");
        question[1].setQuestion("The existing categorization hierarchy associated with the product is : ${existing_categories}");

        String items2[] = new String[3];
        items2[0] = "Is this the correct category associated with the product in the image?";
        items2[1] = "Is this the correct category associated with the product in the image?";
        items2[2] = "Is this the correct category associated with the product in the image?";
        question[1].setItems(items2);

        question[2].setQuestionId(3);
        question[2].setQuestion("Provide provide your comments to help us improve our tasks");

        System.out.println("Question After ::" + question.toString());
        hRequest.setQuestionContent(question);
        
        HITResponse hit = gateway.createIntroductionHIT(hRequest);
        
        assertNotNull(hit);
        assertNotNull(hit.getSyncResponse().getHITId());

        System.out.println("Created HIT: " + hit.getSyncResponse().getHITId());
        System.out.println("HIT location: ");
        System.out.println(gateway.getWebsiteURL() + "/mturk/preview?groupId=" + hit.getSyncResponse().getHITTypeId());
    }
}
