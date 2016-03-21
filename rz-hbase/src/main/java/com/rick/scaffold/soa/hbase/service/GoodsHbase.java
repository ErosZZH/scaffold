package com.rick.scaffold.soa.hbase.service;

import com.rick.scaffold.soa.hbase.HbaseResult;

import java.io.IOException;
import java.util.List;

/**
 * Created by user on 16/3/18.
 */
public interface GoodsHbase {

    void createTable();

    void deleteTable();

    void insertDescription(String rowkey, String val);

    List<HbaseResult> getDescription(String rowkey);

    List<HbaseResult> scanData(String startRow, String stopRow);

    void delDescription(String rowkey) throws IOException;
}
