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
   
   /**
    * 
    * @param subject
    * @param messageText
    * @param workerId
    */
   public void notifyWorkers(String subject, String messageText, String[] workerId); 

    
	/** Returns websiteURL
	 * @return String website URL
	 */
	public String getWebsiteURL();
	
}
