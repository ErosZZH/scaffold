package com.rick.scaffold.search.services;

import java.util.Collection;
import java.util.Map;

/**
 * Object used for autocomplete and regular search
 * 
 * @author Carl Samson
 * 
 */
public class RZSearchResponse {

	private String inputSearchJson;
	private Collection<String> ids;
	private int count;
	private String[] inlineSearchList;
	private Collection<RZSearchHit> searchHits;
	private Map<String, RZFacet> facets;

	public Map<String, RZFacet> getFacets() {
		return facets;
	}

	public void setFacets(Map<String, RZFacet> facets) {
		this.facets = facets;
	}

	public String[] getInlineSearchList() {
		return inlineSearchList;
	}

	public void setInlineSearchList(String[] inlineSearchList) {
		this.inlineSearchList = inlineSearchList;
	}

	public Collection<RZSearchHit> getSearchHits() {
		return searchHits;
	}

	public void setSearchHits(Collection<RZSearchHit> searchHits) {
		this.searchHits = searchHits;
	}

	public String getInputSearchJson() {
		return inputSearchJson;
	}

	public void setInputSearchJson(String inputSearchJson) {
		this.inputSearchJson = inputSearchJson;
	}

	public Collection<String> getIds() {
		return ids;
	}

	public void setIds(Collection<String> ids) {
		this.ids = ids;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
