package com.rick.scaffold.core.service.cart;

import com.rick.scaffold.core.dao.cart.CartItemDao;
import com.rick.scaffold.core.entity.cart.CartItem;
import com.rick.scaffold.core.service.generic.BaseService;
import org.springframework.stereotype.Service;

/**
 * Created by user on 16/3/10.
 */
@Service
public class CartItemService extends BaseService<CartItemDao, CartItem> {
}
