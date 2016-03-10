package com.rick.scaffold.core.dao.goods;

import com.rick.scaffold.common.persistence.annotation.MyBatisDao;
import com.rick.scaffold.core.dao.generic.CrudDao;
import com.rick.scaffold.core.entity.goods.Goods;

@MyBatisDao
public interface GoodsDao extends CrudDao<Goods> {

}