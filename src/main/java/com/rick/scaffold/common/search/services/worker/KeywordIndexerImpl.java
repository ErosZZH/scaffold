package com.rick.scaffold.common.search.services.worker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.rick.scaffold.common.jsontool.JsonMapper;
import com.rick.scaffold.common.search.services.RZIndexKeywordRequest;
import com.rick.scaffold.common.search.services.delegate.SearchDelegate;
import com.rick.scaffold.common.search.services.field.RZBooleanField;
import com.rick.scaffold.common.search.services.field.RZDoubleField;
import com.rick.scaffold.common.search.services.field.RZField;
import com.rick.scaffold.common.search.services.field.RZIntegerField;
import com.rick.scaffold.common.search.services.field.RZListField;
import com.rick.scaffold.common.search.services.field.RZLongField;
import com.rick.scaffold.common.search.services.field.RZStringField;
import com.rick.scaffold.common.search.utils.CustomIndexConfiguration;
import com.rick.scaffold.common.search.utils.CustomIndexFieldConfiguration;
import com.rick.scaffold.common.search.utils.SearchClient;
import com.rick.scaffold.common.utils.StringUtils;

public class KeywordIndexerImpl implements IndexWorker {

	@Autowired
	DeleteKeywordsImpl deleteKeywordsImpl;

	@Autowired
	private SearchDelegate searchDelegate;

	private static Logger log = Logger.getLogger(KeywordIndexerImpl.class);

	private static boolean init = false;

	private List<CustomIndexConfiguration> indexConfigurations = null;

	public List<CustomIndexConfiguration> getIndexConfigurations() {
		return indexConfigurations;
	}

	public void setIndexConfigurations(
			List<CustomIndexConfiguration> indexConfigurations) {
		this.indexConfigurations = indexConfigurations;
	}

	private static Map<String, CustomIndexConfiguration> indexConfigurationsMap = null;

	private synchronized void init() {
		try {
			if (indexConfigurations != null) {
				for (CustomIndexConfiguration ic : indexConfigurations) {
					String key = ic.getOnType();
					if (indexConfigurationsMap == null) {
						indexConfigurationsMap = new HashMap<String, CustomIndexConfiguration>();
					}
					if (StringUtils.isBlank(key)) {
						log.error("Require property createOnIndexName in keyword indexer");
						continue;
					}
					indexConfigurationsMap.put(key, ic);
				}
			}
			init = true;
		} catch (Exception e) {
			log.error("create keyword index fail.", e);
		}
	}

	@Override
	public void execute(SearchClient client, String json, String index,
			String type, String id)
			throws Exception {
		if (!init) {
			init();
		}
		try {
			if (indexConfigurationsMap != null && indexConfigurationsMap.containsKey(type)) {
				CustomIndexConfiguration conf = indexConfigurationsMap.get(type);
				Map<String, Object> indexData = JsonMapper.fromJsonString(json, Map.class);
				// get fields to index
				List<CustomIndexFieldConfiguration> fields = conf.getFields();
				if (fields != null) {
					List<String> k = new ArrayList<String>();
					for (CustomIndexFieldConfiguration cifc : fields) {
						String fieldName = cifc.getFieldName();
						if (fieldName.trim().toLowerCase().equals("id")) {
							continue;
						}
						String fieldType = cifc.getFieldType();
						if (!StringUtils.isBlank(fieldType)) {
							if (fieldType.equals("List")) {
								try {
									List<String> keyWords = (List<String>) indexData.get(fieldName);
									if (keyWords != null) {
										k.addAll(keyWords);
									}
								} catch (Exception e) {// might have one instead
									String keyword = (String) indexData.get(fieldName);
									if (keyword != null) {
										k.add(keyword);
									}
								}
							} else {// String
								String keyword = (String) indexData.get(fieldName);
								if (keyword != null) {
									k.add(keyword);
								}
							}
						}// end field type
					}// end for
					if (k != null && k.size() > 0) {
						Collection<RZIndexKeywordRequest> bulks = new ArrayList<RZIndexKeywordRequest>();
						for (String value : k) {
							RZIndexKeywordRequest kr = new RZIndexKeywordRequest();
							if (StringUtils.isBlank(value)) {
								continue;
							}
							String _id = (String) indexData.get("id");
							kr.setId(_id);
							kr.setKey(value);
							if (conf.getFilters() != null && conf.getFilters().size() > 0) {
								for (CustomIndexFieldConfiguration filter : conf.getFilters()) {
									String fieldName = filter.getFieldName();
									String fieldType = filter.getFieldType();
									RZField f = null;
									if (fieldType.equals("List")) {
										List<Object> ooo = (List<Object>) indexData.get(fieldName);
										f = new RZListField();
										f.setValue(ooo);
										f.setName(fieldName);
										kr.getFilters().add(f);
									} else if (fieldType.equals("Boolean")) {
										String s = (String) indexData.get(fieldName);
										Boolean ooo = new Boolean(s);
										f = new RZBooleanField();
										f.setValue(ooo);
										f.setName(fieldName);
										kr.getFilters().add(f);
									} else if (fieldType.equals("Integer")) {
										Integer ooo = (Integer) indexData.get(fieldName);
										f = new RZIntegerField();
										f.setValue(ooo);
										f.setName(fieldName);
										kr.getFilters().add(f);
									} else if (fieldType.equals("Long")) {
										Long ooo = (Long) indexData.get(fieldName);
										f = new RZLongField();
										f.setValue(ooo);
										f.setName(fieldName);
										kr.getFilters().add(f);
									} else if (fieldType.equals("Double")) {
										Double ooo = (Double) indexData.get(fieldName);
										f = new RZDoubleField();
										f.setValue(ooo);
										f.setName(fieldName);
										kr.getFilters().add(f);
									} else if (fieldType.equals("Date")) {
										Date dt = (Date) indexData.get(fieldName);
										f = new RZDoubleField();
										f.setValue(dt);
										f.setName(fieldName);
										kr.getFilters().add(f);
									} else {
										String ooo = (String) indexData.get(fieldName);
										f = new RZStringField();
										f.setValue(ooo.toLowerCase());
										f.setName(fieldName);
										kr.getFilters().add(f);
									}
								}
							}
							bulks.add(kr);
						}
						// delete previous keywords for the same id
						deleteKeywordsImpl.deleteObject(client, index, "keyword", id);
						searchDelegate.bulkIndexKeywords(bulks, index, "keyword");
					}
				}
			}
		} catch (Exception e) {
			log.error("Cannot index keywords, maybe a timing ussue for no shards available", e);
		}
	}

	@Override
	public void init(SearchClient client) {
		if (!init) {
			init();
		}

	}

}
