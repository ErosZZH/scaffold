package com.rick.scaffold.core.service.goods;

import com.rick.scaffold.core.dao.goods.GoodsDao;
import com.rick.scaffold.core.entity.goods.Goods;
import com.rick.scaffold.core.service.generic.CrudService;
import org.springframework.stereotype.Service;

/**
 * Created by user on 16/3/10.
 */
@Service
public class GoodsService extends CrudService<GoodsDao, Goods> {
}
