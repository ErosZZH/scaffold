package com.rick.scaffold.exception;

import com.rick.scaffold.common.utils.ExceptionUtils;


public abstract class CommonException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String trackId;
	
	public CommonException(String message) {
		super(message);
		this.trackId = ExceptionUtils.getTrackID();
	}

	public String getTrackId() {
		return trackId;
	}	

}
