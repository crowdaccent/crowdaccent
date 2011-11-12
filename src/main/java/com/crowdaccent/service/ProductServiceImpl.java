/**
 * 
 */
package com.crowdaccent.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crowdaccent.entity.Product;
import com.crowdaccent.orchestration.gateway.Gateway;
import com.crowdaccent.orchestration.gateway.HITRequest;
import com.crowdaccent.orchestration.gateway.HITResponse;
import com.crowdaccent.orchestration.gateway.amazon.GatewayAmazonMTurkImpl;
import com.crowdaccent.orchestration.gateway.amazon.Overview;
import com.crowdaccent.orchestration.gateway.amazon.Question;
import com.crowdaccent.repository.ProductDAO;

/**
 * @author kbhalla
 *
 */
@Service
public class ProductServiceImpl implements ProductService {
	private ProductDAO productDAO;
	
	/* (non-Javadoc)
	 * @see com.crowdaccent.service.ProductService#save(com.crowdaccent.entity.Product)
	 */
	@Override
	public void save(Product product) {
		productDAO.save(product);
	}

	/* (non-Javadoc)
	 * @see com.crowdaccent.service.ProductService#delete(com.crowdaccent.entity.Product)
	 */
	@Override
	public void delete(Product product) {
		productDAO.delete(product);
	}

	/* (non-Javadoc)
	 * @see com.crowdaccent.service.ProductService#getById(java.lang.String)
	 */
	@Override
	public Product getById(String id) {
		return productDAO.getById(id);
	}

	/* (non-Javadoc)
	 * @see com.crowdaccent.service.ProductService#getAll()
	 */
	@Override
	public List<Product> getAll() {
		return productDAO.getAll();
	}

	/**
	 * @param productDAO the productDAO to set
	 */
	@Autowired
	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	/* (non-Javadoc)
	 * @see com.crowdaccent.service.ProductService#getNumProducts(int)
	 */
	@Override
	public List<Product> getNumProducts(int number) {
		return productDAO.getNumProducts(number);
	}

	/* (non-Javadoc)
	 * @see com.crowdaccent.service.ProductService#createHIT(java.lang.String)
	 */
	@Override
	public Product createHIT(String id) {
		Product p = this.getById(id);
		Gateway gw = new GatewayAmazonMTurkImpl();
		HITRequest hrequest = new HITRequest();
		hrequest.setTitle(p.getSubject());
		hrequest.setDescription(p.getSummary());
		hrequest.setKeywords(p.getCategory()); // keywords
		hrequest.setQuestion("Check if this product belongs to " + p.getCategory());
		hrequest.setReward(.1);
		hrequest.setAssignmentDurationInSecs((long) (3 * 60 * 60));
		hrequest.setAutoApprovalDelaySecs((long) (72 * 60 * 60));
		hrequest.setLifeTimeInSeconds(new Long(24 * 60 * 60));
		hrequest.setMaxAssignments(10);
		hrequest.setRequestorAnnotation(null);
		hrequest.setQualificationRequirement(null);
		hrequest.setResponseGroup(null);
		HITResponse hit = gw.createBasicFreeTextHIT(hrequest);
		
		p.setHitCreated(new Date());
		p.setHitURL(gw.getWebsiteURL()
				+ "/mturk/preview?groupId="
				+ hit.getSyncResponse().getHITTypeId());
		
		this.save(p);
		return p;
	}

	/* (non-Javadoc)
     * @see com.crowdaccent.service.ProductService#createIntroductionHIT(java.lang.String)
     */
    @Override
    public Product createIntroductionHIT(String id) {
        Product p = this.getById(id);
        Gateway gw = new GatewayAmazonMTurkImpl();
        HITRequest hRequest = new HITRequest();
        hRequest.setTitle(p.getSubject());
        hRequest.setDescription(p.getSummary());
        hRequest.setKeywords(p.getCategory()); // keywords
        hRequest.setReward(.1);
        hRequest.setAssignmentDurationInSecs((long) (3 * 60 * 60));
        hRequest.setAutoApprovalDelaySecs((long) (72 * 60 * 60));
        hRequest.setLifeTimeInSeconds(new Long(24 * 60 * 60));
        hRequest.setMaxAssignments(10);
        hRequest.setRequestorAnnotation(null);
        hRequest.setQualificationRequirement(null);
        hRequest.setResponseGroup(null);

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
        question[0].setQuestion("Please copy and paste the url : "+ p.getImageURL() + "on your browser and press enter. Then answer the following");
        
        String items[] = new String[3];
        items[0] = "Does the image downloads on Firefox browser without javascript and page not found error?";
        items[1] = "Does the image downloads on IE browser without javascript and page not found error?";
        items[2] = "Does the image downloads on Chrome browser without javascript and page not found error?";
        question[0].setItems(items);

        question[1].setQuestionId(2);
        question[1].setDisplayName("Category Validation");
        question[1].setRequired(true);
        question[1].setTitle("Category Validation");
        question[1].setQuestion("The existing categorization hierarchy associated with the product is : " + p.getCategory());

        String items2[] = new String[3];
        items2[0] = "Is this the correct category associated with the product in the image?";
        items2[1] = "Is this the correct category associated with the product in the image?";
        items2[2] = "Is this the correct category associated with the product in the image?";
        question[1].setItems(items);

        question[2].setQuestionId(3);
        question[2].setQuestion("Provide provide your comments to help us improve our tasks");

        System.out.println("Question After ::" + question.toString());
        hRequest.setQuestionContent(question);
        
        HITResponse hit = gw.createIntroductionHIT(hRequest);
        
        p.setHitCreated(new Date());
        p.setHitURL(gw.getWebsiteURL()
                + "/mturk/preview?groupId="
                + hit.getSyncResponse().getHITTypeId());
        
        this.save(p);
        return p;
    }

}
