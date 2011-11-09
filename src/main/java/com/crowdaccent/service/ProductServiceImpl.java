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

}
