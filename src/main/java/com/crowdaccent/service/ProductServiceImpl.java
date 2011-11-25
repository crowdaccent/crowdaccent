/**
 * 
 */
package com.crowdaccent.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.axis.utils.XMLUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.mturk.requester.Comparator;
import com.amazonaws.mturk.requester.HIT;
import com.amazonaws.mturk.requester.QualificationRequirement;
import com.crowdaccent.entity.Hit;
import com.crowdaccent.entity.Product;
import com.crowdaccent.orchestration.gateway.Gateway;
import com.crowdaccent.orchestration.gateway.HITRequest;
import com.crowdaccent.orchestration.gateway.amazon.GatewayAmazonMTurkImpl;
import com.crowdaccent.orchestration.gateway.amazon.Overview;
import com.crowdaccent.orchestration.gateway.amazon.Question;
import com.crowdaccent.repository.HitDAO;
import com.crowdaccent.repository.ProductDAO;

/**
 * @author kbhalla
 *
 */
@Service
public class ProductServiceImpl implements ProductService {

    private static final String[] DEFAULT_ASSIGNMENT_RESPONSE_GROUP = new String [] { 
        "Minimal", 
        "AssignmentFeedback" 
    };

    private static final String[] DEFAULT_HIT_RESPONSE_GROUP = new String [] { 
        "Minimal", 
        "HITDetail", 
        "HITQuestion", 
        "HITAssignmentSummary" 
    };    
    
    private ProductDAO productDAO;
	private HitDAO hitDAO;
	
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
		hrequest.setRequestorAnnotation("/crowdaccent?lowcomplexity=1");
        
		QualificationRequirement qualificationRequirement[] = new QualificationRequirement[1];
        qualificationRequirement[0] = new QualificationRequirement();
        qualificationRequirement[0].setQualificationTypeId("00000000000000000060");
        qualificationRequirement[0].setComparator(Comparator.EqualTo);
        qualificationRequirement[0].setIntegerValue(1);
        
        hrequest.setQualificationRequirement(qualificationRequirement);
		hrequest.setResponseGroup(DEFAULT_HIT_RESPONSE_GROUP);
		HIT hit = gw.createBasicFreeTextHIT(hrequest);
		
		Hit h = new Hit();
		h.setHit_id(hit.getHITId());
		h.setHit_type_id(hit.getHITTypeId());
		h.setTitle(hit.getTitle());
		h.setDescription(hit.getDescription());
		h.setKeywords(hit.getKeywords());
		
		hitDAO.save(h);
	    p.setDateCreated(new Date());
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
        hRequest.setTitle("Product Image and Category Validation");
        hRequest.setDescription("Validate categories of the products");
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

        question[0].setQuestionId(1);
        question[0].setDisplayName("Image URL");
        question[0].setRequired(true);
        question[0].setTitle("Image URL");
        question[0].setQuestion("Please copy and paste the url : "+ p.getImageURL() + " on your browser and press enter. Then answer the following");
        
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
        question[1].setItems(items2);

        question[2].setQuestionId(3);
        question[2].setQuestion("Provide provide your comments to help us improve our tasks");

        hRequest.setQuestionContent(question);

        QualificationRequirement qualificationRequirement[] = new QualificationRequirement[1];
        qualificationRequirement[0] = new QualificationRequirement();
        qualificationRequirement[0].setQualificationTypeId("00000000000000000060");
        qualificationRequirement[0].setComparator(Comparator.EqualTo);
        qualificationRequirement[0].setIntegerValue(1);
 
        HIT hit = gw.createIntroductionHIT(hRequest);
        
        Hit h = new Hit();
        h.setHit_id(hit.getHITId());
        h.setHit_type_id(hit.getHITTypeId());
        h.setTitle(hit.getTitle());
        h.setDescription(hit.getDescription());
        h.setKeywords(hit.getKeywords());
        
