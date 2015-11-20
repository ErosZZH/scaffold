package com.rick.scaffold.common.search.services.workflow;

import java.util.List;

import com.rick.scaffold.common.search.services.worker.DeleteObjectWorker;

public class DeleteObjectWorkflow extends Workflow{
	
	private List<DeleteObjectWorker> deleteObjectWorkflow;

	public List<DeleteObjectWorker> getDeleteObjectWorkflow() {
		return deleteObjectWorkflow;
	}


	public void setDeleteObjectWorkflow(List<DeleteObjectWorker> deleteObjectWorkflow) {
		this.deleteObjectWorkflow = deleteObjectWorkflow;
	}


	public void deleteObject(String index, String type, String id) throws Exception {	
		if(deleteObjectWorkflow!=null) {
			for(DeleteObjectWorker iw : deleteObjectWorkflow) {
				iw.deleteObject(super.getSearchClient(), index, type, id);
			}
		}
	}

}
