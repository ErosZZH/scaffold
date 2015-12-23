package com.rick.scaffold.search.services.worker;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.rick.scaffold.search.services.RZSearchRequest;
import com.rick.scaffold.search.services.RZSearchResponse;
import com.rick.scaffold.search.services.delegate.SearchDelegate;
import com.rick.scaffold.search.utils.CustomIndexConfiguration;
import com.rick.scaffold.search.utils.SearchClient;

public class DeleteKeywordsImpl implements DeleteObjectWorker {

	private List<CustomIndexConfiguration> indexConfigurations = null;
	
	public List<CustomIndexConfiguration> getIndexConfigurations() {
		return indexConfigurations;
	}

	public void setIndexConfigurations(
			List<CustomIndexConfiguration> indexConfigurations) {
		this.indexConfigurations = indexConfigurations;
	}

	@Autowired
	private SearchDelegate searchDelegate;

	@Override
	public void deleteObject(SearchClient client, String index, String type, String id) throws Exception {
		if (searchDelegate.indexExist(index)) {
			String query = new StringBuilder()
					.append("{\"query\":{\"term\" : {\"_id_\" : \"").append(id)
					.append("\" }}}").toString();
			RZSearchRequest sr = new RZSearchRequest();
			sr.setIndex(index);
			sr.setType(type);
			sr.setJson(query);
			RZSearchResponse r = searchDelegate.search(sr);
			if (r != null) {
				Collection<String> ids = r.getIds();
				searchDelegate.bulkDeleteIndex(ids, index);
			}
		}
	}

}
