package com.rick.scaffold.common.search.services.worker;

import com.rick.scaffold.common.search.utils.SearchClient;


public interface IndexWorker {
	
	void init(SearchClient client);
	void execute(SearchClient client, String json, String index, String type, String id) throws Exception;

}
