package com.rick.scaffold.search.services.worker;

import com.rick.scaffold.search.services.RZSearchResponse;
import com.rick.scaffold.search.utils.SearchClient;


public interface KeywordSearchWorker {
	
	RZSearchResponse execute(SearchClient client,String index,String json,int size) throws Exception;

}
