package com.rick.scaffold.service.user;

import org.junit.Test;

import com.rick.scaffold.common.persistence.tool.ObjectId;

public class TestObjectId {

	@Test
	public void testObjectId() {
		System.out.println(ObjectId.get().toString());
	}
}
