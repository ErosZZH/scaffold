package com.rick.scaffold.service.address;

import com.rick.scaffold.base.BaseTest;
import com.rick.scaffold.common.jsontool.JsonMapper;
import com.rick.scaffold.core.entity.address.Region;
import com.rick.scaffold.core.service.address.RegionService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by user on 16/3/10.
 */
public class TestRegion extends BaseTest {

    @Autowired
    private RegionService rs;

    @Test
    public void testGetRegion() {
        Region region = rs.findOne(3L);
        System.out.println(JsonMapper.getInstance().toJson(region));
    }
}
