package com.rick.scaffold.core.dao.order;

import com.rick.scaffold.common.persistence.annotation.MyBatisDao;
import com.rick.scaffold.core.dao.generic.CrudDao;
import com.rick.scaffold.core.entity.order.Order;

@MyBatisDao
public interface OrderDao extends CrudDao<Order> {

}