package com.rick.scaffold.search.utils;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

import javax.annotation.PreDestroy;

import org.apache.log4j.Logger;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.node.Node;

/**
 * Singleton
 * 
 * @author Carl Samson
 * 
 */
public class SearchClient {

	private static Logger log = Logger.getLogger(SearchClient.class);

	private Client client = null;
	private Node node = null;
	private ServerConfiguration serverConfiguration = null;
	
	private SearchClient() {
		
	}

	public ServerConfiguration getServerConfiguration() {
		return serverConfiguration;
	}

	public void setServerConfiguration(ServerConfiguration serverConfiguration) {
		this.serverConfiguration = serverConfiguration;
	}
	
	public Client getClient() {
		if (client == null) {
			initClient();
		}
		return client;
	}

	@PreDestroy
	public void stopClient() {
		if (client != null) {
			client.close();
		}
		if (node != null) {
			node.close();
		}
	}

	private synchronized void initClient() {
		if (client == null) {
			try {
				if (serverConfiguration.getMode().equalsIgnoreCase("remote")) {
					Settings s = ImmutableSettings
							.settingsBuilder()
							.put("cluster.name", serverConfiguration.getClusterName())
							.build();
					client = new TransportClient(s)
							.addTransportAddress(new InetSocketTransportAddress(
									serverConfiguration.getClusterHost(),
									serverConfiguration.getClusterPort()));
				} else {
					if(node == null) {
						node = nodeBuilder()
								.clusterName(serverConfiguration.getClusterName())
								.local(true).node();
					}
					client = node.client();
				}
				log.debug("****** ES client ready ********");
			} catch (Exception e) {
				log.warn("Can't start ES client.", e);
			}
		}
	}
}
