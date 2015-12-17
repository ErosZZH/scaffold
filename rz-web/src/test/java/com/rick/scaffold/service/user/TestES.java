package com.rick.scaffold.service.user;

import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.rick.scaffold.base.BaseTest;
import com.rick.scaffold.common.jsontool.JsonMapper;
import com.rick.scaffold.common.search.biz.SearchEntry;
import com.rick.scaffold.common.search.biz.SearchKeywords;
import com.rick.scaffold.common.search.biz.SearchResult;
import com.rick.scaffold.common.search.biz.user.IndexUser;
import com.rick.scaffold.common.search.biz.user.UserSearch;
import com.rick.scaffold.common.search.services.RZSearchService;
import com.rick.scaffold.common.search.services.delegate.SearchDelegate;
import com.rick.scaffold.core.entity.user.User;

public class TestES extends BaseTest {

	@Autowired
	private UserSearch ss;
	
	@Autowired
	private SearchDelegate sd;
	
	@Autowired
	private RZSearchService searchService;
	
	@Test
	public void testCreateIndex() {
		User user = new User();
		user.setId("abc");
		user.setEmail("a@b.com");
		user.setName("中华人民共和国国歌");
		user.setLoginName("eroszzh");
		user.setPhone("13140998809");
		ss.createIndex(user);
	}
	
	@Test
	public void testDeleteIndex() {
		ss.deleteIndex("abc");
	}
	
	@Test
	public void testSearchKeyWords() {
		String index = "scaffold";
		String query = QueryBuilders.matchQuery("keyword", "中华").toString();
		System.out.println(query);
		SearchKeywords sk = ss.searchForKeywords(index, query, -1);
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
			IndexUser user = se.getIndexUser();
			System.out.println(user.getLoginName());
		}
	}
	
	@Test
	public void testImport() throws Exception {
		searchService.importFromMysql("select * from sys_user", "scaffold", "import_user");
	}
	
	@Test
	public void testCreateIndexAgain() {
		User user = new User();
		user.setId("abc");
		user.setEmail("a@b.com");
		user.setName("Rick");
		user.setLoginName("eroszzh");
		user.setPhone("13140998809");
		sd.index(JsonMapper.toJsonString(user), "scaffold", "user", "abc");
	}

}
