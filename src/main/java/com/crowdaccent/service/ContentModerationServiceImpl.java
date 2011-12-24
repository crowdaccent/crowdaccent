/**
 * 
 */
package com.crowdaccent.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.mturk.addon.HITDataInput;
import com.amazonaws.mturk.addon.HITProperties;
import com.amazonaws.mturk.addon.HITQuestion;
import com.amazonaws.mturk.requester.HIT;
import com.crowdaccent.config.AppConfiguration;
import com.crowdaccent.entity.ContentModeration;
import com.crowdaccent.entity.Task;
import com.crowdaccent.orchestration.gateway.Gateway;
import com.crowdaccent.orchestration.gateway.HITDataInputReader;
import com.crowdaccent.repository.ContentModerationDAO;

/**
 * @author kbhalla
 *
 */
@Service
public class ContentModerationServiceImpl implements ContentModerationService {

	private static final String CONTENT_MODERATION_PROPERTIES = "content_moderation/content_moderation.properties";

	private static final String CONTENT = "content";

	private static final String INSTRUCTIONS = "instructions";

	private static final String CONTENT_MODERATION_HITDATA = "content_moderation/content_moderation.hitdata";

	private static final String CONTENT_MODERATION_QUESTION = "content_moderation.question";

	private @Autowired ContentModerationDAO contentModerationDAO;
	
	private @Autowired Gateway gateway;
	
	private @Autowired AppConfiguration appConfiguration;

	private @Autowired HitService hitService;

	/* (non-Javadoc)
	 * @see com.crowdaccent.service.ContentModerationService#save(com.crowdaccent.entity.ContentModeration)
	 */
	@Override
	public void save(ContentModeration contentModeration) {
		contentModerationDAO.save(contentModeration);
	}

	/* (non-Javadoc)
	 * @see com.crowdaccent.service.ContentModerationService#findContentModeration(java.lang.Long)
	 */
	@Override
	public ContentModeration findContentModeration(Long id) {
		return contentModerationDAO.findContentModeration(id);
	}

	/* (non-Javadoc)
	 * @see com.crowdaccent.service.ContentModerationService#findContentModerationEntries(int, int)
	 */
	@Override
	public Collection<ContentModeration> findContentModerationEntries(int firstResult,
			int sizeNo) {
		return contentModerationDAO.findContentModerationEntries(firstResult, sizeNo);
	}

	/* (non-Javadoc)
	 * @see com.crowdaccent.service.ContentModerationService#countContentModerations()
	 */
	@Override
	public Float countContentModerations() {
		return contentModerationDAO.countContentModerations();
	}

	/* (non-Javadoc)
	 * @see com.crowdaccent.service.ContentModerationService#findAllContentModerations()
	 */
	@Override
	public Collection<ContentModeration> findAllContentModerations() {
		return contentModerationDAO.findAllContentModerations();
	}

	/* (non-Javadoc)
	 * @see com.crowdaccent.service.ContentModerationService#delete(com.crowdaccent.entity.ContentModeration)
	 */
	@Override
	public void delete(ContentModeration contentModeration) {
		contentModerationDAO.delete(contentModeration);
	}

	/* (non-Javadoc)
	 * @see com.crowdaccent.service.ContentModerationService#createHIT(java.lang.String)
	 */
	@Override
	public ContentModeration createHIT(String id) {
		ContentModeration c = this.findContentModeration(new Long(id));

		Properties properties = new Properties();
		try {
			properties.load(this.getClass().getClassLoader()
					.getResourceAsStream(CONTENT_MODERATION_PROPERTIES));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		HITProperties hitProperties = new HITProperties(properties);

		List<String> fieldNames = new ArrayList<String>();
		fieldNames.add(CONTENT);
		fieldNames.add(INSTRUCTIONS);
		
		List<String> rows = new ArrayList<String>();
		StringBuffer r = new StringBuffer();
		r.append(c.getContent()).append(HITDataInputReader.DEFAULT_DELIM);
		r.append(c.getInstructions()).append(HITDataInputReader.DEFAULT_DELIM);
		
		Properties hitData = new Properties();
		try {
			hitData.load(this.getClass().getClassLoader()
					.getResourceAsStream(CONTENT_MODERATION_HITDATA));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		Enumeration<Object> keys = hitData.keys();
		while (keys.hasMoreElements()){
			String key = keys.nextElement().toString();
			String value = hitData.getProperty(key);
			fieldNames.add(key);
			r.append(value).append(HITDataInputReader.DEFAULT_DELIM);
		}
		
		rows.add(r.toString());
		//TODO: off by one error in SDK. Leaving it open as of now.
		rows.add(r.toString());
		HITDataInput hitDataInputReader = new HITDataInputReader(fieldNames.toArray(new String[0]), rows.toArray(new String[0]));

		HITQuestion hitQuestion = null;
		try {
			hitQuestion = new HITQuestion(CONTENT_MODERATION_QUESTION);
		} catch (Exception e) {
			e.printStackTrace();
		}

		HIT hit = gateway.createIntroductionHITWithImage(hitProperties, hitDataInputReader, hitQuestion);
		gateway.setNotificationURL(hit.getHITTypeId(), appConfiguration.getCallbackURL());
		
		String websiteURL = gateway.getWebsiteURL() + appConfiguration.getPreviewURL()+hit.getHITTypeId();
		
		hitService.persistHITData((Task)c, websiteURL, hit);
		return c;
	}
}
