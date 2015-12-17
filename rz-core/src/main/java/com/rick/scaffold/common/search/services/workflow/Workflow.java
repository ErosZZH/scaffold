package com.rick.scaffold.common.search.services.workflow;

import com.rick.scaffold.common.search.utils.SearchClient;

public abstract class Workflow {

	private SearchClient searchClient;

	public SearchClient getSearchClient() {
		return searchClient;
	}

	public void setSearchClient(SearchClient searchClient) {
		this.searchClient = searchClient;
	}

	public Workflow() {
		super();
	}

}