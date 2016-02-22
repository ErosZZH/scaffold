package com.rick.scaffold.search.services.delegate;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequestBuilder;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.admin.indices.exists.types.TypesExistsResponse;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.facet.Facet;
import org.elasticsearch.search.facet.Facets;
import org.elasticsearch.search.facet.datehistogram.DateHistogramFacet;
import org.elasticsearch.search.facet.geodistance.GeoDistanceFacet;
import org.elasticsearch.search.facet.histogram.HistogramFacet;
import org.elasticsearch.search.facet.range.RangeFacet;
import org.elasticsearch.search.facet.terms.TermsFacet;
import org.elasticsearch.search.facet.terms.TermsFacet.Entry;

import com.rick.scaffold.search.services.RZEntry;
import com.rick.scaffold.search.services.RZFacet;
import com.rick.scaffold.search.services.RZGetResponse;
import com.rick.scaffold.search.services.RZIndexKeywordRequest;
import com.rick.scaffold.search.services.RZSearchHit;
import com.rick.scaffold.search.services.RZSearchRequest;
import com.rick.scaffold.search.services.RZSearchResponse;
import com.rick.scaffold.search.services.field.RZBooleanField;
import com.rick.scaffold.search.services.field.RZDateField;
import com.rick.scaffold.search.services.field.RZDoubleField;
import com.rick.scaffold.search.services.field.RZField;
import com.rick.scaffold.search.services.field.RZIntegerField;
import com.rick.scaffold.search.services.field.RZListField;
import com.rick.scaffold.search.services.field.RZLongField;
import com.rick.scaffold.search.services.field.RZStringField;
import com.rick.scaffold.search.utils.SearchClient;

public class SearchDelegateImpl implements SearchDelegate {
	
	private static Logger log = Logger.getLogger(SearchDelegateImpl.class);

	private SearchClient searchClient = null;

	public SearchClient getSearchClient() {
		return searchClient;
	}

	public void setSearchClient(SearchClient searchClient) {
		this.searchClient = searchClient;
	}

	@Override
	public boolean indexExist(String index) throws Exception {
		Client client = searchClient.getClient();
		IndicesExistsRequestBuilder indiceRequestBuilder = client.admin()
				.indices().prepareExists(index);
		IndicesExistsResponse indiceResponse = indiceRequestBuilder.execute()
				.actionGet();
		return indiceResponse.isExists();

	}
	
	@Override
	public boolean typeExist(String index, String type) throws Exception {
		Client client = searchClient.getClient();
		TypesExistsResponse typeExistsResponse = client.admin().indices().prepareTypesExists(index)
				.setTypes(type).execute().actionGet();
		return typeExistsResponse.isExists();
	}

	@Override
	public void createIndice(String mapping, String settings,
			String indice, String type) throws Exception {
		Client client = searchClient.getClient();
		CreateIndexRequest indexRequest = new CreateIndexRequest(indice);
		if (mapping != null) {
			indexRequest.mapping(type, mapping);
		}
		if (settings != null) {
			indexRequest.settings(settings);
		}
		client.admin().indices().create(indexRequest).actionGet();
	}

	@Override
	public void index(String json, String index, String type, Long id) {
		Client client = searchClient.getClient();
		client.prepareIndex(index, type, id.toString()).setSource(json).execute().actionGet();
	}

	@Override
	public void delete(String index, String type, Long id)
			throws Exception {
		if (this.indexExist(index)) {
			Client client = searchClient.getClient();
			client.prepareDelete(index, type, id.toString()).execute().actionGet();
		}
	}

