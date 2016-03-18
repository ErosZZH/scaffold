package com.rick.scaffold.goods;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.rick.scaffold.base.BaseTest;
import com.rick.scaffold.common.jsontool.JsonMapper;
import com.rick.scaffold.common.utils.FileUtils;
import com.rick.scaffold.search.services.RZGetResponse;
import com.rick.scaffold.search.services.RZSearchRequest;
import com.rick.scaffold.search.services.RZSearchResponse;
import com.rick.scaffold.search.services.delegate.SearchDelegate;
import com.rick.scaffold.search.services.worker.KeywordIndexerImpl;
import com.rick.scaffold.search.services.worker.ObjectIndexerImpl;
import com.rick.scaffold.search.utils.SearchClient;
import com.rick.scaffold.soa.search.SearchEntry;
import com.rick.scaffold.soa.search.SearchKeywords;
import com.rick.scaffold.soa.search.SearchResult;
import com.rick.scaffold.soa.search.model.IndexGoods;
import com.rick.scaffold.soa.search.service.GoodsSearch;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * Created by user on 16/3/16.
 */
public class TestGoods extends BaseTest {

    @Autowired
    private ObjectIndexerImpl oii;

    @Autowired
    private KeywordIndexerImpl kii;

    @Autowired
    private SearchClient sc;

    @Autowired
    private SearchDelegate sd;

    @Autowired
    private GoodsSearch gs;

    @Test
    public void testImportGoods () throws Exception {
        gs.importFromDB("select id as _id, name, sn, shop_price, shop_id, cate_id from cs_goods");
    }

    @Test
    public void testKeywordImportGoods () throws Exception {
        gs.importKeywordFromDB("select id as dbid, name as keyword from cs_goods union select id as dbid, sn as keyword from cs_goods");
    }

    @Test
    public void testCreateType() throws Exception {
        oii.init((SearchClient) sc);
        System.out.println(sd.typeExist("scaffold", "goods"));

    }

    @Test
    public void testTypeExist() throws Exception {
        System.out.println(sd.typeExist("scaffold", "goods"));

    }

    @Test
    public void testIndex() throws Exception {
        IndexGoods ig = new IndexGoods();
        ig.setCate_id(1L);
        ig.setName("指南针");
        ig.setShop_id(1L);
        ig.setShop_price(20f);
        ig.setSn("123456");
        sd.index(JsonMapper.toJsonString(ig), "scaffold", "goods", "100000");
    }

    @Test
    public void testDelete() throws Exception {
        sd.delete("scaffold", "goods", "100000");
    }

    @Test
    public void testGetObject() throws Exception {
        RZGetResponse res = sd.getObject("scaffold", "goods", "100000");
        System.out.println(res.getResponseAsString());
    }

    @Test
    public void testBulkDelete() throws Exception {
        List<String> ids = Lists.newArrayList();
        ids.add("100000");
        sd.bulkDeleteIndex("scaffold", "goods", ids);
    }

    @Test
    public void testSearch() throws Exception {
//        String json = QueryBuilders.termQuery("name", "康师傅").toString();
        String json = FileUtils.readFileAsString("complex_search.json");
        System.out.println(json);
//        XContentBuilder builder = jsonBuilder()
//                .startObject()
//                .field("name", "康师傅")
//                .endObject();
//        System.out.println(builder.string());
        RZSearchRequest request = new RZSearchRequest();
        request.setIndex("scaffold");
        request.setSize(-1);
        request.setStart(0);
        request.setType("goods");
        request.setJson(json);
        RZSearchResponse res = sd.search(request);
        System.out.println(res.getCount());
        for(String id: res.getIds()) {
            System.out.println(id);
        }
        System.out.println("Facets: "+JsonMapper.toJsonString(res.getFacets()));
    }

    @Test
    public void testSearch1() throws Exception {
        String json = FileUtils.readFileAsString("complex_search.json");
        SearchResult sr = gs.search(json, 0, -1);
        for(SearchEntry se: sr.getEntries()) {
            IndexGoods goods = (IndexGoods)se.getIndexObject();
            System.out.println(goods.getName());
        }
    }

    @Test
    public void testAddKeywordIndex() throws Exception {
        IndexGoods ig = new IndexGoods();
        ig.setCate_id(1L);
        ig.setName("指南针1");
        ig.setShop_id(1L);
        ig.setShop_price(20f);
        ig.setSn("890456");
        kii.execute(sc, JsonMapper.toJsonString(ig), "scaffold", "goods", 2L);
    }

    @Test
    public void testAutoComplete() throws Exception {
        String json = QueryBuilders.termQuery("keyword", "康师傅").toString();
        Set<String> res = sd.searchAutoComplete("scaffold", json, "keyword_goods", 10);
        for(String s: res) {
            System.out.println(s);
        }
    }

    @Test
    public void testAutoComplete1() throws Exception {
        String index = "scaffold";
        String type = "keyword_goods";
        SearchKeywords sk = gs.searchAutoComplete(index, type, "康师傅", 10);
        System.out.println(sk.getKeywords().size());
        for(String s: sk.getKeywords()) {
            System.out.print(s + ",");
        }
    }
}
