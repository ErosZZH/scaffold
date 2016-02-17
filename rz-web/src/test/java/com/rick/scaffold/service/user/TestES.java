package com.rick.scaffold.service.user;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.rick.scaffold.base.BaseTest;
import com.rick.scaffold.core.entity.user.User;
import com.rick.scaffold.soa.search.SearchEntry;
import com.rick.scaffold.soa.search.SearchKeywords;
import com.rick.scaffold.soa.search.SearchResult;
import com.rick.scaffold.soa.search.model.IndexUser;
import com.rick.scaffold.soa.search.service.ProductSearch;
import com.rick.scaffold.soa.search.service.UserSearch;

public class TestES extends BaseTest {

	@Autowired
	private UserSearch ss;
	
	@Autowired
	private ProductSearch ps;
	
//	@Autowired
//	private SearchDelegate sd;
	
//	@Autowired
//	private RZSearchService searchService;
	
	@Test
	public void testCreateIndex() {
		User user = new User();
		user.setId(123L);
		user.setEmail("a@b.com");
		user.setName("中华人民共和国国歌");
		user.setLoginName("eroszzh");
		user.setPhone("13140998809");
		
		IndexUser indexObj = new IndexUser();

		indexObj.setId(user.getId());
		indexObj.setEmail(user.getEmail());
		indexObj.setLoginName(user.getLoginName());
		indexObj.setName(user.getName());
		indexObj.setPhone(user.getPhone());
		
		ss.createIndex(indexObj);
	}
	
	@Test
	public void testDeleteIndex() {
		ss.deleteIndex("123L");
	}
	
	@Test
	public void testSearchKeyWords() {
		String index = "scaffold";
		String type = "keyword_user";
//		String query = QueryBuilders.matchQuery("keyword", "国歌").toString();
		String query = "{\"match\" : {\"keyword\" : {\"query\" :\"国歌\",\"type\" : \"boolean\"}}}";
		System.out.println(query);
		SearchKeywords sk = ss.searchForKeywords(index, type, query, -1);
		System.out.println(sk.getKeywords().size());
		for(String s: sk.getKeywords()) {
			System.out.print(s + ",");
		}
	}
	
	@Test
	public void testSearchProduct() {
		String index = "scaffold";
		String type = "product";
		String query = "{\"match\" : {\"keyword\" : {\"query\" :\"妙洁\",\"type\" : \"boolean\"}}}";
		System.out.println(query);
		SearchKeywords sk = ps.searchForKeywords(index, type, query, 20);
		System.out.println(sk.getKeywords().size());
		for(String s: sk.getKeywords()) {
			System.out.print(s + ",");
		}
	}
	
	@Test
	public void testSearch() {
		String query = "{\"query\":{\"field\":{\"name\":\"中华\"}}}";
		SearchResult sr = ss.search(query, -1, 0);
		for(SearchEntry se: sr.getEntries()) {
			IndexUser user = (IndexUser)se.getIndexObject();
			System.out.println(user.getLoginName());
		}
	}
	
//	@Test
//	public void testImport() throws Exception {
//		searchService.importFromMysql("select * from sys_user", "scaffold", "import_user");
//	}
	
//	@Test
//	public void testCreateIndexAgain() {
//		User user = new User();
//		user.setId("abc");
//		user.setEmail("a@b.com");
//		user.setName("Rick");
//		user.setLoginName("eroszzh");
//		user.setPhone("13140998809");
//		sd.index(JsonMapper.toJsonString(user), "scaffold", "user", "abc");
//	}
//	
//	@Test
//	public void testTypeExist() throws Exception {
//		boolean res = sd.typeExist("scaffold", "user");
//		System.out.println(res);
//	}

}
