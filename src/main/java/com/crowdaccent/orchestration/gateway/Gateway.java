package com.crowdaccent.orchestration.gateway;

public interface Gateway {

	/**
	 * 
	 * @param hrequest
	 * @return
	 */
	public HITResponse createHIT(HITRequest hrequest);
	
}
