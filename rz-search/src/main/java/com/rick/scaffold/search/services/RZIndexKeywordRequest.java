package com.rick.scaffold.search.services;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import com.rick.scaffold.search.services.field.RZField;

public class RZIndexKeywordRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String id;
	private String key;
	private Collection<RZField> filters = new ArrayList<RZField>();

	public Collection<RZField> getFilters() {
		return filters;
	}

	public void setFilters(Collection<RZField> filters) {
		this.filters = filters;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