	@Override
	public void bulkDeleteIndex(Collection<String> ids, String type, String index)
			throws Exception {
		if (this.indexExist(index)) {
			Client client = searchClient.getClient();
			if (ids != null && ids.size() > 0) {
				BulkRequestBuilder bulkRequest = client.prepareBulk();
				for (String s : ids) {
					DeleteRequest dr = new DeleteRequest();
					dr.type(type).index(index).id(s);
					bulkRequest.add(dr);
				}
				BulkResponse bulkResponse = bulkRequest.execute().actionGet();
				if (bulkResponse.hasFailures()) {
					log.error("ES bulkDeleteIndex response has failures");
				}
			}
		}
	}

	@Override
	public void bulkIndexKeywords(Collection<RZIndexKeywordRequest> bulks,
			String index, String type) throws Exception {
		try {
			Client client = searchClient.getClient();
			BulkRequestBuilder bulkRequest = client.prepareBulk();
			for (RZIndexKeywordRequest bulk : bulks) {
				XContentBuilder b = jsonBuilder().startObject()
						.field("keyword", bulk.getKey())
						.field("_id_", bulk.getId());
				Collection<RZField> fields = bulk.getFilters();
				if (fields.size() > 0) {
					for (RZField field : fields) {
						if (field instanceof RZBooleanField) {
							Boolean val = ((RZBooleanField)field).getValue();
							b.field(field.getName(), val.booleanValue());
						} else if (field instanceof RZIntegerField) {
							Integer val = ((RZIntegerField) field).getValue();
							b.field(field.getName(), val.intValue());
						} else if (field instanceof RZLongField) {
							Long val = ((RZLongField) field).getValue();
							b.field(field.getName(), val.longValue());
						} else if (field instanceof RZListField) {
							List<Object> val = ((RZListField) field).getValue();
							b.field(field.getName(), val);
						} else if (field instanceof RZDoubleField) {
							Double val = ((RZDoubleField) field).getValue();
							b.field(field.getName(), val.doubleValue());
						} else if (field instanceof RZDateField) {
							Date val = ((RZDateField) field).getValue();
							b.field(field.getName(), val);
						} else {
							String val = ((RZStringField) field).getValue();
							b.field(field.getName(), val);
						}
					}
				}
				b.endObject();
				bulkRequest.add(client.prepareIndex(index, type)
						.setSource(b));
			}
			BulkResponse bulkResponse = bulkRequest.execute().actionGet();
			if (bulkResponse.hasFailures()) {
				log.error("ES bulkIndexKeywords response has failures");
			}
		} catch (Exception e) {
			log.error("ES bulkIndexKeywords failed.", e);
			throw e;
		}

	}

	@Override
	public RZGetResponse getObject(String index, String type, String id)
			throws Exception {
		GetResponse response = searchClient.getClient()
				.prepareGet(index, type, id).setOperationThreaded(true)
				.setFields("_source").execute().actionGet();
		RZGetResponse r = null;
		if (response != null) {
			r = new RZGetResponse(response);
		}
		return r;
	}

