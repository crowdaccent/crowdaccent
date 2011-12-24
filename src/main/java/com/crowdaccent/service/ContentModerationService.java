/**
 * 
 */
package com.crowdaccent.service;

import java.util.Collection;

import com.crowdaccent.entity.ContentModeration;

/**
 * @author kbhalla
 *
 */
public interface ContentModerationService {

	/**
	 * @param contentModeration 
	 * 
	 */
	void save(ContentModeration contentModeration);

	/**
	 * @param id
	 * @return
	 */
	ContentModeration findContentModeration(Long id);

	/**
	 * @param firstResult
	 * @param sizeNo
	 * @return
	 */
	Collection<ContentModeration> findContentModerationEntries(int firstResult, int sizeNo);

	/**
	 * @return
	 */
	Float countContentModerations();

	/**
	 * @return
	 */
	Collection<ContentModeration> findAllContentModerations();

	/**
	 * @param contentModeration
	 */
	void delete(ContentModeration contentModeration);

	/**
	 * @param id
	 * @return
	 */
	ContentModeration createHIT(String id);

}
