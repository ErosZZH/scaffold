package com.rick.scaffold.service.user;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.rick.scaffold.base.BaseTest;
import com.rick.scaffold.core.entity.user.Company;
import com.rick.scaffold.core.service.user.CompanyService;

public class TestCompany extends BaseTest{

	@Autowired
	private CompanyService cs;
	
	@Test
	public void testAddCompany() {
		Company c = new Company();
		c.setName("yzl1");
		c.setPhone("12346");
		cs.save(c);
	}
}
