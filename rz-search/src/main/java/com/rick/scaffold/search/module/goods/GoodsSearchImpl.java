package com.rick.scaffold.search.module.goods;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.rick.scaffold.search.SearchConstants;
import com.rick.scaffold.search.services.*;
import com.rick.scaffold.soa.search.SearchEntry;
import com.rick.scaffold.soa.search.SearchFacet;
import com.rick.scaffold.soa.search.SearchKeywords;
import com.rick.scaffold.soa.search.SearchResult;
import com.rick.scaffold.soa.search.model.IndexGoods;
import com.rick.scaffold.soa.search.service.GoodsSearch;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.search.highlight.HighlightField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("goodsSearchService")
public class GoodsSearchImpl implements GoodsSearch {
	
	private static final Logger logger = LoggerFactory
			.getLogger(GoodsSearchImpl.class);

	private final static String TYPE = "goods";

	@Autowired
	private RZSearchService searchService;

	@Override
	public void importFromDB(String sql) throws Exception {
		searchService.importFromMysql(sql, SearchConstants.INDICE, TYPE, "goods_river");
	}

    @Override
    public void importKeywordFromDB(String sql) throws Exception {
        searchService.importFromMysql(sql, SearchConstants.INDICE, "keyword_"+TYPE, "keyword_goods_river");
    }

    @Override
    public void createIndex(IndexGoods goods) {
        try {
            searchService.index(goods, SearchConstants.INDICE, TYPE);
        } catch (Exception e) {
            logger.error("Create index for goods fail.", e);
        }
    }

    @Override
    public void deleteIndex(Long id) {
        try {
            searchService.deleteObject(SearchConstants.INDICE, TYPE, id);
        } catch (Exception e) {
            logger.error("Delete index for user fail.", e);
        }
    }

    @Override
    public SearchKeywords searchAutoComplete(String index, String type, String keyword, int entriesCount) {
        try {
            RZSearchResponse response = searchService.searchAutoComplete(
                    index, type, keyword, entriesCount);
            SearchKeywords keywords = new SearchKeywords();
            keywords.setKeywords(Arrays.asList(response.getInlineSearchList()));
            return keywords;
        } catch (Exception e) {
            logger.error("Error while searching keywords " + keyword, e);
            return null;
        }
    }

    @Override
    public SearchResult search(String jsonString, int startIndex, int entriesCount) {
        try {
            String collectionName = SearchConstants.INDICE;
            RZSearchRequest request = new RZSearchRequest();
            request.setIndex(collectionName);
            request.setSize(entriesCount);
            request.setStart(startIndex);
            request.setType(TYPE);
            request.setJson(jsonString);

            RZSearchResponse response = searchService.search(request);
            SearchResult resp = new SearchResult();
            resp.setTotalCount(response.getCount());
            List<SearchEntry> entries = Lists.newArrayList();
            Collection<RZSearchHit> hits = response.getSearchHits();
            for (RZSearchHit hit : hits) {
                SearchEntry entry = new SearchEntry();
                Map<String, Object> metaEntries = hit.getMetaEntries();

                IndexGoods indexGoods = new IndexGoods();
                indexGoods.setId(Long.valueOf(hit.getId()));

                Map<String, Object> sourceEntries = (Map<String, Object>) metaEntries.get("source");
                indexGoods.setSn((String) sourceEntries.get("sn"));
                indexGoods.setShop_price(Float.valueOf(sourceEntries.get("shop_price").toString()));
                indexGoods.setName((String) sourceEntries.get("name"));
                indexGoods.setShop_id(Long.valueOf(sourceEntries.get("shop_id").toString()));
                indexGoods.setCate_id(Long.valueOf(sourceEntries.get("cate_id").toString()));
                entry.setIndexUser(indexGoods);
                entries.add(entry);

                Map<String, HighlightField> fields = hit.getHighlightFields();
                if (fields != null) {
                    List<String> highlights = Lists.newArrayList();
                    for (HighlightField field : fields.values()) {
                        String f = field.getName();
                        highlights.add(f);
                    }
                    entry.setHighlights(highlights);
                }
            }
            resp.setEntries(entries);

            Map<String, RZFacet> facets = response.getFacets();
            if (facets != null) {
                Map<String, List<SearchFacet>> searchFacets = Maps.newHashMap();
                for (String key : facets.keySet()) {
                    RZFacet f = facets.get(key);
                    List<SearchFacet> fs = searchFacets.get(key);
                    if (fs == null) {
                        fs = Lists.newArrayList();
                        searchFacets.put(key, fs);
                    }
                    List<RZEntry> facetEntries = f.getEntries();
                    for (RZEntry facetEntry : facetEntries) {
                        SearchFacet searchFacet = new SearchFacet();
                        searchFacet.setKey(facetEntry.getName());
                        searchFacet.setName(facetEntry.getName());
                        searchFacet.setCount(f.getEntries().size());
                        fs.add(searchFacet);
                    }
                }
                resp.setFacets(searchFacets);
            }
            return resp;
        } catch (Exception e) {
            logger.error("Error while searching keywords " + jsonString, e);
            return null;
        }
    }

}
