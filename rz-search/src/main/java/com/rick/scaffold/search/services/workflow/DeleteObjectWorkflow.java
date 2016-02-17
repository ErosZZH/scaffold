package com.rick.scaffold.search.services.workflow;

import java.util.List;

import com.rick.scaffold.search.services.worker.DeleteObjectWorker;

public class DeleteObjectWorkflow extends Workflow{
	
	private List<DeleteObjectWorker> deleteObjectWorkflow;

	public List<DeleteObjectWorker> getDeleteObjectWorkflow() {
		return deleteObjectWorkflow;
	}


	public void setDeleteObjectWorkflow(List<DeleteObjectWorker> deleteObjectWorkflow) {
		this.deleteObjectWorkflow = deleteObjectWorkflow;
	}


	public void deleteObject(String index, String type, Long id) throws Exception {	
		if(deleteObjectWorkflow!=null) {
			for(DeleteObjectWorker iw : deleteObjectWorkflow) {
				iw.deleteObject(super.getSearchClient(), index, type, id);
			}
		}
	}

}
