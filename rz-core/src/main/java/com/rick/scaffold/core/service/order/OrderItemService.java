package com.rick.scaffold.core.service.order;

import com.rick.scaffold.core.dao.order.OrderItemDao;
import com.rick.scaffold.core.entity.order.OrderItem;
import com.rick.scaffold.core.service.generic.BaseService;
import org.springframework.stereotype.Service;

/**
 * Created by user on 16/3/10.
 */
@Service
public class OrderItemService extends BaseService<OrderItemDao, OrderItem> {
}
