package com.crowdaccent.orchestration.gateway;

public interface Gateway {

	/**
	 * 
	 * @param hrequest
	 * @return
	 */
	public HITResponse createProductCategorizationHIT(HITRequest hrequest);

	/** Returns websiteURL
	 * @return String website URL
	 */
	public String getWebsiteURL();
	
}
