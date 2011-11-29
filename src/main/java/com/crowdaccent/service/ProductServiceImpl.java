/**
 * 
 */
package com.crowdaccent.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.mturk.addon.HITDataInput;
import com.amazonaws.mturk.addon.HITProperties;
import com.amazonaws.mturk.addon.HITQuestion;
import com.amazonaws.mturk.requester.Comparator;
import com.amazonaws.mturk.requester.HIT;
import com.amazonaws.mturk.requester.QualificationRequirement;
import com.crowdaccent.entity.Hit;
import com.crowdaccent.entity.Product;
import com.crowdaccent.orchestration.gateway.Gateway;
import com.crowdaccent.orchestration.gateway.HITDataInputReader;
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

	/**
	 * 
	 */
	private static final String PRODUCT_CATEGROY_QUESTION = "product_category.question.";

	/**
	 * 
	 */
	private static final String CATEGORY_DELIMITER = ">";

	/**
	 * 
	 */
	private static final String CATEGORY = "category";

	/**
	 * 
	 */
	private static final String SUMMARY = "summary";

	/**
	 * 
	 */
	private static final String SUBJECT = "subject";

	/**
	 * 
	 */
	private static final String IMAGE_URL = "imageURL";

	/**
	 * 
	 */
	private static final String PRODUCT_CATEGROY_PROPERTIES = "product_category/product_category.properties";

	private static final String[] DEFAULT_ASSIGNMENT_RESPONSE_GROUP = new String[] {
			"Minimal", "AssignmentFeedback" };

	private static final String[] DEFAULT_HIT_RESPONSE_GROUP = new String[] {
			"Minimal", "HITDetail", "HITQuestion", "HITAssignmentSummary" };

	private ProductDAO productDAO;
	private HitDAO hitDAO;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.crowdaccent.service.ProductService#save(com.crowdaccent.entity.Product
	 * )
	 */
	@Override
	public void save(Product product) {
		productDAO.save(product);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.crowdaccent.service.ProductService#delete(com.crowdaccent.entity.
	 * Product)
	 */
	@Override
	public void delete(Product product) {
		productDAO.delete(product);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.crowdaccent.service.ProductService#getById(java.lang.String)
	 */
	@Override
	public Product getById(String id) {
		return productDAO.getById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.crowdaccent.service.ProductService#getAll()
	 */
	@Override
	public List<Product> getAll() {
		return productDAO.getAll();
	}

	/**
	 * @param productDAO
	 *            the productDAO to set
	 */
	@Autowired
	public void setProductDAO(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.crowdaccent.service.ProductService#getNumProducts(int)
	 */
	@Override
	public List<Product> getNumProducts(int number) {
		return productDAO.getNumProducts(number);
	}

	/*
	 * (non-Javadoc)
	 * 
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
		hrequest.setQuestion("Check if this product belongs to "
				+ p.getCategory());
		hrequest.setReward(.1);
		hrequest.setAssignmentDurationInSecs((long) (3 * 60 * 60));
		hrequest.setAutoApprovalDelaySecs((long) (72 * 60 * 60));
		hrequest.setLifeTimeInSeconds(new Long(24 * 60 * 60));
		hrequest.setMaxAssignments(10);
		hrequest.setRequestorAnnotation("/crowdaccent?lowcomplexity=1");

		QualificationRequirement qualificationRequirement[] = new QualificationRequirement[1];
		qualificationRequirement[0] = new QualificationRequirement();
		qualificationRequirement[0]
				.setQualificationTypeId("00000000000000000060");
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.crowdaccent.service.ProductService#createIntroductionHIT(java.lang
	 * .String)
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
		question[0]
				.setQuestion("Please copy and paste the url : "
						+ p.getImageURL()
						+ " on your browser and press enter. Then answer the following");

		String items[] = new String[3];
		items[0] = "Does the image downloads on Firefox browser without javascript and page not found error?";
		items[1] = "Does the image downloads on IE browser without javascript and page not found error?";
		items[2] = "Does the image downloads on Chrome browser without javascript and page not found error?";
		question[0].setItems(items);

		question[1].setQuestionId(2);
		question[1].setDisplayName("Category Validation");
		question[1].setRequired(true);
		question[1].setTitle("Category Validation");
		question[1]
				.setQuestion("The existing categorization hierarchy associated with the product is : "
						+ p.getCategory());

		String items2[] = new String[3];
		items2[0] = "Is this the correct category associated with the product in the image?";
		items2[1] = "Is this the correct category associated with the product in the image?";
		items2[2] = "Is this the correct category associated with the product in the image?";
		question[1].setItems(items2);

		question[2].setQuestionId(3);
		question[2]
				.setQuestion("Provide provide your comments to help us improve our tasks");

		hRequest.setQuestionContent(question);

		QualificationRequirement qualificationRequirement[] = new QualificationRequirement[1];
		qualificationRequirement[0] = new QualificationRequirement();
		qualificationRequirement[0]
				.setQualificationTypeId("00000000000000000060");
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.crowdaccent.service.ProductService#createIntroductionHIT(java.lang
	 * .String)
	 */
	@Override
	public Product createIntroductionHITWithImage(String id) {
		Product p = this.getById(id);

		// Load HIT properties from file. TODO: To be loaded from the UI or
		// database later.
		Properties properties = new Properties();
		try {
			properties.load(this.getClass().getClassLoader()
					.getResourceAsStream(PRODUCT_CATEGROY_PROPERTIES));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		HITProperties hitProperties = new HITProperties(properties);

		List<String> categories = Arrays.asList(p.getCategory().split(
				CATEGORY_DELIMITER));

		List<String> fieldNames = new ArrayList<String>();
		fieldNames.add(IMAGE_URL);
		fieldNames.add(SUBJECT);
		fieldNames.add(SUMMARY);
		fieldNames.add(CATEGORY);

		List<String> rows = new ArrayList<String>();
		StringBuffer r = new StringBuffer();
		r.append(p.getImageURL()).append(HITDataInputReader.DEFAULT_DELIM);
		r.append(p.getSubject()).append(HITDataInputReader.DEFAULT_DELIM);
		r.append(p.getSummary()).append(HITDataInputReader.DEFAULT_DELIM);
		r.append(p.getCategory()).append(HITDataInputReader.DEFAULT_DELIM);

		int categoryNum = 0;
		for (String cat : categories) {
			fieldNames.add(CATEGORY + categoryNum++);
			r.append(cat).append(HITDataInputReader.DEFAULT_DELIM);
		}

		rows.add(r.toString());
		rows.add(r.toString());
		HITDataInput hitDataInputReader = new HITDataInputReader(fieldNames.toArray(new String[0]), rows.toArray(new String[0]));

		HITQuestion hitQuestion = null;
		try {
			hitQuestion = new HITQuestion(PRODUCT_CATEGROY_QUESTION + categoryNum);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Gateway gw = new GatewayAmazonMTurkImpl();

		HIT hit = gw.createIntroductionHITWithImage(hitProperties, hitDataInputReader, hitQuestion);
		
		persistHITData(p, gw.getWebsiteURL(), hit);
		return p;
	}

	/**
	 * @param p
	 * @param url
	 * @param hit
	 */
	private void persistHITData(Product p, String url, HIT hit) {
		if (hit == null)
			return;
		Hit h = new Hit();
		h.setProduct(p);
		h.setHit_id(hit.getHITId());
		h.setHit_type_id(hit.getHITTypeId());
		h.setTitle(hit.getTitle());
		h.setDescription(hit.getDescription());
		h.setKeywords(hit.getKeywords());
		h.setHit_url(url);

		if (hit.getHITStatus() != null) {
			h.setHit_status(hit.getHITStatus().getValue());
		}
		if (hit.getHITReviewStatus() != null) {
			h.setHit_review_status(hit.getHITReviewStatus().getValue());
		}
		if (hit.getCreationTime() != null) {
			h.setCreation_time(hit.getCreationTime().getTime());
		}
		if (hit.getAutoApprovalDelayInSeconds() != null) {
			h.setAuto_approval_delay_in_secs(hit
					.getAutoApprovalDelayInSeconds().intValue());
		}
		if (hit.getMaxAssignments() != null) {
			h.setMax_assignments(hit.getMaxAssignments());
		}
		if (hit.getReward() != null) {
			h.setReward(hit.getReward().getAmount().doubleValue());
		}
		if (hit.getNumberOfSimilarHITs() != null) {
			h.setNum_similar_hits(hit.getNumberOfSimilarHITs());
		}
		if (hit.getNumberOfAssignmentsAvailable() != null) {
			h.setNumber_of_assignments_available(hit
					.getNumberOfAssignmentsAvailable());
		}
		if (hit.getNumberOfAssignmentsAvailable() != null) {
			h.setNumber_of_assignments_available(hit
					.getNumberOfAssignmentsAvailable());
		}
		if (hit.getNumberOfAssignmentsCompleted() != null) {
			h.setNumber_of_assignments_completed(hit
					.getNumberOfAssignmentsCompleted());
		}
		if (hit.getNumberOfAssignmentsPending() != null) {
			h.setNumber_of_assignments_pending(hit
					.getNumberOfAssignmentsPending());
		}

		hitDAO.save(h);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.crowdaccent.service.ProductService#getNumValidProducts(int)
	 */
	@Override
	public List<Product> getNumValidProducts(int number) {
		return this.productDAO.getNumValidProducts(number);
	}

	/**
	 * @param hitDAO
	 *            the hitDAO to set
	 */
	@Autowired
	public void setHitDAO(HitDAO hitDAO) {
		this.hitDAO = hitDAO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.crowdaccent.service.ProductService#findProductEntries(int, int)
	 */
	@Override
	public List<Product> findProductEntries(int firstResult, int sizeNo) {
		return productDAO.findProductEntries(firstResult, sizeNo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.crowdaccent.service.ProductService#countProducts()
	 */
	@Override
	public Float countProducts() {
		return productDAO.countProducts();
	}
}
