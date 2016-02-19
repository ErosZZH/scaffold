package com.rick.scaffold.common.http;

import java.io.IOException;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

@Component
public class HttpServiceImpl implements HttpService {

	
	
	@Override
	public String put(String url, String params) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPut httpput = new HttpPut(url);
		//timeout
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(5000).setConnectTimeout(2000).build();
		httpput.setConfig(requestConfig);
		
		httpput.setHeader("Content-type", "application/json");
		StringEntity requestParam = new StringEntity(params,"utf-8");
        httpput.setEntity(requestParam); 
        CloseableHttpResponse response = null;
        String result = null;
        try {
        	response = httpclient.execute(httpput);
		    result = EntityUtils.toString(response.getEntity());
		    System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(response != null) {
					response.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
