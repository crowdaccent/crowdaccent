package com.crowdaccent.orchestration.gateway;

public interface Gateway {

	/**
	 * 
	 * @param hrequest
	 * @return
	 */
	public HITResponse createBasicFreeTextHIT(HITRequest hrequest);
	
	/**
	 * 
	 * @param hrequest
	 * @return
	 */
	public HITResponse createComplexFreeTextHIT(HITRequest hrequest);

	 /**
     * 
     * @param hrequest
     * @return
     */
    public HITResponse createIntroductionHIT(HITRequest hrequest);

    /** 
    * @param hrequest
    * @return
    */
   public HITResponse createIntroductionHITWithImage(HITRequest hrequest);
    
	/** Returns websiteURL
	 * @return String website URL
	 */
	public String getWebsiteURL();
	
}
