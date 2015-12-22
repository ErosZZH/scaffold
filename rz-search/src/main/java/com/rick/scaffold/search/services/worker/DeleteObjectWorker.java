package com.rick.scaffold.search.services.worker;

import com.rick.scaffold.search.utils.SearchClient;


/**
 * Deletes an object from the index
 * @author Carl Samson
 *
 */
public interface DeleteObjectWorker {
	
	public void deleteObject(SearchClient client, String index, String type, String id) throws Exception;

}
