package com.rick.scaffold.web.api.product;

import com.rick.scaffold.soa.search.service.GoodsSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonNode;
import com.rick.scaffold.exception.APIException;
import com.rick.scaffold.soa.search.SearchKeywords;
import com.rick.scaffold.web.api.generic.BaseAPI;

@Controller("productApiV1")
@RequestMapping(value="/api/v1/product",produces={"application/json;charset=UTF-8"})
public class ProductAPI extends BaseAPI{
	
	@Autowired
	private GoodsSearch gs;

	@RequestMapping(value = "/typeAhead", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> productTypeAhead(@RequestBody JsonNode node) throws APIException {
		String keyword = node.path("keyword").asText();
		String index = "scaffold";
		String type = "goods";
        SearchKeywords sk = gs.searchAutoComplete(index, type, keyword, 10);
		System.out.println(sk.getKeywords().size());
		return responseSuccess(sk.getKeywords());
	}
}
