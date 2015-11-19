package com.rick.scaffold.common.search.utils;

import java.util.Map;

import com.rick.scaffold.common.utils.StringUtils;

public class DynamicIndexNameUtil {

	public static String getIndexName(String name, Map<String, Object> indexData) {
//		if (name.startsWith("%") && name.endsWith("%")) {
//			String containedField = name.substring(1, name.length() - 1);
//			String f = (String) indexData.get(containedField);
//			if (StringUtils.isBlank(f)) {
//				return name;
//			}
//			return f;
//		}
//		return name;
		
		String f = (String) indexData.get(name);
		if (StringUtils.isBlank(f)) {
			return name;
		}
		return f;
	}

}
