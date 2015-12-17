package com.rick.scaffold.core.entity.user;

import com.rick.scaffold.core.entity.generic.BaseEntity;

public class Company extends BaseEntity<Company> {

	private static final long serialVersionUID = 1L;
	
	private String name;
	private String phone;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
