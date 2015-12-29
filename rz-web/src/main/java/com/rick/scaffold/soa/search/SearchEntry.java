package com.rick.scaffold.soa.search;

import java.io.Serializable;
import java.util.List;

public class SearchEntry  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private IndexObject indexObject;
	private List<String> highlights;
	
	public void setHighlights(List<String> highlights) {
		this.highlights = highlights;
	}
	public List<String> getHighlights() {
		return highlights;
	}
	public IndexObject getIndexObject() {
		return indexObject;
	}
	public void setIndexUser(IndexObject indexObject) {
		this.indexObject = indexObject;
	}
	
	

}
