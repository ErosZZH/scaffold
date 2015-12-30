package com.rick.scaffold.soa.search.service;

import javax.annotation.PostConstruct;

public interface ProductSearch {

	void importFromDB(String sql) throws Exception ;

	@PostConstruct
	void initService();
}
