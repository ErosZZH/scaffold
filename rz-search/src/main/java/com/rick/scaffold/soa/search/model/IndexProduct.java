package com.rick.scaffold.soa.search.model;

import com.rick.scaffold.soa.search.IndexObject;


public class IndexProduct extends IndexObject {
	
    private String name;

    private String sn;

    private String introduction;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

    

}
