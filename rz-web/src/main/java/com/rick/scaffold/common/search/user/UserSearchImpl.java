package com.rick.scaffold.common.search.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.elasticsearch.common.text.Text;
import org.elasticsearch.search.highlight.HighlightField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.rick.scaffold.common.search.SearchEntry;
import com.rick.scaffold.common.search.SearchFacet;
import com.rick.scaffold.common.search.SearchKeywords;
import com.rick.scaffold.common.search.SearchResult;
import com.rick.scaffold.core.entity.user.User;
import com.rick.scaffold.search.SearchConstants;
import com.rick.scaffold.search.services.RZEntry;
import com.rick.scaffold.search.services.RZFacet;
import com.rick.scaffold.search.services.RZSearchHit;
import com.rick.scaffold.search.services.RZSearchRequest;
import com.rick.scaffold.search.services.RZSearchResponse;
import com.rick.scaffold.search.services.RZSearchService;

public class UserSearchImpl implements UserSearch {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(UserSearchImpl.class);

	private final static String TYPE = "user";

	@Autowired
	private RZSearchService searchService;

	@Override
	public void initService() {
		searchService.initService();
	}

	@Override
	public void createIndex(User user) {

		String indice = SearchConstants.indice;

		IndexUser indexObj = new IndexUser();

		indexObj.setId(user.getId());
		indexObj.setEmail(user.getEmail());
		indexObj.setLoginName(user.getLoginName());
		indexObj.setName(user.getName());
		indexObj.setPhone(user.getPhone());

		try {
			searchService.index(indexObj, indice, TYPE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteIndex(String id) {
		String collectionName = SearchConstants.indice;
		try {
			searchService.deleteObject(collectionName, TYPE, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public SearchKeywords searchForKeywords(String collectionName,
			String jsonString, int entriesCount) {
		try {
			RZSearchResponse response = searchService.searchAutoComplete(
					collectionName, jsonString, entriesCount);
			SearchKeywords keywords = new SearchKeywords();
			keywords.setKeywords(Arrays.asList(response.getInlineSearchList()));
			return keywords;
		} catch (Exception e) {
			LOGGER.error("Error while searching keywords " + jsonString, e);
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public SearchResult search(String jsonString, int entriesCount,
			int startIndex) {
		try {
			String collectionName = SearchConstants.indice;
			RZSearchRequest request = new RZSearchRequest();
			request.setIndex(collectionName);
			request.setSize(entriesCount);
			request.setStart(startIndex);
			request.setType("user");
			request.setJson(jsonString);

			RZSearchResponse response = searchService.search(request);
			SearchResult resp = new SearchResult();
			resp.setTotalCount(response.getCount());
			List<SearchEntry> entries = new ArrayList<SearchEntry>();
			Collection<RZSearchHit> hits = response.getSearchHits();
			for (RZSearchHit hit : hits) {
				SearchEntry entry = new SearchEntry();
				Map<String, Object> metaEntries = hit.getMetaEntries();
				IndexUser indexUser = new IndexUser();
				Map sourceEntries = (Map) metaEntries.get("source");
				indexUser.setEmail((String) sourceEntries.get("email"));
				indexUser.setId((String) sourceEntries.get("id"));
				indexUser.setLoginName((String) sourceEntries.get("loginName"));
				indexUser.setName((String) sourceEntries.get("name"));
				indexUser.setPhone((String) sourceEntries.get("phone"));
				entry.setIndexUser(indexUser);
				entries.add(entry);

				Map<String, HighlightField> fields = hit.getHighlightFields();
				if (fields != null) {
					List<String> highlights = new ArrayList<String>();
					for (HighlightField field : fields.values()) {
						Text[] text = field.getFragments();
						// text[0]
						String f = field.getName();
						highlights.add(f);
					}
					entry.setHighlights(highlights);
				}
			}
			resp.setEntries(entries);

			Map<String, RZFacet> facets = response.getFacets();
			if (facets != null) {
				Map<String, List<SearchFacet>> searchFacets = new HashMap<String, List<SearchFacet>>();
				for (String key : facets.keySet()) {
					RZFacet f = facets.get(key);
					List<SearchFacet> fs = searchFacets.get(key);
					if (fs == null) {
						fs = new ArrayList<SearchFacet>();
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
			LOGGER.error("Error while searching keywords " + jsonString, e);
			e.printStackTrace();
			return null;
		}
	}
}
