package com.rick.scaffold.search.services.workflow;

import com.rick.scaffold.search.utils.SearchClient;

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