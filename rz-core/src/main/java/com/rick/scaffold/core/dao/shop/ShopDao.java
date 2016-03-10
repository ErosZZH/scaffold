package com.rick.scaffold.core.dao.shop;

import com.rick.scaffold.common.persistence.annotation.MyBatisDao;
import com.rick.scaffold.core.dao.generic.CrudDao;
import com.rick.scaffold.core.entity.shop.Shop;

@MyBatisDao
public interface ShopDao extends CrudDao<Shop> {

}