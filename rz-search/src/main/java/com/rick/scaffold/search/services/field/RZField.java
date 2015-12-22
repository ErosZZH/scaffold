package com.rick.scaffold.search.services.field;

public abstract class RZField {

	private String name;
	private Object object;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setValue(Object o) {
		this.object = o;
	}

	protected Object getValue() {
		return object;
	}

}
