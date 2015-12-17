package com.rick.scaffold.web.bean.generic;

public class FailBean {

	private int code;
	private String status;
	private String message;
	private String trackId;
	
	public FailBean(int code, String status, String message, String trackId) {
		this.code = code;
		this.status = status;
		this.message = message;
		this.trackId = trackId;
	}

	public int getCode() {
		return code;
	}

	public String getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public String getTrackId() {
		return trackId;
	}
	
	
	
}
