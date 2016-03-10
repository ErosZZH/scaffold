package com.rick.scaffold.service.user;

import com.google.common.base.Optional;
import com.rick.scaffold.base.BaseTest;
import com.rick.scaffold.common.jsontool.JsonMapper;
import com.rick.scaffold.common.security.SecurityComponent;
import com.rick.scaffold.common.utils.JedisUtils;
import com.rick.scaffold.core.entity.user.FullUser;
import com.rick.scaffold.core.entity.user.Photo;
import com.rick.scaffold.core.entity.user.User;
import com.rick.scaffold.core.entity.user.Users;
import com.rick.scaffold.core.service.user.PhotoService;
import com.rick.scaffold.core.service.user.UserService;
import com.rick.scaffold.core.service.user.UsersService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class TestUsers extends BaseTest{

	@Autowired
	private UsersService us;
	
	@Autowired
	private SecurityComponent sc;

	
	@Test
	public void testAddUserWithPassword() {
		Users user = new Users();
		user.setUsername("zzh");
		user.setPassword(sc.sha1Hash("password"));
		int res = us.save(user);
		System.out.println(res);
	}
	

}
