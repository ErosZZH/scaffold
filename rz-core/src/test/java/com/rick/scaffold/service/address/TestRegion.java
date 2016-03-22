package com.rick.scaffold.service.address;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.net.URL;

import javax.jms.DeliveryMode;
import javax.jms.MapMessage;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;

import net.mikesu.fastdfs.FastdfsClient;
import net.mikesu.fastdfs.FastdfsClientFactory;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.rick.scaffold.base.BaseTest;
import com.rick.scaffold.common.jsontool.JsonMapper;
import com.rick.scaffold.core.entity.address.Region;
import com.rick.scaffold.core.service.address.RegionService;

/**
 * Created by user on 16/3/10.
 */
public class TestRegion extends BaseTest {

    @Autowired
    private RegionService rs;

    @Autowired
    private FastdfsClientFactory fcf;

    @Test
    public void testGetRegion() {
        Region region = rs.findOne(3L);
        System.out.println(JsonMapper.getInstance().toJson(region));
    }

    @Test
    public void testFastdfs() throws Exception {
        fcf.getFastdfsClient();
        FastdfsClient fastdfsClient = fcf.getFastdfsClient();
        URL fileUrl = this.getClass().getResource("/IMG_0003.JPG");
        File file = new File(fileUrl.getPath());
        String fileId = fastdfsClient.upload(file);
        System.out.println("fileId:"+fileId);
        assertNotNull(fileId);
    }
}
