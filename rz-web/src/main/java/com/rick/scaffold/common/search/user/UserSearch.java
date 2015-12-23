package com.rick.scaffold.common.search.user;

import javax.annotation.PostConstruct;

import com.rick.scaffold.common.search.SearchKeywords;
import com.rick.scaffold.common.search.SearchResult;
import com.rick.scaffold.core.entity.user.User;

public interface UserSearch {

	void createIndex(User user);

	void deleteIndex(String id);

	/**
	 * Similar keywords based on a a series of characters. Used in the auto-complete
	 * functionality
	 * @param collectionName
	 * @param jsonString
	 * @param entriesCount
	 * @return
	 * @throws ServiceException
	 */
	SearchKeywords searchForKeywords(String index, String type, String jsonString, int entriesCount);

	/**
	 * Search products based on user entry
	 * @param store
	 * @param languageCode
	 * @param jsonString
	 * @param entriesCount
	 * @param startIndex
	 * @throws ServiceException
	 */
	SearchResult search(String jsonString, int entriesCount, int startIndex);

	/**
	 * Initializes search service in order to avoid lazy initialization
	 */
	@PostConstruct
	void initService();

}
