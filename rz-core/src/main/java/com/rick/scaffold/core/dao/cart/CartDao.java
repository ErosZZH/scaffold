package com.rick.scaffold.core.dao.cart;

import com.rick.scaffold.common.persistence.annotation.MyBatisDao;
import com.rick.scaffold.core.dao.generic.CrudDao;
import com.rick.scaffold.core.entity.cart.Cart;

@MyBatisDao
public interface CartDao extends CrudDao<Cart> {

}