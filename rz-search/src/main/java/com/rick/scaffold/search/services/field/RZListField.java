package com.rick.scaffold.search.services.field;

import java.util.List;

public class RZListField extends RZField {
	
	@SuppressWarnings("unchecked")
	public List<Object> getValue() {
		return (List<Object>)super.getValue();
	}

}
