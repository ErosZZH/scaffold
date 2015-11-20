package com.rick.scaffold.common.search.services.worker;

import com.rick.scaffold.common.search.services.RZSearchRequest;
import com.rick.scaffold.common.search.services.RZSearchResponse;
import com.rick.scaffold.common.search.utils.SearchClient;

public interface SearchWorker {
	
	RZSearchResponse execute(SearchClient client, RZSearchRequest request) throws Exception;

}
