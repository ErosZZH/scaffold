package com.rick.scaffold.core.dao.admin;

import com.rick.scaffold.common.persistence.annotation.MyBatisDao;
import com.rick.scaffold.core.dao.generic.CrudDao;
import com.rick.scaffold.core.entity.admin.Admin;

@MyBatisDao
public interface AdminDao extends CrudDao<Admin> {

}