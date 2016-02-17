package com.rick.scaffold.service.user;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.rick.scaffold.base.BaseTest;
import com.rick.scaffold.core.entity.user.Photo;
import com.rick.scaffold.core.service.user.PhotoService;

public class TestPhoto extends BaseTest{

	@Autowired
	private PhotoService ps;
	
	@Test
	public void testAddPhoto() {
		Photo p = new Photo();
		p.setSize(102);
		p.setUrl("sina.com");
		p.setUserId(56886380597248L);
		ps.save(p);
	}
}
