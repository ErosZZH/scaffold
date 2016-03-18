package com.rick.scaffold.core.service.order;

import com.rick.scaffold.core.dao.order.OrderDao;
import com.rick.scaffold.core.entity.order.Order;
import com.rick.scaffold.core.service.generic.CrudService;
import org.springframework.stereotype.Service;

/**
 * Created by user on 16/3/10.
 */
@Service
public class OrderService extends CrudService<OrderDao, Order> {
}
