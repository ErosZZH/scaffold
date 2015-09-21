package com.rick.scaffold.exception;

public class APIException extends CommonException {

	private static final long serialVersionUID = 1L;

	private int errorCode;

	public APIException(String message) {
		super(message);
		this.errorCode = 500;
	}

	public APIException(String message, Throwable e) {
		super(message, e);
		this.errorCode = 500;
	}

	public APIException(String message, int errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public APIException(String message, int errorCode, Throwable e) {
		super(message, e);
		this.errorCode = errorCode;
	}

	// use when test
	public APIException(String message, int errorCode, String trackId) {
		super(message);
		this.errorCode = errorCode;
		this.trackId = trackId;
	}

	// use when test
	public APIException(String message, int errorCode, String trackId,
			Throwable e) {
		super(message, e);
		this.errorCode = errorCode;
		this.trackId = trackId;
	}

	public int getErrorCode() {
		return errorCode;
	}

}
