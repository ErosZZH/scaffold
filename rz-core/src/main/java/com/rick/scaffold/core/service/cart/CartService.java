package com.rick.scaffold.core.service.cart;

import com.rick.scaffold.core.dao.cart.CartDao;
import com.rick.scaffold.core.entity.cart.Cart;
import com.rick.scaffold.core.service.generic.CrudService;
import org.springframework.stereotype.Service;

/**
 * Created by user on 16/3/10.
 */
@Service
public class CartService extends CrudService<CartDao, Cart> {
}
