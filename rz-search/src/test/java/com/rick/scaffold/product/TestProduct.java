package com.rick.scaffold.product;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.rick.scaffold.base.BaseTest;
import com.rick.scaffold.search.services.RZSearchService;
import com.rick.scaffold.soa.search.service.ProductSearch;

public class TestProduct extends BaseTest{

	@Autowired
	private ProductSearch ps;
	
	@Autowired
	private RZSearchService searchService;

	@Test
	public void testImportProduct () throws Exception {
		ps.importFromDB("select id, name, sn, introduction from yzl_product"); 
	}
	
	@Test
	public void testImport() throws Exception {
		searchService.importFromMysql("select * from sys_user", "scaffold", "import_user", "user_river");
	}
}