	@Override
	public RZSearchResponse search(RZSearchRequest request) throws Exception {
		RZSearchResponse response = new RZSearchResponse();
		try {
			SearchRequestBuilder builder = searchClient.getClient()
					.prepareSearch(request.getIndex())
					.setSource(request.getJson())
					.setTypes(request.getType())
					.setExplain(false);
			builder.setFrom(request.getStart());
			
			if (request.getSize() > -1) {
				builder.setSize(request.getSize());
			}

			SearchResponse rsp = builder.execute().actionGet();
			SearchHit[] docs = rsp.getHits().getHits();
			List<RZSearchHit> hits = new ArrayList<RZSearchHit>();
			List<String> ids = new ArrayList<String>();
			response.setCount(docs.length);
			for (SearchHit sd : docs) {
				log.debug("Found entry " + sd.sourceAsString());
				RZSearchHit hit = new RZSearchHit(sd);
				hits.add(hit);
				ids.add(sd.getId());
			}
			response.setIds(ids);
			
			Facets facets = rsp.getFacets();
			if (facets != null) {
				Map<String, RZFacet> facetsMap = new HashMap<String, RZFacet>();
				for (Facet facet : facets.facets()) {
					if (facet instanceof TermsFacet) {
						TermsFacet ff = (TermsFacet) facet;
						RZFacet f = new RZFacet();
						f.setName(ff.getName());
						List<RZEntry> entries = new ArrayList<RZEntry>();
						for (Object o : ff) {
							RZEntry entry = new RZEntry();
							Entry e = (Entry) o;
							entry.setName(e.getTerm().string());
							entry.setCount(e.getCount());
							entries.add(entry);
						}
						f.setEntries(entries);
						facetsMap.put(ff.getName(), f);
					} else if (facet instanceof RangeFacet) {
						RangeFacet ff = (RangeFacet) facet;
						RZFacet f = new RZFacet();
						f.setName(ff.getName());
						List<RZEntry> entries = new ArrayList<RZEntry>();
						for (Object o : ff) {
							RZEntry entry = new RZEntry();
							Entry e = (Entry) o;
							entry.setName(e.getTerm().string());
							entry.setCount(e.getCount());
							entries.add(entry);
						}
						f.setEntries(entries);
						facetsMap.put(ff.getName(), f);
					} else if (facet instanceof HistogramFacet) {
						HistogramFacet ff = (HistogramFacet) facet;
						RZFacet f = new RZFacet();
						f.setName(ff.getName());
						List<RZEntry> entries = new ArrayList<RZEntry>();
						for (Object o : ff) {
							RZEntry entry = new RZEntry();
							Entry e = (Entry) o;
							entry.setName(e.getTerm().string());
							entry.setCount(e.getCount());
							entries.add(entry);
						}
						f.setEntries(entries);
						facetsMap.put(ff.getName(), f);
					} else if (facet instanceof DateHistogramFacet) {
						DateHistogramFacet ff = (DateHistogramFacet) facet;
						RZFacet f = new RZFacet();
						f.setName(ff.getName());
						List<RZEntry> entries = new ArrayList<RZEntry>();
						for (Object o : ff) {
							RZEntry entry = new RZEntry();
							Entry e = (Entry) o;
							entry.setName(e.getTerm().string());
							entry.setCount(e.getCount());
							entries.add(entry);
						}
						f.setEntries(entries);
						facetsMap.put(ff.getName(), f);
					} else if (facet instanceof GeoDistanceFacet) {
						GeoDistanceFacet ff = (GeoDistanceFacet) facet;
						RZFacet f = new RZFacet();
						f.setName(ff.getName());
						List<RZEntry> entries = new ArrayList<RZEntry>();
						for (Object o : ff) {
							RZEntry entry = new RZEntry();
							Entry e = (Entry) o;
							entry.setName(e.getTerm().string());
							entry.setCount(e.getCount());
							entries.add(entry);
						}
						f.setEntries(entries);
						facetsMap.put(ff.getName(), f);
					}
				}
				response.setFacets(facetsMap);
			}
			response.setSearchHits(hits);
			return response;
		} catch (Exception e) {
			log.error("Search failed.",e);
			throw e;
		}

	}

	@Override
	public Set<String> searchAutoComplete(String index, String json, String type,
			int size) throws Exception {
		Set<String> returnList = new HashSet<String>();
		try {
			SearchRequestBuilder builder = searchClient.getClient()
					.prepareSearch(index).setTypes(type)
					.setQuery(json).setExplain(false);
			if (size > -1) {
				builder.setFrom(0).setSize(size);
			}
			SearchResponse rsp = builder.execute().actionGet();
			SearchHit[] docs = rsp.getHits().getHits();
			for (SearchHit sd : docs) {
				Map<String, Object> source = sd.getSource();
				String f = (String) source.get("keyword");
				returnList.add(f.toLowerCase());
			}
		} catch (Exception e) {
			log.error("searchAutoComplete failed.",e);
			throw e;
		}
		return returnList;
	}
}
