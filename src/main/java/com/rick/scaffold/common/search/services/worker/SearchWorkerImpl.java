package com.rick.scaffold.common.search.services.worker;

import org.springframework.beans.factory.annotation.Autowired;

import com.rick.scaffold.common.search.services.RZSearchRequest;
import com.rick.scaffold.common.search.services.RZSearchResponse;
import com.rick.scaffold.common.search.services.delegate.SearchDelegate;
import com.rick.scaffold.common.search.utils.SearchClient;

public class SearchWorkerImpl implements SearchWorker {

	@Autowired
	private SearchDelegate searchDelegate;

	public RZSearchResponse execute(SearchClient client,
			RZSearchRequest request, ExecutionContext context) throws Exception {

		RZSearchResponse response = searchDelegate.search(request);

		response.setInputSearchJson(request.getJson());
		if (context == null) {
			context = new ExecutionContext();
		}
		context.setObject("response", response);
		return response;

	}

}
