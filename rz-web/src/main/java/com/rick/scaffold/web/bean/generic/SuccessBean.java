package com.rick.scaffold.web.bean.generic;

public class SuccessBean {

	private int code;
	private String status;
	private Object data;
	
	public SuccessBean(int code, String status, Object data) {
		this.code = code;
		this.status = status;
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public String getStatus() {
		return status;
	}

	public Object getData() {
		return data;
	}
	
	
	
	
}
