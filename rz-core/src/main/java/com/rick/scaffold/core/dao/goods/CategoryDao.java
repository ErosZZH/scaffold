package com.rick.scaffold.core.dao.goods;

import com.rick.scaffold.common.persistence.annotation.MyBatisDao;
import com.rick.scaffold.core.dao.generic.CrudDao;
import com.rick.scaffold.core.entity.goods.Category;

@MyBatisDao
public interface CategoryDao extends CrudDao<Category> {

}