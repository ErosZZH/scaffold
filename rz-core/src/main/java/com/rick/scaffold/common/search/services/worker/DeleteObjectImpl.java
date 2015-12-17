package com.rick.scaffold.common.search.services.worker;

import org.springframework.beans.factory.annotation.Autowired;

import com.rick.scaffold.common.search.services.delegate.SearchDelegate;
import com.rick.scaffold.common.search.utils.SearchClient;

public class DeleteObjectImpl implements DeleteObjectWorker {

	@Autowired
	private SearchDelegate searchDelegate;

	@Override
	public void deleteObject(SearchClient client, String index,
			String type, String id) throws Exception {
		searchDelegate.delete(index, type, id);
	}

}
