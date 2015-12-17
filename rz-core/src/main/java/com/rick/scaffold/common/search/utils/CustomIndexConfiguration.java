package com.rick.scaffold.common.search.utils;

import java.util.List;

public class CustomIndexConfiguration extends IndexConfiguration {

	
	private String onType;
	private List<CustomIndexFieldConfiguration> fields;
	private List<CustomIndexFieldConfiguration> filters;

	public String getOnType() {
		return onType;
	}

	public void setOnType(String onType) {
		this.onType = onType;
	}

	public List<CustomIndexFieldConfiguration> getFields() {
		return fields;
	}

	public void setFields(List<CustomIndexFieldConfiguration> fields) {
		this.fields = fields;
	}

	public List<CustomIndexFieldConfiguration> getFilters() {
		return filters;
	}

	public void setFilters(List<CustomIndexFieldConfiguration> filters) {
		this.filters = filters;
	}
}
