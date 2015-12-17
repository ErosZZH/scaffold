package com.rick.scaffold.common.search.biz.user;

import com.rick.scaffold.common.search.biz.IndexObject;


public class IndexUser extends IndexObject {
	
	private String loginName;

    private String name;

    private String email;

    private String phone;


	public String getLoginName() {
		return loginName;
	}


	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}

	
}
