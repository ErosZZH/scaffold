package com.rick.scaffold.common.search.services.worker;

import java.util.HashMap;
import java.util.Map;

public class ExecutionContext {
	
	private Map<String, Object> internalMap = new HashMap<String, Object>();
	
	public Object getObject(String key) {
		return internalMap.get(key);
	}
	
	public void setObject(String key, Object o) {
		internalMap.put(key, o);
	}
}
