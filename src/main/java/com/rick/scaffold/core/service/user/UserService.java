package com.rick.scaffold.core.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rick.scaffold.core.dao.user.UserDao;
import com.rick.scaffold.core.entity.user.User;
import com.rick.scaffold.core.service.generic.CrudService;

@Service
@Transactional(readOnly = true)
public class UserService extends CrudService<UserDao, User> {
	
	private UserDao userDao;
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
		super.dao = userDao;
	}
}

