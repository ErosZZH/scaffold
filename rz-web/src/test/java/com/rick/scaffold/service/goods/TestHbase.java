package com.rick.scaffold.service.goods;

import com.rick.scaffold.base.BaseTest;
import com.rick.scaffold.soa.hbase.HbaseResult;
import com.rick.scaffold.soa.hbase.service.GoodsHbase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by user on 16/3/18.
 */
public class TestHbase extends BaseTest {

    @Autowired
    private GoodsHbase gh;

    @Test
    public void testInsertGoodsDescription() throws Exception {
        gh.insertDescription("1-4", "Rick帅的一比");
    }

    @Test
    public void testGetData() throws Exception {
        List<HbaseResult> hrs = gh.getDescription("1-4");
        for(HbaseResult hr: hrs) {
            System.out.println(hr.getValue());
        }
        System.out.println(hrs.size());
    }

    @Test
    public void testScanData() throws Exception {
        List<HbaseResult> hrs = gh.scanData("1-4", "1-5");
        for(HbaseResult hr: hrs) {
            System.out.println(hr.getValue());
        }
        System.out.println(hrs.size());
    }

    @Test
    public void testDeleteRow() throws Exception {
        gh.delDescription("1-4");
    }
}
