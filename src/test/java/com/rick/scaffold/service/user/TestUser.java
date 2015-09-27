package com.rick.scaffold.service.user;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.base.Optional;
import com.rick.scaffold.base.BaseTest;
import com.rick.scaffold.common.jsontool.JsonMapper;
import com.rick.scaffold.common.utils.JedisUtils;
import com.rick.scaffold.core.entity.user.FullUser;
import com.rick.scaffold.core.entity.user.Photo;
import com.rick.scaffold.core.entity.user.User;
import com.rick.scaffold.core.service.user.PhotoService;
import com.rick.scaffold.core.service.user.UserService;

public class TestUser extends BaseTest{

	@Autowired
	private UserService us;
	
	@Autowired
	private PhotoService ps;
	
	@Test
	public void testAddUser() {
		User user = new User();
		user.setLoginName("Eros4");
		user.setPassword("password");
		user.setName("zzh4");
		user.setCompanyId("56077d096a8ad31ca69b9f3b");
		int res = us.saveAndCache(user);
		System.out.println(res);
	}
	
	@Test
	public void testFindOne() {
		String id = "5607cf1f6a8ad35221f687d5";  //cached
//		String id = "56077e0d6a8ad31d5fe33a63"; //uncached
//		String id = "56077e0d6a8ad31d5fe33a64"; //not exist
		User user = (User)Optional.fromNullable(JedisUtils.getObject(id)).or(us.findOne(id));
		System.out.println(JsonMapper.toJsonString(user));
	}
	
	@Test
	public void testFindOne1() {
		User user = us.findOne1();
		System.out.println(JsonMapper.toJsonString(user));
	}
	
	@Test
	public void testFindAll1() {
		Map<String, Object> users = us.findAll1();
		System.out.println(JsonMapper.toJsonString(users));
	}
	
	@Test
	public void testFindCascade() {
		List<FullUser> user = us.findCascade("56077e0d6a8ad31d5fe33a63");
		System.out.println(JsonMapper.toJsonString(user));
	}
	
	@Test
	public void testLazy() {
		List<FullUser> user = us.findLazy("56077e0d6a8ad31d5fe33a63");
//		FullUser u = user.get(0);
//		Photo p = u.getPhotos().get(0);
//		p.setSize(1);
//		ps.update(p);
//		u.setEmail("testEmail");
//		us.update(u);
		System.out.println(JsonMapper.toJsonString(user));
	}
}
