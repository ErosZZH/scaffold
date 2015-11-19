package com.rick.scaffold.common.search.services;

import java.util.List;
import java.util.Map;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.index.get.GetField;

public class RZGetResponse {

	private GetResponse response;

	public RZGetResponse(GetResponse r) {
		response = r;
	}

	public String getResponseAsString() {
		return response.toString();
	}

	public Map<String, Object> getFields() {
		return response.getSource();
	}

	public List<Object> getField(String key) {
		GetField f = response.getFields().get(key);
		if (f != null) {
			return f.getValues();
		}
		return null;
	}

}
