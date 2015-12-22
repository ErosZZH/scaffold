package com.rick.scaffold.search.services.delegate;

import java.util.Collection;
import java.util.Set;

import com.rick.scaffold.search.services.RZGetResponse;
import com.rick.scaffold.search.services.RZIndexKeywordRequest;
import com.rick.scaffold.search.services.RZSearchRequest;
import com.rick.scaffold.search.services.RZSearchResponse;

public interface SearchDelegate {

	boolean indexExist(String indexName) throws Exception;

	void createIndice(String mappingJson, String settingsJson, String indice, String type) throws Exception;

	void index(String json, String index, String type, String id);

	void delete(String index, String type, String id) throws Exception;

	void bulkDeleteIndex(Collection<String> ids, String index) throws Exception;

	void bulkIndexKeywords(Collection<RZIndexKeywordRequest> bulks, String index, String type) throws Exception;

	RZGetResponse getObject(String index, String type, String id) throws Exception;

	RZSearchResponse search(RZSearchRequest request) throws Exception;

	Set<String> searchAutoComplete(String index, String json, int size) throws Exception;

}