package com.rick.scaffold.core.entity.user;

import com.rick.scaffold.core.entity.generic.BaseEntity;

public class Photo extends BaseEntity<Photo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String url;
	private int size;
	private long userId;
	
	
	
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
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
