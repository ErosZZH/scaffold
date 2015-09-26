package com.rick.scaffold.service.user;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.rick.scaffold.base.BaseTest;
import com.rick.scaffold.common.jsontool.JsonMapper;
import com.rick.scaffold.core.entity.user.FullUser;
import com.rick.scaffold.core.entity.user.User;
import com.rick.scaffold.core.service.user.UserService;

public class TestUser extends BaseTest{

	@Autowired
	private UserService us;
	
	@Test
	public void testAddUser() {
		User user = new User();
		user.setLoginName("猪猪");
		user.setPassword("password");
		user.setName("zzh1");
		user.setCompanyId(1l);
		int res = us.save(user);
		System.out.println(res);
	}
	
	@Test
	public void testFindOne1() {
		User user = us.findOne1();
		System.out.println(JsonMapper.toJsonString(user));
	}
	
	@Test
	public void testFindCascade() {
		List<FullUser> user = us.findCascade(1l);
		System.out.println(JsonMapper.toJsonString(user));
	}
	
	@Test
	public void testLazy() {
		List<FullUser> user = us.findLazy(1l);
		System.out.println(JsonMapper.toJsonString(user));
	}
}
