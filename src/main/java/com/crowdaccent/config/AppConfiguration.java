/**
 * 
 */
package com.crowdaccent.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author kbhalla
 *
 */
@Configuration
public class AppConfiguration {
	private @Value("${callback.url}") String callbackURL;
	private @Value("${rest.version}") String RESTVersion;
	private @Value("${preview.url}") String previewURL;

	/**
	 * @return the callbackURL
	 */
	public String getCallbackURL() {
		return callbackURL;
	}

	/**
	 * @param callbackURL the callbackURL to set
	 */
	public void setCallbackURL(String callbackURL) {
		this.callbackURL = callbackURL;
	}

	/**
	 * @param rESTVersion the rESTVersion to set
	 */
	public void setRESTVersion(String rESTVersion) {
		RESTVersion = rESTVersion;
	}

	/**
	 * @return the rESTVersion
	 */
	public String getRESTVersion() {
		return RESTVersion;
	}

	/**
	 * @return the previewURL
	 */
	public String getPreviewURL() {
		return previewURL;
	}

	/**
	 * @param previewURL the previewURL to set
	 */
	public void setPreviewURL(String previewURL) {
		this.previewURL = previewURL;
	}
}
