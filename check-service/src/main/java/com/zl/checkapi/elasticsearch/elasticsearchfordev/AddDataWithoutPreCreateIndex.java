package com.zl.checkapi.elasticsearch.elasticsearchfordev;

import com.alibaba.fastjson.JSONObject;
import com.zl.checkapi.elasticsearch.ElasticSearchClient;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

public class AddDataWithoutPreCreateIndex {
    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.xml");
        OriginalBean originalBean = new OriginalBean();
        //转化成map结构
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(originalBean);
        IndexRequest indexRequest1 = new IndexRequest("xuchuangbintest", "rule1", "1");

        XContentBuilder xContentBuilder = getSource(jsonObject);
        indexRequest1.source(xContentBuilder);
        IndexResponse indexResponse = ElasticSearchClient.client().index(indexRequest1);
        System.out.println(indexResponse);
    }
    private static XContentBuilder getSource(Map<String, Object> map) throws Exception{
        XContentBuilder xContentBuilder = jsonBuilder().startObject();
        for(Map.Entry<String, Object> entry : map.entrySet()){
            xContentBuilder.field(entry.getKey(), entry.getValue());
        }
        xContentBuilder.endObject();
        return xContentBuilder;
    }
}
