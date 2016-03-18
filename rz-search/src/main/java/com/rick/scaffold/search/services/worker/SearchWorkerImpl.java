package com.rick.scaffold.search.services.worker;

import org.springframework.beans.factory.annotation.Autowired;

import com.rick.scaffold.search.services.RZSearchRequest;
import com.rick.scaffold.search.services.RZSearchResponse;
import com.rick.scaffold.search.services.delegate.SearchDelegate;
import com.rick.scaffold.search.utils.SearchClient;

public class SearchWorkerImpl implements SearchWorker {

	@Autowired
	private SearchDelegate searchDelegate;

    @Override
	public RZSearchResponse execute(SearchClient client, RZSearchRequest request) throws Exception {
		RZSearchResponse response = searchDelegate.search(request);
		response.setInputSearchJson(request.getJson());
		return response;

	}

}
