package com.zl.checkapi.elasticsearch;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zl.checkapi.util.Constants;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.zl.checkapi.elasticsearch.ElasticSearchClient.client;

@Component
public class ElasticHelper {

    private static Logger log = LoggerFactory.getLogger(ElasticHelper.class);

    @Autowired
    private MutilQueryReq mutilQueryReq;

    public static final float MIN_SCORE = 0.1f;
    public static final int DEFAULT_SIZE = 100;
    public static final int SCROLL_SIZE = 1000;
    public static final int SCROLL_BEGIN_SIZE = 10000;//(from+size)>10000，则需要使用滚动查询

//	/**
//	 * 插入数据 ，id自定义
//	 * @param index
//	 * @param type
//	 * @param id
//	 * @param params
//	 * @param refresh 是否立即刷新（立即刷新意味着写入后马上可以查询，默认自动1秒后自动刷新，如无必要，无数设置为立即刷新）
//	 * @throws IOException
//	 */
//	public IndexResponse input(String index, String type,String id,Map<String,Object> params,boolean refresh) throws Exception{
//
//		return client().prepareIndex().setIndex(index).setType(type).setId(id)
//			.setSource(_buildParamSource(params))
//			.setRefresh(refresh)
//			.get();
//	}
//	private XContentBuilder _buildParamSource(Map<String,Object> params) throws IOException {
//		XContentBuilder build = jsonBuilder()
//				.startObject();
//		for (String field : params.keySet()) {
//			build.field(field, params.get(field));
//		}
//		build.endObject();
//		return build;
//	}
//	/**
//	 * 插入数据 ，id自动生成
//	 * @param index
//	 * @param type
//	 * @param params
//	 * @param refresh 是否立即刷新（立即刷新意味着写入后马上可以查询，默认自动1秒后自动刷新，如无必要，无数设置为立即刷新）
//	 * @throws IOException
//	 */
//	public IndexResponse input(String index, String type,Map<String,Object> params,boolean refresh) throws Exception{
//
//		return client().prepareIndex().setIndex(index).setType(type)
//			.setSource(_buildParamSource(params))
//			.setRefresh(refresh)
//			.get();
//	}
//	/**
//	 * 根据ID 删除数据
//	 * refresh true立即刷新文档
//	 * @return
//	 */
//	public DeleteResponse delete(String index, String type,String id,boolean refresh){
//
//		return client().prepareDelete().setIndex(index).setType(type).setId(id)
//				.setRefresh(refresh)
//				.get();
//	}
//
//	/**
//	 * 滚动模式查询，只用于查询明细，当from+size大于10000时，使用滚动查询查询明细
//	 */
//	public Set<String> scrollQueryForField(String[] indices, String type,
//			String dateField, long dateStart, long dateEnd,
//			Map<String, Object> termParams, Map<String,Boolean> exists, String fieldName,int from, int size) {
//
//		long st = System.currentTimeMillis();
//
//		log.info("scrollQuery,滚动查询了..."+Arrays.toString(indices));
//
//		Set<String> result = new HashSet<>();
//
//		BoolQueryBuilder multiQuery = QueryBuilders.boolQuery();
//		for (String termField : termParams.keySet()) {
//			Object value = termParams.get(termField);
//			// 参数的值为空时不能查询
//			if (StringUtils.isNotEmpty(termField) && value != null) {
//				multiQuery.filter(QueryBuilders.termQuery(termField,
//						termParams.get(termField)));
//			}
//		}
//		for (String existField : exists.keySet()) {
//			Boolean exist = exists.get(existField);
//			// 参数的值为空时不能查询
//			if (StringUtils.isNotEmpty(existField) && exist != null) {
//				if(exist){
//					multiQuery.must(QueryBuilders.existsQuery(existField));
//				}else{
//					multiQuery.mustNot(QueryBuilders.existsQuery(existField));
//				}
//			}
//		}
//		if(dateField!=null){
//			multiQuery.filter(QueryBuilders.rangeQuery(dateField).from(dateStart)
//					.to(dateEnd));
//		}
//
//		log.debug("scrollQuery=>" +multiQuery.toString());
//
//		SearchRequestBuilder searchBuilder = client().prepareSearch(indices);
//		// type可以为空，空值不要设置
//		if (StringUtils.isNotEmpty(type)) {
//			searchBuilder.setTypes(type);
//		}
//
//		int scrollSize = 1000;//每次滚动的条数
//		TimeValue expireTime = TimeValue.timeValueSeconds(10);// 在这个时间之后，将滚动的数据内容清理掉
//
//		searchBuilder.setQuery(multiQuery)
//				.setIndicesOptions(IndicesOptions.fromOptions(true, true, true, true))// 忽略不存在的index
//				.setScroll(expireTime)// 滚动记录清除时间
//				.addField(fieldName)
//				.setSize(scrollSize);
//
//		SearchResponse scrollResp = searchBuilder.get();
//
//		// System.out.println(query.toString());
//
//		long total = scrollResp.getHits().getTotalHits();// 查询结果总数量
//
//		if (from >= total) {
//			log.warn(String.format("from[%s] gt total[%s] ", from, total));
//			return result;
//		}
//
//		int hitSize = 0;// 每次查询命中数量
//
//		int fromIndex = 0; //每一次查询的起始位置
//		int lastIndex = from + size;//结束查询位置
//
//		do {
//				hitSize = scrollResp.getHits().getHits().length;
//
//				//取出所需位置的数据
//				if ((fromIndex + hitSize) >= from && fromIndex <= lastIndex) {
//					int startIndex = Math.max(fromIndex, from);
//					int endIndex = Math.min((fromIndex + hitSize), (from + size));
//
//					int startHitIndex = 0;//本次查询hits中取数的开始下标
//					if (fromIndex < from) {
//						startHitIndex = from - fromIndex;
//					} else {
//						startHitIndex = 0;
//					}
//					int subLength = endIndex - startIndex;//本次查询hits中共取数条数
//
//					for (int i = startHitIndex; i < startHitIndex + subLength; i++) {
//						SearchHitField hit = scrollResp.getHits().getAt(i).field(fieldName);
//						if(hit!=null){
//							result.add(hit.getValue());
//						}
//					}
//
//				}
//
//				fromIndex += hitSize;
//
//				if (fromIndex > lastIndex) {
//					//System.out.println("到这里就可以了，停下来吧");
//					break;
//				}
//				if (hitSize < scrollSize) {
//					//System.err.println("已经滚动到最后了");
//					break;
//				}
//
//				scrollResp = client().prepareSearchScroll(scrollResp.getScrollId())
//						.setScroll(expireTime).get();
//
//		} while (scrollResp.getHits().getHits().length > 0);
//
//		log.info(String.format("scrollQueryField,本次滚动查询用时[%s]ms,from[%s],size[%s],全记录共[%s],共查询到结果[%s]",(System.currentTimeMillis()-st),from,size,total,result.size()));
//
//		return result;
//
//	}

//    /**
//     * 滚动模式查询，只用于查询明细，当from+size大于10000时，使用滚动查询查询明细
//     */
//    public ElasticSearchResult scrollQuery(EventSearchConsts.EventItem item,String partnerId, String[] indices, String[] types,
//                                           String dateField, long dateStart, long dateEnd,
//                                           Map<String, Object> termParams, Map<String, Boolean> exists, SortType sortType,
//                                           String sortField, int from, int size) {
//
//        long st = System.currentTimeMillis();
//
//        log.info("scrollQuery,尼玛这谁啊,居然需要滚动查询了..." + Arrays.toString(indices));
//
//        ElasticSearchResult result = new ElasticSearchResult();
//
//        BoolQueryBuilder multiQuery = QueryBuilders.boolQuery();
//        for (String termField : termParams.keySet()) {
//            Object value = termParams.get(termField);
//            // 参数的值为空时不能查询
//            if (StringUtils.isNotEmpty(termField) && value != null) {
//                multiQuery.filter(QueryBuilders.termQuery(termField,
//                        termParams.get(termField)));
//            }
//        }
//        for (String existField : exists.keySet()) {
//            Boolean exist = exists.get(existField);
//            // 参数的值为空时不能查询
//            if (StringUtils.isNotEmpty(existField) && exist != null) {
//                if (exist) {
//                    multiQuery.must(QueryBuilders.existsQuery(existField));
//                } else {
//                    multiQuery.mustNot(QueryBuilders.existsQuery(existField));
//                }
//            }
//        }
//        if (dateField != null) {
//            multiQuery.filter(QueryBuilders.rangeQuery(dateField).from(dateStart)
//                    .to(dateEnd));
//        }
//
//        log.debug("scrollQuery=>" + multiQuery.toString());
//
//        SearchRequestBuilder searchBuilder = client().prepareSearch(indices);
//
//        filterPartnerIdAndApp(multiQuery, item, partnerId,types);
//
//        // 排序可以为空
//        if (sortField != null && sortField.length() > 0) {
//            switch (sortType) {
//                case ASC:
//                    searchBuilder.addSort(sortField, SortOrder.ASC);
//                    break;
//                case DESC:
//                    searchBuilder.addSort(sortField, SortOrder.DESC);
//                    break;
//                case NO:
//                    break;
//            }
//        }
//
//        int scrollSize = SCROLL_SIZE;//每次滚动的条数
//        TimeValue expireTime = TimeValue.timeValueSeconds(10);// 在这个时间之后，将滚动的数据内容清理掉
//
//        searchBuilder.setQuery(multiQuery)
//                .setIndicesOptions(IndicesOptions.fromOptions(true, true, true, true))// 忽略不存在的index
//                .setScroll(expireTime)// 滚动记录清除时间
//                .setSize(scrollSize);
//
//        SearchResponse scrollResp = searchBuilder.get();
//
//        // System.out.println(query.toString());
//
//        long total = scrollResp.getHits().getTotalHits();// 查询结果总数量
//
//        log.debug("查询总条数：" + scrollResp.getHits().getTotalHits());
//
//        result.setTotalHits(total);
//
//        if (from >= total) {
//            log.warn(String.format("from[%s] gt total[%s] ", from, total));
//            return result;
//        }
//
//        int hitSize = 0;// 每次查询命中数量
//
//        int fromIndex = 0; //每一次查询的起始位置
//        int lastIndex = from + size;//结束查询位置
//
//        do {
//            hitSize = scrollResp.getHits().getHits().length;
//
//            //取出所需位置的数据
//            if ((fromIndex + hitSize) >= from && fromIndex <= lastIndex) {
//                int startIndex = Math.max(fromIndex, from);
//                int endIndex = Math.min((fromIndex + hitSize), (from + size));
//
//                int startHitIndex = 0;//本次查询hits中取数的开始下标
//                if (fromIndex < from) {
//                    startHitIndex = from - fromIndex;
//                } else {
//                    startHitIndex = 0;
//                }
//                int subLength = endIndex - startIndex;//本次查询hits中共取数条数
//
//                for (int i = startHitIndex; i < startHitIndex + subLength; i++) {
//                    result.add(scrollResp.getHits().getAt(i));
//                }
//
//                //System.out.println("这是所需要的" + fromIndex);
//            }
//
//            fromIndex += hitSize;
//
//            if (fromIndex > lastIndex) {
//                //System.out.println("到这里就可以了，停下来吧");
//                break;
//            }
//            if (hitSize < scrollSize) {
//                //System.err.println("已经滚动到最后了");
//                break;
//            }
//
//            scrollResp = client().prepareSearchScroll(scrollResp.getScrollId())
//                    .setScroll(expireTime).get();
//
//        } while (scrollResp.getHits().getHits().length > 0);
//
//        log.info(String.format("scrollQuery,本次滚动查询用时[%s]ms,from[%s],size[%s],全记录共[%s],共查询到结果[%s]", (System.currentTimeMillis() - st), from, size, total, result.getHits().size()));
//
//        return result;
//
//    }
    /**
     * 兼容击中规则或者策略的周、天的index数据结构
     * @param multiQuery
     * @param item
     * @param apps
     */
    private void filterPartnerIdAndApp(BoolQueryBuilder multiQuery, EventSearchConsts.EventItem item, String partnerId, String[] apps){
    	if(item != EventSearchConsts.EventItem.event && partnerId != null){
    		multiQuery.filter(QueryBuilders.termQuery("partnerId", partnerId));
    	}

    	if (apps != null && apps.length > 0) {
     		if(apps.length == 1){
     			multiQuery.filter(QueryBuilders.termQuery("appId", apps[0]));
     		}else{
     			multiQuery.filter(QueryBuilders.termsQuery("appId", apps));
     		}
         }
    }
    private void filterPartnerIdAndAppForHisEvent(BoolQueryBuilder multiQuery, String[] apps){

    	filterPartnerIdAndApp( multiQuery, EventSearchConsts.EventItem.event, null, apps);
    }

