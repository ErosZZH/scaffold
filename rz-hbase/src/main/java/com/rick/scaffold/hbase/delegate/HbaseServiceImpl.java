package com.rick.scaffold.hbase.delegate;

import com.google.common.collect.Lists;
import com.rick.scaffold.hbase.utils.HbaseClient;
import com.rick.scaffold.soa.hbase.HbaseResult;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.List;

/**
 * Created by user on 16/3/18.
 */
@Service
public class HbaseServiceImpl implements HbaseService {

    private static Logger logger = Logger.getLogger(HbaseServiceImpl.class);

    @Autowired
    private HbaseClient client;

    @Override
    public void createTable(String tableName, String[] cols) throws IOException {
        Admin admin = client.getAdmin();
        TableName tn = TableName.valueOf(tableName);
        if (admin.tableExists(tn)) {
            logger.debug("Table " + tableName + " exists.");
        } else {
            HTableDescriptor hTableDescriptor = new HTableDescriptor(tableName);
            for (String col : cols) {
                HColumnDescriptor hColumnDescriptor = new HColumnDescriptor(col);
                hTableDescriptor.addFamily(hColumnDescriptor);
            }
            admin.createTable(hTableDescriptor);
        }
    }

    @Override
    public void deleteTable(String tableName) throws IOException {
        Admin admin = client.getAdmin();
        TableName tn = TableName.valueOf(tableName);
        if (admin.tableExists(tn)) {
            admin.disableTable(tn);
            admin.deleteTable(tn);
        }
    }

    @Override
    public void insertRow(String tableName, String rowkey, String colFamily, String col, String val) throws IOException {
        Table table = client.getConnection().getTable(TableName.valueOf(tableName));
        Put put = new Put(Bytes.toBytes(rowkey));
        put.addColumn(Bytes.toBytes(colFamily), Bytes.toBytes(col), Bytes.toBytes(val));
        table.put(put);
        table.close();
    }

    @Override
    public List<HbaseResult> getData(String tableName, String rowkey, String colFamily, String col) throws IOException {
        Table table = client.getConnection().getTable(TableName.valueOf(tableName));
        Get get = new Get(Bytes.toBytes(rowkey));
        if(colFamily != null) {
            get.addFamily(Bytes.toBytes(colFamily));
            if(col != null) {
                get.addColumn(Bytes.toBytes(colFamily),Bytes.toBytes(col));
            }
        }
        Result result = table.get(get);
        List<HbaseResult> res = Lists.newArrayList();
        Cell[] cells = result.rawCells();
        for (Cell cell : cells) {
            HbaseResult hr = new HbaseResult();
            hr.setRowKey(new String(CellUtil.cloneRow(cell)));
            hr.setTimestamp(cell.getTimestamp());
            hr.setColFamily(new String(CellUtil.cloneFamily(cell)));
            hr.setColName(new String(CellUtil.cloneQualifier(cell)));
            hr.setValue(new String(CellUtil.cloneValue(cell)));
            res.add(hr);
        }
        table.close();
        return res;
    }

    @Override
    public List<HbaseResult> scanData(String tableName, String startRow, String stopRow) throws IOException {
        Table table = client.getConnection().getTable(TableName.valueOf(tableName));
        Scan scan = new Scan();
        scan.setStartRow(Bytes.toBytes(startRow));
        scan.setStopRow(Bytes.toBytes(stopRow));
        ResultScanner resultScanner = table.getScanner(scan);
        List<HbaseResult> res = Lists.newArrayList();
        for (Result result : resultScanner) {
            Cell[] cells = result.rawCells();
            for (Cell cell : cells) {
                HbaseResult hr = new HbaseResult();
                hr.setRowKey(new String(CellUtil.cloneRow(cell)));
                hr.setTimestamp(cell.getTimestamp());
                hr.setColFamily(new String(CellUtil.cloneFamily(cell)));
                hr.setColName(new String(CellUtil.cloneQualifier(cell)));
                hr.setValue(new String(CellUtil.cloneValue(cell)));
                res.add(hr);
            }
        }
        table.close();
        return res;
    }

    @Override
    public void deleRow(String tableName, String rowkey, String colFamily, String col) throws IOException {
        Table table = client.getConnection().getTable(TableName.valueOf(tableName));
        Delete delete = new Delete(Bytes.toBytes(rowkey));
        if(colFamily != null) {
            delete.addFamily(Bytes.toBytes(colFamily));
            if(col != null) {
                delete.addColumn(Bytes.toBytes(colFamily),Bytes.toBytes(col));
            }
        }
        table.delete(delete);
        table.close();
    }
}
