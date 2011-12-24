/**
 * 
 */
package com.crowdaccent.repository;

import java.util.Collection;
import java.util.List;

import com.crowdaccent.entity.ContentModeration;

/**
 * @author kbhalla
 *
 */
public interface ContentModerationDAO {

	/**
	 * @param contentModeration
	 */
	void save(ContentModeration contentModeration);

	/**
	 * @param contentModeration
	 */
	void delete(ContentModeration contentModeration);

	/**
	 * @return
	 */
	Float countContentModerations();

	/**
	 * @return
	 */
	Collection<ContentModeration> findAllContentModerations();

	/**
	 * @param firstResult
	 * @param sizeNo
	 * @return
	 */
	List<ContentModeration> findContentModerationEntries(int firstResult, int sizeNo);

	/**
	 * @param id
	 * @return
	 */
	ContentModeration findContentModeration(Long id);

}
