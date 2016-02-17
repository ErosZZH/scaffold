package com.rick.scaffold.service.user;

import org.junit.Test;

import com.rick.scaffold.common.persistence.tool.IncrSequenceTimeHandler;

public class TestObjectId {

//	@Test
//	public void testObjectId() {
//		System.out.println(ObjectId.get().toString());
//	}
	
	@Test
	public void testSequence() {
		long id = IncrSequenceTimeHandler.getInstance().nextId();
		System.out.println(id);
	}
	
	@Test
	public void testCurrentTime() {
		System.out.println(System.currentTimeMillis());
		System.out.println(1288834974657L);
	}
}
