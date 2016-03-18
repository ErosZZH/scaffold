package com.rick.scaffold.service.user;

import com.rick.scaffold.base.BaseTest;
import com.rick.scaffold.common.utils.FileUtils;
import com.rick.scaffold.soa.search.SearchEntry;
import com.rick.scaffold.soa.search.SearchKeywords;
import com.rick.scaffold.soa.search.SearchResult;
import com.rick.scaffold.soa.search.model.IndexGoods;
import com.rick.scaffold.soa.search.service.GoodsSearch;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TestES extends BaseTest {

	@Autowired
	private GoodsSearch gs;
	
	
	@Test
	public void testCreateIndex() {
        IndexGoods ig = new IndexGoods();
        ig.setCate_id(1L);
        ig.setName("指南针6");
        ig.setShop_id(1L);
        ig.setShop_price(20f);
        ig.setSn("123456");
		ig.setId(200001L);
		gs.createIndex(ig);
	}
	
	@Test
	public void testDeleteIndex() {
		try {
			gs.deleteIndex(200001L);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSearchKeyWords() {
		String index = "scaffold";
		String type = "goods";
		SearchKeywords sk = gs.searchAutoComplete(index, type, "康师傅", 10);
		System.out.println(sk.getKeywords().size());
		for(String s: sk.getKeywords()) {
			System.out.print(s + ",");
		}
	}
	
	@Test
	public void testSearch() {
        String json = FileUtils.readFileAsString("complex_search.json");
        SearchResult sr = gs.search(json, 0, -1);
        for(SearchEntry se: sr.getEntries()) {
            IndexGoods goods = (IndexGoods)se.getIndexObject();
            System.out.println(goods.getName());
        }
	}
	

}
