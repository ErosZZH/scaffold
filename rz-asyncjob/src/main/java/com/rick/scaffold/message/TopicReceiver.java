package com.rick.scaffold.message;

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.Logger;

import com.rick.scaffold.utils.ImageUtils;

public class TopicReceiver {
	
	private static Logger log = Logger.getLogger(TopicReceiver.class);

	public static final String BROKER_URL = "tcp://192.168.6.46:61616";
	public static final String DESTINATION = "image.compress.topic";
	
	public static void run() throws Exception {
		TopicConnection connection = null;
		TopicSession session = null;
		try {
			TopicConnectionFactory factory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, 
					ActiveMQConnection.DEFAULT_PASSWORD, BROKER_URL);
			connection = factory.createTopicConnection();
			connection.start();
			session = connection.createTopicSession(true, Session.AUTO_ACKNOWLEDGE);
			Topic topic = session.createTopic(DESTINATION);
			TopicSubscriber subscriber = session.createSubscriber(topic);
			subscriber.setMessageListener(new MessageListener() {
				@Override
				public void onMessage(Message msg) {
					if(msg != null) {
						MapMessage map = (MapMessage)msg;
						try {
							String sourceDir = map.getString("sourceDir");
							String targetDir = map.getString("targetDir");
							System.out.println(sourceDir + "接收#" + targetDir);
							ImageUtils.resetSize(sourceDir, targetDir);
						} catch (Exception e) {
							log.error("Error", e);
							e.printStackTrace();
						}
					}
				}
			});
			
			while(true) {
				Thread.sleep(1000 * 100);
			}
			
		} catch(Exception e) {
			log.error("Error", e);
			e.printStackTrace();
		} finally {
			if(session != null) {
				session.commit();
				session.close();
			}
			if(connection != null) {
				connection.close();
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println("Image compress receiver started.");
		TopicReceiver.run();
	}
}