        hitDAO.save(h);
        p.setDateCreated(new Date());
        this.save(p);
        return p;
    }


    /* (non-Javadoc)
     * @see com.crowdaccent.service.ProductService#createIntroductionHIT(java.lang.String)
     */
    @Override
    public Product createIntroductionHITWithImage(String id) {
        Product p = this.getById(id);
        Gateway gw = new GatewayAmazonMTurkImpl();
        HITRequest hRequest = new HITRequest();
        hRequest.setTitle("Product Image and Category Validation");
        hRequest.setDescription("Validate categories of the products");
        hRequest.setKeywords(p.getCategory()); // keywords
        hRequest.setReward(.1);
        hRequest.setAssignmentDurationInSecs((long) (3 * 60 * 60));
        hRequest.setAutoApprovalDelaySecs((long) (72 * 60 * 60));
        hRequest.setLifeTimeInSeconds(new Long(24 * 60 * 60));
        hRequest.setMaxAssignments(10);
        hRequest.setRequestorAnnotation(null);
        hRequest.setQualificationRequirement(null);
        hRequest.setResponseGroup(null);
        hRequest.setDisplayName("Testing Products");
        
        Overview overview = new Overview();
        overview.setTitle("Product Image URL and Category Validation");
        
        String information[] = new String[9];
        information[0] = "Image :";
        information[1] = XMLUtils.xmlEncodeString(p.getImageURL());
        information[2] = XMLUtils.xmlEncodeString(p.getSubject());
        information[3] = "300";
        information[4] = "400";
        information[5] = "image-id";
        information[6] = "1";
        information[7] = XMLUtils.xmlEncodeString(p.getSubject());
        information[8] = XMLUtils.xmlEncodeString(p.getSummary());
        
        overview.setInformation(information);
        hRequest.setOverviewContent(overview);
        
        Question question[] = new Question[3];
        question[0] = new Question();
        question[1] = new Question();
        question[2] = new Question();

        question[0].setQuestionId(1);
        question[0].setDisplayName("Image URL");
        question[0].setRequired(true);
        question[0].setQuestion("Now answer the following questions:");

        String items1[] = new String[2];
        items1[0] = "Does the image loads correctly for you?";
        items1[1] = "Does the the title and description matches with the image?";
        question[0].setItems(items1);

        question[1].setQuestionId(2);
        question[1].setDisplayName("Category Validation");
        question[1].setRequired(true);
        question[1].setQuestion("The existing categorization hierarchy associated with the product is : "  + XMLUtils.xmlEncodeString(p.getCategory()));
        List<String> categories = new ArrayList<String>();
        categories = Arrays.asList(p.getCategory().split(">"));

        String items2[] = new String[categories.size()];
        int questionNo = 0;
        for (String category: categories){
        	items2[questionNo++] = "Is "+ XMLUtils.xmlEncodeString(category) +"  the correct category associated with the product in the image?";
        }
        
        question[1].setItems(items2);

        question[2].setQuestionId(3);
        question[2].setQuestion("Please provide us your comments to help improve our tasks");

        hRequest.setQuestionContent(question);
        
        QualificationRequirement qualificationRequirement[] = new QualificationRequirement[1];
        qualificationRequirement[0] = new QualificationRequirement();
        qualificationRequirement[0].setQualificationTypeId("00000000000000000060");
        qualificationRequirement[0].setComparator(Comparator.EqualTo);
        qualificationRequirement[0].setIntegerValue(1);
        
        hRequest.setQualificationRequirement(qualificationRequirement);
        HIT hit = gw.createIntroductionHITWithImage(hRequest);
        
        Hit h = new Hit();
        h.setHit_id(hit.getHITId());
        h.setHit_type_id(hit.getHITTypeId());
        h.setTitle(hit.getTitle());
        h.setDescription(hit.getDescription());
        h.setKeywords(hit.getKeywords());
        h.setHit_url(gw.getWebsiteURL());
        
        if(hit.getHITStatus() != null) {
            h.setHit_status(hit.getHITStatus().getValue());
        }
        if(hit.getHITReviewStatus() != null) {
          h.setHit_review_status(hit.getHITReviewStatus().getValue());
        }
        if(hit.getCreationTime() != null) {
          h.setCreation_time(hit.getCreationTime().getTime());
        }
        if(hit.getAutoApprovalDelayInSeconds() != null) {
            h.setAuto_approval_delay_in_secs(hit.getAutoApprovalDelayInSeconds().intValue());
        }
        if(hit.getMaxAssignments() != null) {
            h.setMax_assignments(hit.getMaxAssignments());
        }
        if(hit.getReward() != null) {
            h.setReward(hit.getReward().getAmount().doubleValue());
        }
        if(hit.getNumberOfSimilarHITs() != null) {
            h.setNum_similar_hits(hit.getNumberOfSimilarHITs());
        }
        if(hit.getNumberOfAssignmentsAvailable() != null) {
            h.setNumber_of_assignments_available(hit.getNumberOfAssignmentsAvailable());
        }
        if(hit.getNumberOfAssignmentsAvailable() != null) {
            h.setNumber_of_assignments_available(hit.getNumberOfAssignmentsAvailable());
        }
        if(hit.getNumberOfAssignmentsCompleted() != null) {
            h.setNumber_of_assignments_completed(hit.getNumberOfAssignmentsCompleted());
        }
        if(hit.getNumberOfAssignmentsPending() != null) {
            h.setNumber_of_assignments_pending(hit.getNumberOfAssignmentsPending());
        }

        hitDAO.save(h);
        p.setDateCreated(new Date());
        this.save(p);
        return p;
    }

	/* (non-Javadoc)
	 * @see com.crowdaccent.service.ProductService#getNumValidProducts(int)
	 */
	@Override
	public List<Product> getNumValidProducts(int number) {
		return this.productDAO.getNumValidProducts(number);
	}
	/**
	 * @param hitDAO the hitDAO to set
	 */
	@Autowired
	public void setHitDAO(HitDAO hitDAO) {
		this.hitDAO = hitDAO;
	}

	/* (non-Javadoc)
	 * @see com.crowdaccent.service.ProductService#findProductEntries(int, int)
	 */
	@Override
	public List<Product> findProductEntries(int firstResult, int sizeNo) {
		return productDAO.findProductEntries(firstResult,sizeNo);
	}

	/* (non-Javadoc)
	 * @see com.crowdaccent.service.ProductService#countProducts()
	 */
	@Override
	public Float countProducts() {
		return productDAO.countProducts();
	}
}
