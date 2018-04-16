package com.zl.checkapi.elasticsearch;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.rest.RestStatus;

import java.util.UUID;

/**
 * Created by zhouliang on 2017/9/19.
 */
public class EStest {

    public static void main(String[] args) throws Exception{
        RestHighLevelClient restHighLevelClient = ElasticSearchClient.client();
        //创建索引
        String index = "test_xuchuangbin";
        IndexRequest indexRequest = new IndexRequest(index, UUID.randomUUID().toString());
        IndexResponse indexResponse = restHighLevelClient.index(indexRequest, null);
        RestStatus restStatus = indexResponse.status();
        int status = restStatus.getStatus();
        System.out.println(status);
    }
}
