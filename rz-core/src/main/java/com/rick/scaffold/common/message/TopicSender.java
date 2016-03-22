package com.rick.scaffold.common.message;

import java.util.Map;

import javax.jms.DeliveryMode;
import javax.jms.MapMessage;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 发布订阅模式
 * @author eros
 *
 */
public class TopicSender {

	private String broker_url = "tcp://192.168.6.46:61616";
	private String destination = "image.compress.topic";
	
	private TopicSender(String broker_url, String destination) {
		this.broker_url = broker_url;
		this.destination = destination;
	}
	
	private void sendMessage(TopicSession session, TopicPublisher publisher, Map<String, String> paramMap) throws Exception {
		MapMessage map = session.createMapMessage();
		map.setString("sourceDir", paramMap.get("sourceDir"));
		map.setString("targetDir", paramMap.get("targetDir"));
		System.out.println(map);
		publisher.send(map);
	}
	
	public void run(Map<String, String> map) throws Exception {
		TopicConnection connection = null;
		TopicSession session = null;
		try {
			TopicConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, 
					ActiveMQConnection.DEFAULT_PASSWORD, broker_url);
			connection = factory.createTopicConnection();
			connection.start();
			session = connection.createTopicSession(true, Session.AUTO_ACKNOWLEDGE);
			Topic topic = session.createTopic(destination);
			TopicPublisher publisher = session.createPublisher(topic);
			publisher.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			sendMessage(session, publisher, map);
			session.commit();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) {
				session.close();
			}
			if(connection != null) {
				connection.close();
			}
		}
	}

}

