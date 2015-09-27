package com.rick.scaffold.core.service.user;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Maps;
import com.rick.scaffold.core.dao.user.UserDao;
import com.rick.scaffold.core.entity.user.FullUser;
import com.rick.scaffold.core.entity.user.User;
import com.rick.scaffold.core.service.generic.CrudService;
import com.rick.scaffold.core.vo.Paginator;

@Service
@Transactional(readOnly = true)
public class UserService extends CrudService<UserDao, User> {
	
	@Autowired
	private UserDao userDao;
	
	public Map<String, Object> findAll1() {
		PageHelper.startPage(1, 10, "id desc");
		List<User> users = userDao.findAll();
		Page<User> page = (Page<User>)users;
		Paginator p = new Paginator();
		BeanUtils.copyProperties(page, p);
		Map<String, Object> map = Maps.newHashMap();
		map.put("users", users);
		map.put("page", p);
		return map;
	}
	
	public User findOne1() {
		return userDao.findOne1("56077e0d6a8ad31d5fe33a63", "zzh1");
	}
	
	public List<FullUser> findCascade(String id) {
		return userDao.findCascade(id);
	}
	
	public List<FullUser> findLazy(String id) {
		return userDao.findLazy(id);
	}
}

