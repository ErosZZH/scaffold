package com.rick.scaffold.search.utils;

/**
 * Configured from Spring
 * 
 * @author Carl Samson
 * 
 */
public class IndexConfiguration {

	private String indiceName;
	private String typeName;
	private String mappingFileName;
	private String settingsFileName;
	
	public String getIndiceName() {
		return indiceName;
	}
	public void setIndiceName(String indiceName) {
		this.indiceName = indiceName;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getMappingFileName() {
		return mappingFileName;
	}
	public void setMappingFileName(String mappingFileName) {
		this.mappingFileName = mappingFileName;
	}
	public String getSettingsFileName() {
		return settingsFileName;
	}
	public void setSettingsFileName(String settingsFileName) {
		this.settingsFileName = settingsFileName;
	}
	
	
}
