package com.rick.scaffold.service.goods;

import com.rick.scaffold.base.BaseTest;
import com.rick.scaffold.common.jsontool.JsonMapper;
import com.rick.scaffold.core.entity.goods.Goods;
import com.rick.scaffold.core.service.goods.GoodsService;
import com.rick.scaffold.soa.hbase.service.GoodsHbase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by user on 16/3/21.
 */
public class TestGoods extends BaseTest {

    @Autowired
    private GoodsService gs;

    @Autowired
    private GoodsHbase gh;

    @Test
    public void testGetGoods() {
        long goodsId = 4L;
        Goods g = gs.findOne(goodsId);
        g.setDescription(gh.getDescription(g.getShopId() + "-" + goodsId).get(0).getValue());
        System.out.println(JsonMapper.toJsonString(g));
    }
}
