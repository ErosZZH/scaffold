package com.rick.scaffold.product;

import com.rick.scaffold.soa.search.service.GoodsSearch;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.rick.scaffold.base.BaseTest;
import com.rick.scaffold.search.services.RZSearchService;
import com.rick.scaffold.soa.search.service.ProductSearch;

public class TestProduct extends BaseTest{

	@Autowired
	private ProductSearch ps;

    @Autowired
    private GoodsSearch gs;
	
	@Autowired
	private RZSearchService searchService;

	@Test
	public void testImportProduct () throws Exception {
		ps.importFromDB("select id, name as keyword, sn, introduction from yzl_product"); 
	}

    @Test
    public void testImportGoods () throws Exception {
        gs.importFromDB("select id, name as keyword, sn, shop_price, shop_id, cate_id from cs_goods");
    }
	
	@Test
	public void testImport() throws Exception {
		searchService.importFromMysql("select * from sys_user", "scaffold", "import_user", "user_river");
	}
}
