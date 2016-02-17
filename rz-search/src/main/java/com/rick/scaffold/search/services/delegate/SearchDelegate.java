package com.rick.scaffold.search.services.delegate;

import java.util.Collection;
import java.util.Set;

import com.rick.scaffold.search.services.RZGetResponse;
import com.rick.scaffold.search.services.RZIndexKeywordRequest;
import com.rick.scaffold.search.services.RZSearchRequest;
import com.rick.scaffold.search.services.RZSearchResponse;

public interface SearchDelegate {

	boolean indexExist(String indexName) throws Exception;
	
	boolean typeExist(String index, String type) throws Exception;

	void createIndice(String mappingJson, String settingsJson, String indice, String type) throws Exception;

	void index(String json, String index, String type, Long id);

	void delete(String index, String type, Long id) throws Exception;

	void bulkDeleteIndex(Collection<String> ids, String type, String index) throws Exception;

	void bulkIndexKeywords(Collection<RZIndexKeywordRequest> bulks, String index, String type) throws Exception;

	RZGetResponse getObject(String index, String type, String id) throws Exception;

	RZSearchResponse search(RZSearchRequest request) throws Exception;

	Set<String> searchAutoComplete(String index, String json, String type, int size) throws Exception;

}