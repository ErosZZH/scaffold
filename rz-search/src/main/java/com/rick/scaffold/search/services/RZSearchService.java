package com.rick.scaffold.search.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.rick.scaffold.search.services.worker.KeywordIndexerImpl;
import com.rick.scaffold.search.services.worker.ObjectIndexerImpl;
import com.rick.scaffold.search.services.workflow.DeleteObjectWorkflow;
import com.rick.scaffold.search.services.workflow.GetWorkflow;
import com.rick.scaffold.search.services.workflow.ImportWorkflow;
import com.rick.scaffold.search.services.workflow.IndexWorkflow;
import com.rick.scaffold.search.services.workflow.SearchWorkflow;
import com.rick.scaffold.search.utils.SearchClient;
import com.rick.scaffold.soa.search.IndexObject;

/**
 * This is the main class for indexing and searching services
 * 
 * @author Carl Samson
 * 
 */
public class RZSearchService {

	private static Logger log = Logger.getLogger(RZSearchService.class);

	@Autowired
	private DeleteObjectWorkflow deleteWorkflow;

	@Autowired
	private IndexWorkflow indexWorkflow;

	@Autowired
	private GetWorkflow getWorkflow;

	@Autowired
	private SearchWorkflow searchWorkflow;

	@Autowired
	private ObjectIndexerImpl index;

	@Autowired
	private KeywordIndexerImpl keyword;
	
	@Autowired
	private ImportWorkflow importWorkflow;

	@Autowired
	private SearchClient searchClient;
	
	

	public void initService() {
		log.debug("Initializing search service");
		try {
			index.init(searchClient);
			keyword.init(searchClient);
		} catch (Exception e) {
			log.error("Cannot initialize SearchService correctly, will be initialized lazily", e);
		}
	}

	public void deleteObject(String index, String type, Long id)
			throws Exception {
		deleteWorkflow.deleteObject(index, type, id);
	}

	public RZGetResponse getObject(String index, String type, String id)
			throws Exception {
		return getWorkflow.getObject(index, type, id);
	}

	public void index(IndexObject jsonObj, String index, String type)
			throws Exception {
		indexWorkflow.index(jsonObj, index, type);
	}

	public RZSearchResponse searchAutoComplete(String index, String json, String type,
			int size) throws Exception {
		return searchWorkflow.searchAutoComplete(index, json, type, size);
	}

	public RZSearchResponse search(RZSearchRequest request) throws Exception {
		return searchWorkflow.search(request);
	}
	
	public void importFromMysql(String sql, String index, String type, String river) throws Exception {
		importWorkflow.importFromDB(sql, index, type, river);
	}
}