    /**
     * 多index查询
     * 其中term是一个固定的字段，但有多个值
     *
     * @param indexes
     * @return
     */
    public SearchResponse termMultiQuery(String[] indexes, Map<String, List<Object>> terms, Map<String, Object> termParams, int size, EventSearchConsts.SortType sortType, String sortField) {
        if (terms == null || terms.size() < 0)
            return null;
        String filed = "";
        List<Object> fieldValues = null;
        BoolQueryBuilder multiQuery = QueryBuilders.boolQuery();
        JSONArray resultArray = null;
        if (termParams != null) {
            if(termParams.get(Constants.EventHistoryParams.EXTRA_CONDITIONS)!=null){
                resultArray = (JSONArray)termParams.get(Constants.EventHistoryParams.EXTRA_CONDITIONS);
                termParams.remove(Constants.EventHistoryParams.EXTRA_CONDITIONS);
            }

            for (String termField : termParams.keySet()) {
                Object value = termParams.get(termField);
                //参数的值为空时不能查询
                if (StringUtils.isNotEmpty(termField) && value != null) {
                    multiQuery.filter(QueryBuilders.termQuery(termField, termParams.get(termField)));
                }
            }

        }
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        for (Map.Entry<String, List<Object>> entry : terms.entrySet()) {
            filed = entry.getKey();
            fieldValues = entry.getValue();
        }
        if (fieldValues == null || fieldValues.size() <= 0)
            return null;
        //排序可以为空
        if (sortField != null) {
            switch (sortType) {
                case ASC:
                    searchSourceBuilder.sort(sortField, SortOrder.ASC);
                    break;
                case DESC:
                    searchSourceBuilder.sort(sortField, SortOrder.DESC);
                    break;
                case NO:
                    break;
            }
        }
        multiQuery.filter(QueryBuilders.termsQuery(filed, fieldValues));

        //高级搜索条件放在最后
        if(resultArray!=null && resultArray.size()>0){
            for(int i=0;i<resultArray.size();i++){
                JSONObject resultObject = resultArray.getJSONObject(i);
                String searchType = resultObject.getString(Constants.EventHistoryParams.CONDITIONS);
                String fieldName = resultObject.getString(Constants.EventHistoryParams.FIELD_NAME);
                if("ruleName".equals(fieldName)){
                    continue;
                }
                String fieldValue = resultObject.getString(Constants.EventHistoryParams.FIELD_VALUE);
                if(StringUtils.isNotEmpty(searchType) && StringUtils.isNotEmpty(fieldName) && StringUtils.isNotEmpty(fieldValue)){
                    mutilQueryReq.getQueryBuilderType(multiQuery, searchType, fieldName, fieldValue);
                }
            }
        }
        log.debug("termMultiQuery=>" + multiQuery.toString());
        try {
            SearchRequest request = new SearchRequest().indices(indexes)
                    .indicesOptions(IndicesOptions.fromOptions(true, true, true, true))
                    .source(searchSourceBuilder.query(multiQuery).size(size));
            return client().search(request);
        } catch (IOException e) {
            log.error("termMultiQuery error ! ", e);
            return null;
        }
    }

