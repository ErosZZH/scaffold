package com.rick.scaffold.service.goods;

import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Maps;
import com.rick.scaffold.base.BaseTest;
import com.rick.scaffold.common.jsontool.JsonMapper;
import com.rick.scaffold.common.message.TopicSender;
import com.rick.scaffold.core.entity.goods.Goods;
import com.rick.scaffold.core.service.goods.GoodsService;
import com.rick.scaffold.soa.hbase.service.GoodsHbase;

/**
 * Created by user on 16/3/21.
 */
public class TestGoods extends BaseTest {

    @Autowired
    private GoodsService gs;

    @Autowired
    private GoodsHbase gh;
    
    @Autowired
    private TopicSender ts;

    @Test
    public void testGetGoods() {
        long goodsId = 4L;
        Goods g = gs.findOne(goodsId);
        g.setDescription(gh.getDescription(g.getShopId() + "-" + goodsId).get(0).getValue());
        System.out.println(JsonMapper.toJsonString(g));
    }
    
    @Test
    public void testMessage() throws Exception {
    	Map<String, String> map = Maps.newHashMap();
    	map.put("sourceDir", "/home/eros/1.png");
    	map.put("targetDir", "/home/eros/1_small.png");
    	ts.run(map);
    }
}
