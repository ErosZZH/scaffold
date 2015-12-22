package com.rick.scaffold.search.services.workflow;


import org.springframework.beans.factory.annotation.Autowired;

import com.rick.scaffold.search.services.RZGetResponse;
import com.rick.scaffold.search.services.delegate.SearchDelegate;


public class GetWorkflow extends Workflow {
	
	@Autowired
	private SearchDelegate searchDelegate;
	public RZGetResponse getObject(String index, String type, String id) throws Exception {

		return searchDelegate.getObject(index, type, id);
		
	}

}
