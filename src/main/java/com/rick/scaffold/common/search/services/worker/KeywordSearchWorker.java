package com.rick.scaffold.common.search.services.worker;

import com.rick.scaffold.common.search.services.RZSearchResponse;
import com.rick.scaffold.common.search.utils.SearchClient;


public interface KeywordSearchWorker {
	
	RZSearchResponse execute(SearchClient client,String index,String json,int size, ExecutionContext context) throws Exception;

}
