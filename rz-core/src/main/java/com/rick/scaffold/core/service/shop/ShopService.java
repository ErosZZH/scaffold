package com.rick.scaffold.core.service.shop;

import com.rick.scaffold.core.dao.shop.ShopDao;
import com.rick.scaffold.core.entity.shop.Shop;
import com.rick.scaffold.core.service.generic.CrudService;
import org.springframework.stereotype.Service;

/**
 * Created by user on 16/3/10.
 */
@Service
public class ShopService extends CrudService<ShopDao, Shop> {
}
