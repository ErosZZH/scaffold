package com.rick.scaffold.common.search.model;

import java.util.List;

public class SearchEntry {
	
	private IndexUser indexUser;
	private List<String> highlights;
	
	public void setHighlights(List<String> highlights) {
		this.highlights = highlights;
	}
	public List<String> getHighlights() {
		return highlights;
	}
	public IndexUser getIndexUser() {
		return indexUser;
	}
	public void setIndexUser(IndexUser indexUser) {
		this.indexUser = indexUser;
	}
	
	

}
