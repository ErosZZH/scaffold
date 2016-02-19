package com.rick.scaffold.common.config;

import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Optional;
import com.google.common.collect.Maps;
import com.rick.scaffold.common.utils.FileUtils;

/**
 * 全局配置类
 * @author Rick
 * @version 2015-08-25
 */
public class Global {

	/**
	 * 当前对象实例
	 */
	private static Global global = new Global();
	
	/**
	 * 保存全局属性值
	 */
	private static Map<String, String> map = Maps.newHashMap();
	
	/**
	 * 属性文件加载对象
	 */
	private static Properties loader = FileUtils.loadProps("config/props/scaffold.properties");
	
	/**
	 * 获取当前对象实例
	 */
	public static Global getInstance() {
		return global;
	}
	
	/**
	 * 获取配置
	 */
	public static String getConfig(String key) {
		String value = map.get(key);
		if (value == null){
			value = loader.getProperty(key);
			map.put(key, Optional.fromNullable(value).or(StringUtils.EMPTY));
		}
		return value;
	}
	
}
