package com.rick.scaffold.soa.search.service;

import com.rick.scaffold.soa.search.SearchKeywords;
import com.rick.scaffold.soa.search.SearchResult;
import com.rick.scaffold.soa.search.model.IndexGoods;

public interface GoodsSearch {

	void importFromDB(String sql) throws Exception;

    void importKeywordFromDB(String sql) throws Exception;

    void createIndex(IndexGoods goods);

    void deleteIndex(Long id);

	SearchKeywords searchAutoComplete(String index, String type, String keyword, int entriesCount);

    SearchResult search(String jsonString, int startIndex, int entriesCount);
}
