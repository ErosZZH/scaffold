package com.rick.scaffold.search.services.worker;

import org.springframework.beans.factory.annotation.Autowired;

import com.rick.scaffold.search.services.delegate.SearchDelegate;
import com.rick.scaffold.search.utils.SearchClient;

public class DeleteObjectImpl implements DeleteObjectWorker {

	@Autowired
	private SearchDelegate searchDelegate;

	@Override
	public void deleteObject(SearchClient client, String index,
			String type, Long id) throws Exception {
		searchDelegate.delete(index, type, id);
	}

}
