package com.rick.scaffold.service.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rick.scaffold.core.entity.user.User;
import com.rick.scaffold.core.service.user.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-applicationContext-all.xml")
public class TestUser {

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
