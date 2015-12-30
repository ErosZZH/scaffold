package com.rick.scaffold.web.api.product;

import java.util.ArrayList;
import java.util.List;

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
import com.rick.scaffold.soa.search.service.ProductSearch;
import com.rick.scaffold.web.api.generic.BaseAPI;

@Controller("productApiV1")
@RequestMapping(value="/api/v1/product",produces={"application/json;charset=UTF-8"})
public class ProductAPI extends BaseAPI{
	
	@Autowired
	private ProductSearch ps;

	@RequestMapping(value = "/typeAhead", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> productTypeAhead(@RequestBody JsonNode node) throws APIException {
		String keyword = node.path("keyword").asText();
		String index = "scaffold";
		String type = "product";
		String query = "{\"match\" : {\"keyword\" : {\"query\" :\""+keyword+"\",\"type\" : \"boolean\"}}}";
		System.out.println(query);
		SearchKeywords sk = ps.searchForKeywords(index, type, query, 20);
		System.out.println(sk.getKeywords().size());
		List<TypeAhead> list = new ArrayList<TypeAhead>();
		for(String s: sk.getKeywords()) {
			TypeAhead t = new TypeAhead();
			t.setName(s);
			list.add(t);
		}
		return responseSuccess(list);
	}
	
	private class TypeAhead {
		
		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
	}
}
