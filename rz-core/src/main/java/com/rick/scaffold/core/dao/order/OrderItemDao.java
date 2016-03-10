package com.rick.scaffold.core.dao.order;

import com.rick.scaffold.common.persistence.annotation.MyBatisDao;
import com.rick.scaffold.core.dao.generic.BaseDao;
import com.rick.scaffold.core.entity.order.OrderItem;

@MyBatisDao
public interface OrderItemDao extends BaseDao<OrderItem> {

}