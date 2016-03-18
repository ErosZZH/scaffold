package com.rick.scaffold.core.dao.cart;

import com.rick.scaffold.common.persistence.annotation.MyBatisDao;
import com.rick.scaffold.core.dao.generic.BaseDao;
import com.rick.scaffold.core.entity.cart.CartItem;

@MyBatisDao
public interface CartItemDao extends BaseDao<CartItem> {

}