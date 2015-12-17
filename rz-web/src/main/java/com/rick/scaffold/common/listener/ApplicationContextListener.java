package com.rick.scaffold.common.listener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.stereotype.Component;

import com.rick.scaffold.common.search.biz.user.UserSearch;

@Component
public class ApplicationContextListener implements
		ApplicationListener<ContextStartedEvent> {
	//ContextRefreshedEvent for test

	@Override
	public void onApplicationEvent(ContextStartedEvent event) {
		ApplicationContext applicationContext = event.getApplicationContext();
		UserSearch searchService = (UserSearch) applicationContext
				.getBean("userSearchService");
		searchService.initService();

	}

}
