package com.rick.scaffold.soa.hbase;

import java.io.Serializable;

/**
 * Created by user on 16/3/18.
 */
public class HbaseResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private String rowKey;

    private Long timestamp;

    private String colFamily;

    private String colName;

    private String value;

    public String getRowKey() {
        return rowKey;
    }

    public void setRowKey(String rowKey) {
        this.rowKey = rowKey;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getColFamily() {
        return colFamily;
    }

    public void setColFamily(String colFamily) {
        this.colFamily = colFamily;
    }

    public String getColName() {
        return colName;
    }

    public void setColName(String colName) {
        this.colName = colName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
