package com.rick.scaffold.exception;

import com.rick.scaffold.common.utils.ExceptionUtils;


public abstract class CommonException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected String trackId;
	
	public CommonException(String message) {
		super(message);
		this.trackId = ExceptionUtils.getTrackID();
	}
	
	public CommonException(String message, Throwable e) {
		super(message, e);
		this.trackId = ExceptionUtils.getTrackID();
	}

	public String getTrackId() {
		return trackId;
	}	

}
