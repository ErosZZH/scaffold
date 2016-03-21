package com.rick.scaffold.hbase.utils;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.log4j.Logger;

import javax.annotation.PreDestroy;
import java.io.IOException;

/**
 * Created by user on 16/3/18.
 */
public class HbaseClient {

    private static Logger logger = Logger.getLogger(HbaseClient.class);

    private Configuration configuration;

    private Connection connection;

    private Admin admin;


    private HbaseClient(String host, String port) {
        if(configuration == null) {
            init(host, port);
        }
    }

    public synchronized void init(String host, String port) {
        if(configuration == null) {
            configuration = HBaseConfiguration.create();
            configuration.set("hbase.zookeeper.quorum", host);
            configuration.set("hbase.zookeeper.property.clientPort", port);
//        configuration.set("zookeeper.znode.parent", "/hbase");
            try {
                connection = ConnectionFactory.createConnection(configuration);
                admin = connection.getAdmin();
            } catch (IOException e) {
                logger.error("Connect to hbase fail.", e);
            }
        }

    }

    @PreDestroy
    public void close() {
        try {
            if (null != admin) admin.close();
            if (null != connection) connection.close();
        } catch (IOException e) {
            logger.error("Can't close hbase client.", e);
        }
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public Connection getConnection() {
        return connection;
    }

    public Admin getAdmin() {
        return admin;
    }
}
