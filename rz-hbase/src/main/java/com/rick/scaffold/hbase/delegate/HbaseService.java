package com.rick.scaffold.hbase.delegate;

import com.rick.scaffold.soa.hbase.HbaseResult;

import java.io.IOException;
import java.util.List;

/**
 * Created by user on 16/3/18.
 */
public interface HbaseService {

    void createTable(String tableName, String[] cols) throws IOException;

    void deleteTable(String tableName) throws IOException;

    void insertRow(String tableName, String rowkey, String colFamily, String col, String val) throws IOException;

    List<HbaseResult> getData(String tableName, String rowkey, String colFamily, String col) throws IOException;

    List<HbaseResult> scanData(String tableName, String startRow, String stopRow) throws IOException;

    void deleRow(String tableName, String rowkey, String colFamily, String col) throws IOException;
}