    /**
     * 多条件聚合查询
     *
     * @param item
     * @param partnerId
     * @param indices
     * @param types
     * @param dateField
     * @param dateStart
     * @param dateEnd
     * @param termParams
     * @param exists
     * @param aggFilds
     * @return
     */
    public SearchResponse termAndDateRangeQueryWithAgg(EventSearchConsts.EventItem item, String partnerId, String[] indices, String[] types, String dateField, long dateStart, long dateEnd, Map<String, Object> termParams, Map<String, Boolean> exists, Set<String> aggFilds) {

        BoolQueryBuilder multiQuery = QueryBuilders.boolQuery();

        //找出高级搜索的条件
        JSONArray resultArray = null;
        if(termParams.get(Constants.EventHistoryParams.EXTRA_CONDITIONS)!=null){
            resultArray = (JSONArray)termParams.get(Constants.EventHistoryParams.EXTRA_CONDITIONS);
            termParams.remove(Constants.EventHistoryParams.EXTRA_CONDITIONS);
        }
        for (String termField : termParams.keySet()) {
            Object value = termParams.get(termField);
            //参数的值为空时不能查询
            if (StringUtils.isNotEmpty(termField) && value != null) {
                multiQuery.filter(QueryBuilders.termQuery(termField, termParams.get(termField)));
            }
        }

        for (String existField : exists.keySet()) {
            Boolean exist = exists.get(existField);
            // 参数的值为空时不能查询
            if (StringUtils.isNotEmpty(existField) && exist != null) {
                if (exist) {
                    multiQuery.must(QueryBuilders.existsQuery(existField));
                } else {
                    multiQuery.mustNot(QueryBuilders.existsQuery(existField));
                }
            }
        }
        //时间区间过滤
        if (dateField != null) {
            multiQuery.filter(QueryBuilders.rangeQuery(dateField).from(dateStart).to(dateEnd));
        }
        //高级搜索条件放在最后(过滤掉非击中规则字段)
        if(resultArray!=null && resultArray.size()>0){
            for(int i=0;i<resultArray.size();i++){
                JSONObject resultObject = resultArray.getJSONObject(i);
                String searchType = resultObject.getString(Constants.EventHistoryParams.CONDITIONS);
                String fieldName = resultObject.getString(Constants.EventHistoryParams.FIELD_NAME);
                if(!"ruleName".equals(fieldName)){
                    continue;
                }
                String fieldValue = resultObject.getString(Constants.EventHistoryParams.FIELD_VALUE);
                if(StringUtils.isNotEmpty(searchType) && StringUtils.isNotEmpty(fieldName) && StringUtils.isNotEmpty(fieldValue)){
                    mutilQueryReq.getQueryBuilderType(multiQuery, searchType, fieldName, fieldValue);
                }
            }
        }
        SearchRequest searchRequest = new SearchRequest().indices(indices).indicesOptions(IndicesOptions.fromOptions(true, true, true, true));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //type可以为空，空值不要设置
        filterPartnerIdAndApp(multiQuery, item, partnerId, types);

        if (aggFilds != null && aggFilds.size() > 0) {
            for (String aggField : aggFilds) {
                TermsAggregationBuilder builder = AggregationBuilders.terms(aggField).field(aggField).size(Integer.MAX_VALUE);
                searchSourceBuilder.aggregation(builder);
            }
        }
        searchSourceBuilder.query(multiQuery);
        searchRequest.source(searchSourceBuilder);
        log.debug("termAndDateRangeQueryWithAgg=>" + multiQuery.toString());

        try {
            return client().search(searchRequest);
        } catch (IOException e) {
            log.error("termAndDateRangeQueryWithAgg error !", e);
            return null;
        }
    }

