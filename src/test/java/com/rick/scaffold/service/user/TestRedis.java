package com.rick.scaffold.service.user;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.rick.scaffold.base.BaseTest;
import com.rick.scaffold.common.jsontool.JsonMapper;
import com.rick.scaffold.common.utils.JedisUtils;
import com.rick.scaffold.core.entity.user.User;
import com.rick.scaffold.core.service.user.UserService;

public class TestRedis extends BaseTest {
	
	@Autowired
	private UserService us;

	@Test
	public void testRedis() {
		 JedisUtils.set("xx", "g", 0);
		 JedisUtils.set("xx", "e", 0);
		String s = JedisUtils.get("xx");
		List<String> list = JedisUtils.getList("xx");
		System.out.println(s);
		System.out.println(list == null);
		long res = JedisUtils.del("abc");
		System.out.println(res);
		long res1 = JedisUtils.del("xx");
		System.out.println(res1);
	}
	
	@Test
	public void testUS() {
		User user = us.findOne("56077e0d6a8ad31d5fe33a63");
		System.out.println(JsonMapper.toJsonString(user));
	}
}
