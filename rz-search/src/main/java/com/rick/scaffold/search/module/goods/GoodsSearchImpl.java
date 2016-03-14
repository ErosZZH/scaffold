package com.rick.scaffold.search.module.goods;

import com.rick.scaffold.search.SearchConstants;
import com.rick.scaffold.search.services.RZSearchResponse;
import com.rick.scaffold.search.services.RZSearchService;
import com.rick.scaffold.soa.search.SearchKeywords;
import com.rick.scaffold.soa.search.service.GoodsSearch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service("goodsSearchService")
public class GoodsSearchImpl implements GoodsSearch {
	
	private static final Logger logger = LoggerFactory
			.getLogger(GoodsSearchImpl.class);

	private final static String TYPE = "goods";

	@Autowired
	private RZSearchService searchService;

	@Override
	public void importFromDB(String sql) throws Exception {
		searchService.importFromMysql(sql, SearchConstants.indice, TYPE, "goods_river");
	}
	
	@Override
	public SearchKeywords searchForKeywords(String index, String type,
			String jsonString, int entriesCount) {
		try {
			RZSearchResponse response = searchService.searchAutoComplete(
					index, jsonString, type, entriesCount);
			SearchKeywords keywords = new SearchKeywords();
			keywords.setKeywords(Arrays.asList(response.getInlineSearchList()));
			return keywords;
		} catch (Exception e) {
			logger.error("Error while searching keywords " + jsonString, e);
			return null;
		}
	}

}