    /**
     * 多index ,多term条件查询，如查询明细时，from+size大于10000，请使用滚动查询方法
     *
     * @param item
     * @param indices    如果index中有不存在的，忽略之
     * @param types
     * @param dateField
     * @param dateStart
     * @param dateEnd
     * @param termParams 查询过滤条件
     * @param exists
     * @param from       查询起始位置，从0开始
     * @param size       查询数据个数
     * @return
     */
    public SearchResponse termAndDateRangeQuery(EventSearchConsts.EventItem item, String partnerId, String[] indices, String[] types, String dateField, long dateStart, long dateEnd, Map<String, Object> termParams, Map<String, Boolean> exists, EventSearchConsts.SortType sortType, String sortField, int from, int size) {

        BoolQueryBuilder multiQuery = QueryBuilders.boolQuery();

        JSONArray resultArray = null;
        if(termParams.get(Constants.EventHistoryParams.EXTRA_CONDITIONS)!=null){
            resultArray = (JSONArray)termParams.get(Constants.EventHistoryParams.EXTRA_CONDITIONS);
            termParams.remove(Constants.EventHistoryParams.EXTRA_CONDITIONS);
        }
        for (String termField : termParams.keySet()) {
            Object value = termParams.get(termField);
            //参数的值为空时不能查询
            if (StringUtils.isNotEmpty(termField) && value != null) {
                multiQuery.filter(QueryBuilders.termQuery(termField, termParams.get(termField)));
            }
        }
        for (String existField : exists.keySet()) {
            Boolean exist = exists.get(existField);
            // 参数的值为空时不能查询
            if (StringUtils.isNotEmpty(existField) && exist != null) {
                if (exist) {
                    multiQuery.must(QueryBuilders.existsQuery(existField));
                } else {
                    multiQuery.mustNot(QueryBuilders.existsQuery(existField));
                }
            }
        }
        //时间区间过滤
        if (dateField != null) {
            multiQuery.filter(QueryBuilders.rangeQuery(dateField).from(dateStart).to(dateEnd));
        }

        //高级搜索条件放在最后
        if(resultArray!=null && resultArray.size()>0){
            for(int i=0;i<resultArray.size();i++){
                JSONObject resultObject = resultArray.getJSONObject(i);
                String searchType = resultObject.getString(Constants.EventHistoryParams.CONDITIONS);
                String fieldName = resultObject.getString(Constants.EventHistoryParams.FIELD_NAME);
                String fieldValue = resultObject.getString(Constants.EventHistoryParams.FIELD_VALUE);
                if(StringUtils.isNotEmpty(searchType) && StringUtils.isNotEmpty(fieldName) && StringUtils.isNotEmpty(fieldValue)){
                    mutilQueryReq.getQueryBuilderType(multiQuery, searchType, fieldName, fieldValue);
                }
            }
        }

        SearchRequest searchRequest = new SearchRequest().indices(indices).indicesOptions(IndicesOptions.fromOptions(true, true, true, true));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder().from(from).size(size);

        //type可以为空，空值不要设置
        filterPartnerIdAndApp(multiQuery, item, partnerId,types);
        //排序可以为空
        if (sortField != null) {
            switch (sortType) {
                case ASC:
                    searchSourceBuilder.sort(sortField, SortOrder.ASC);
                    break;
                case DESC:
                    searchSourceBuilder.sort(sortField, SortOrder.DESC);
                    break;
                case NO:
                    break;
            }
        }
        searchSourceBuilder.query(multiQuery);
        searchRequest.source(searchSourceBuilder);
        log.info("termAndDateRangeQuery=>" + multiQuery.toString());

            try {
            return client().search(searchRequest);
        } catch (IOException e) {
            log.error("termAndDateRangeQuery error !", e);
            return null;
        }
    }


