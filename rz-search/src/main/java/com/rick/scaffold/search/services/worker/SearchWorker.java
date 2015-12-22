package com.rick.scaffold.search.services.worker;

import com.rick.scaffold.search.services.RZSearchRequest;
import com.rick.scaffold.search.services.RZSearchResponse;
import com.rick.scaffold.search.utils.SearchClient;

public interface SearchWorker {
	
	RZSearchResponse execute(SearchClient client, RZSearchRequest request) throws Exception;

}
