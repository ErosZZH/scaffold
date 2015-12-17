package com.rick.scaffold.core.entity.user;

import com.rick.scaffold.core.entity.generic.BaseEntity;

public class Photo extends BaseEntity<Photo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String url;
	private int size;
	private String userId;
	
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
	
}
