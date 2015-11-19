package com.rick.scaffold.service.user;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.rick.scaffold.base.BaseTest;
import com.rick.scaffold.common.jsontool.JsonMapper;
import com.rick.scaffold.common.search.SearchService;
import com.rick.scaffold.common.search.services.delegate.SearchDelegate;
import com.rick.scaffold.core.entity.user.User;

public class TestES extends BaseTest {

	@Autowired
	private SearchService ss;
	
	@Autowired
	private SearchDelegate sd;
	
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
