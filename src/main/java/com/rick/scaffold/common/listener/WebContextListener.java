package com.rick.scaffold.common.listener;

import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;

import com.rick.scaffold.common.config.Global;

public class WebContextListener extends org.springframework.web.context.ContextLoaderListener {
	
	@Override
	public WebApplicationContext initWebApplicationContext(ServletContext servletContext) {
		StringBuilder sb = new StringBuilder();
		sb.append("\r\n======================================================================\r\n");
		sb.append("\r\n    欢迎使用 "+ Global.getConfig("global.productName") + "  - Powered By http://www.rick.com\r\n");
		sb.append("\r\n======================================================================\r\n");
		System.out.println(sb.toString());
		return super.initWebApplicationContext(servletContext);
	}
}