    /**
     * 聚合查询，无明细
     *
     * @param indices，如果index中有不存在的，忽略之
     * @param types                     可以为空，空为查询所有type
     * @param termParams                查询过滤条件
     * @param exists                    字段值否是为null
     * @return
     */
    public SearchResponse aggregateQuery(String[] indices, String[] types,
                                         String dateField, long dateStart, long dateEnd,
                                         Map<String, Object> termParams, Map<String, Boolean> exists, AbstractAggregationBuilder aggregation) {

        BoolQueryBuilder multiQuery = QueryBuilders.boolQuery();

        for (String termField : termParams.keySet()) {
            Object value = termParams.get(termField);
            // 参数的值为空时不能查询
            if (StringUtils.isNotEmpty(termField) && value != null) {
                multiQuery.filter(QueryBuilders.termQuery(termField,
                        termParams.get(termField)));
            }
        }
        for (String existField : exists.keySet()) {
            Boolean exist = exists.get(existField);
            // 参数的值为空时不能查询
            if (StringUtils.isNotEmpty(existField) && exist != null) {
                if (exist) {
                    multiQuery.must(QueryBuilders.existsQuery(existField));
                } else {
                    multiQuery.mustNot(QueryBuilders.existsQuery(existField));
                }
            }
        }

        multiQuery.filter(QueryBuilders.rangeQuery(dateField).from(dateStart).to(dateEnd));

        SearchRequest searchRequest = new SearchRequest().indices(indices).indicesOptions(IndicesOptions.fromOptions(true, true, true, true));
//        SearchRequestBuilder searchBuilder = client().prepareSearch(indices);

        filterPartnerIdAndAppForHisEvent(multiQuery, types);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder().query(multiQuery).aggregation(aggregation).size(0);

        log.debug("aggregateQuery=>" + multiQuery.toString());

        searchRequest.source(searchSourceBuilder);

        try {
            return client().search(searchRequest);
        } catch (IOException e) {
            log.error("aggregateQuery error ! ", e);
            return null;
        }
    }

}