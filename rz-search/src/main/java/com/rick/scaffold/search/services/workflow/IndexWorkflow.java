package com.rick.scaffold.search.services.workflow;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.rick.scaffold.common.jsontool.JsonMapper;
import com.rick.scaffold.search.IndexObject;
import com.rick.scaffold.search.services.worker.IndexWorker;


public class IndexWorkflow extends Workflow {
	
	private static Logger log = Logger.getLogger(IndexWorkflow.class);
	
	private List<IndexWorker> indexWorkflow;
	
	public List<IndexWorker> getIndexWorkflow() {
		return indexWorkflow;
	}

	public void setIndexWorkflow(List<IndexWorker> indexWorkflow) {
		this.indexWorkflow = indexWorkflow;
	}
	
	public void index(IndexObject jsonObj, String index, String type) throws Exception {
		String id = jsonObj.getId();
		if(StringUtils.isBlank(id)) {
			log.warn("No id exist for object.");
			throw new Exception("Invalid index object.");
		}
		String json = JsonMapper.toJsonString(jsonObj);
		if(indexWorkflow != null) {
			for(IndexWorker iw : indexWorkflow) {
				iw.execute(this.getSearchClient(), json, index, type, id);
			}
		}
	}

}
