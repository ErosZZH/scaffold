package com.rick.scaffold.search.services.worker;

import com.rick.scaffold.search.utils.SearchClient;


public interface IndexWorker {
	
	void init(SearchClient client);
	void execute(SearchClient client, String json, String index, String type, Long id) throws Exception;

}
