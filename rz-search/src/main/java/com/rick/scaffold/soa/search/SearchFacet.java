package com.rick.scaffold.soa.search;

import java.io.Serializable;

public class SearchFacet  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String key;
	private long count;
	
	public void setKey(String key) {
		this.key = key;
	}
	public String getKey() {
		return key;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setCount(long count) {
		this.count = count;
	}
	public long getCount() {
		return count;
	};

}
