package com.rick.scaffold.hbase.module.goods;

import com.rick.scaffold.hbase.HbaseConstants;
import com.rick.scaffold.hbase.delegate.HbaseService;
import com.rick.scaffold.soa.hbase.HbaseResult;
import com.rick.scaffold.soa.hbase.service.GoodsHbase;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

/**
 * Created by user on 16/3/18.
 */
@Service("goodsHbaseService")
public class GoodsHbaseImpl implements GoodsHbase {

    private static Logger logger = Logger.getLogger(GoodsHbaseImpl.class);

    @Autowired
    private HbaseService service;

    @Override
    @PostConstruct
    public void createTable() {
        String[] cols = {"goods", "order"};
        try {
            service.createTable(HbaseConstants.DBNAME, cols);
        } catch (Exception e) {
            logger.error("Create table fail.", e);
        }

    }

    @Override
    public void deleteTable() {
        try {
            service.deleteTable(HbaseConstants.DBNAME);
        } catch (Exception e) {
            logger.error("Delete table fail.", e);
        }
    }

    @Override
    public void insertDescription(String rowkey, String val) {
        try {
            service.insertRow(HbaseConstants.DBNAME, rowkey, "goods", "description", val);
        } catch (IOException e) {
            logger.error("Insert goods description fail.", e);
        }
    }

    @Override
    public List<HbaseResult> getDescription(String rowkey) {
        try {
            return service.getData(HbaseConstants.DBNAME, rowkey, "goods", "description");
        } catch (IOException e) {
            logger.error("Get goods description fail.", e);
            return null;
        }
    }

    @Override
    public List<HbaseResult> scanData(String startRow, String stopRow) {
        try {
            return service.scanData(HbaseConstants.DBNAME, startRow, stopRow);
        } catch (IOException e) {
            logger.error("Scan goods description fail.", e);
            return null;
        }
    }

    @Override
    public void delDescription(String rowkey) throws IOException {
        try {
            service.deleRow(HbaseConstants.DBNAME, rowkey, "goods", "description");
        } catch (IOException e) {
            logger.error("Get goods description fail.", e);
        }
    }
}
