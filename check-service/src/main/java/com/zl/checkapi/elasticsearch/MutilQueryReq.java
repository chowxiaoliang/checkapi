package com.zl.checkapi.elasticsearch;

import com.zl.checkapi.util.Constants;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.stereotype.Service;

/**
 * 事件历史多条件搜索
 * @author zhouliang
 */
@Service(value = "mutilQueryReq")
public class MutilQueryReq {

    /**
     * 查询条件
     * @param boolQueryBuilder
     * @param searchType 查询类型
     * @param key 字段
     * @param value 字段值
     * @return
     */
    public BoolQueryBuilder getQueryBuilderType(BoolQueryBuilder boolQueryBuilder, String searchType, String key, String value){
        switch (searchType){
            case Constants.EventHistoryTermsType.EQ :
                boolQueryBuilder.must(QueryBuilders.termQuery(key, value));
                break;
            case Constants.EventHistoryTermsType.NE :
                boolQueryBuilder.mustNot(QueryBuilders.termQuery(key, value));
                break;
            case Constants.EventHistoryTermsType.IN :
                boolQueryBuilder.must(QueryBuilders.wildcardQuery(key, "*"+value+"*"));
                break;
            case Constants.EventHistoryTermsType.NOT_IN :
                boolQueryBuilder.mustNot(QueryBuilders.wildcardQuery(key, "*"+value+"*"));
                break;
            case Constants.EventHistoryTermsType.PREFIX :
                boolQueryBuilder.must(QueryBuilders.prefixQuery(key, value));
                break;
            case Constants.EventHistoryTermsType.POSTFIX :
                boolQueryBuilder.must(QueryBuilders.regexpQuery(key, ".*"+value));
                break;
            case Constants.EventHistoryTermsType.LT :
                boolQueryBuilder.must(QueryBuilders.rangeQuery(key).lt(value));
                break;
            case Constants.EventHistoryTermsType.LTE :
                boolQueryBuilder.must(QueryBuilders.rangeQuery(key).lte(value));
                break;
            case Constants.EventHistoryTermsType.GT :
                boolQueryBuilder.must(QueryBuilders.rangeQuery(key).gt(value));
                break;
            case Constants.EventHistoryTermsType.GTE :
                boolQueryBuilder.must(QueryBuilders.rangeQuery(key).gte(value));
                break;
            default:
                return boolQueryBuilder;
        }
        return boolQueryBuilder;
    }
}
