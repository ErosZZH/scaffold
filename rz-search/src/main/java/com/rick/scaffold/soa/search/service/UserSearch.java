package com.rick.scaffold.soa.search.service;

import javax.annotation.PostConstruct;

import com.rick.scaffold.soa.search.SearchKeywords;
import com.rick.scaffold.soa.search.SearchResult;
import com.rick.scaffold.soa.search.model.IndexUser;

public interface UserSearch {

	void createIndex(IndexUser user);

	void deleteIndex(Long id);

	SearchKeywords searchForKeywords(String index, String type, String jsonString, int entriesCount);

	SearchResult search(String jsonString, int entriesCount, int startIndex);

	@PostConstruct
	void initService();
}
