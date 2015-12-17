package com.rick.scaffold.common.search.services;

import java.util.List;

public class RZFacet {

	private String name;
	private List<RZEntry> entries;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<RZEntry> getEntries() {
		return entries;
	}

	public void setEntries(List<RZEntry> entries) {
		this.entries = entries;
	}

}
