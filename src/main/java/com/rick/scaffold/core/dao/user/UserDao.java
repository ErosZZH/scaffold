package com.rick.scaffold.core.dao.user;

import com.rick.scaffold.common.persistence.annotation.MyBatisDao;
import com.rick.scaffold.core.dao.generic.CrudDao;
import com.rick.scaffold.core.entity.user.User;

@MyBatisDao
public interface UserDao extends CrudDao<User> {

}