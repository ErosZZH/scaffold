package com.rick.scaffold.service.user;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.rick.scaffold.base.BaseTest;
import com.rick.scaffold.core.entity.user.User;
import com.rick.scaffold.core.service.user.UserService;

public class TestUser extends BaseTest{

	@Autowired
	private UserService us;
	
	@Test
	public void testAddUser() {
		User user = new User();
		user.setLoginName("Rick");
		user.setPassword("password");
		user.setName("zzh");
		int res = us.save(user);
		System.out.println(res);
	}
}
