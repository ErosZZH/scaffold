package com.rick.scaffold.common.search.model;

import java.util.List;

import com.rick.scaffold.common.jsontool.JsonMapper;

public class SearchKeywords {
	
	private List<String> keywords;

	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}

	public List<String> getKeywords() {
		return keywords;
	}
	
	public String toJSONString() {
		return JsonMapper.toJsonString(this);
	}

}
