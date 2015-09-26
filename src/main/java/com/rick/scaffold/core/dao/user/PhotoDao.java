package com.rick.scaffold.core.dao.user;

import com.rick.scaffold.common.persistence.annotation.MyBatisDao;
import com.rick.scaffold.core.dao.generic.BaseDao;
import com.rick.scaffold.core.entity.user.Photo;

@MyBatisDao
public interface PhotoDao extends BaseDao<Photo> {

}