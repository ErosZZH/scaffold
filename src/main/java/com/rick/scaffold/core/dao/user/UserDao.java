package com.rick.scaffold.core.dao.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.rick.scaffold.common.persistence.annotation.MyBatisDao;
import com.rick.scaffold.core.dao.generic.CrudDao;
import com.rick.scaffold.core.entity.user.FullUser;
import com.rick.scaffold.core.entity.user.User;

@MyBatisDao
public interface UserDao extends CrudDao<User> {

	User findOne1(@Param("id") long id, @Param("name") String name);
	
	List<FullUser> findCascade(long id);
	
	List<FullUser> findLazy(long id);
}