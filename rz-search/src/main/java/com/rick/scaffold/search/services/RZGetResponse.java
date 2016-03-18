package com.rick.scaffold.search.services;

import java.util.List;
import java.util.Map;

import com.rick.scaffold.common.jsontool.JsonMapper;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.index.get.GetField;

public class RZGetResponse {

	private GetResponse response;

	public RZGetResponse(GetResponse r) {
		response = r;
	}

	public String getResponseAsString() {
		return JsonMapper.toJsonString(this.getFields());
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
