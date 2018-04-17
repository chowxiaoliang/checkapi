package com.zl.checkapi.elasticsearch.elasticsearchfordev;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zl.checkapi.elasticsearch.ElasticSearchClient;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.Map;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * @author lenovo
 */
public class AddDataTest {
    public static void main(String[] args) throws IOException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.xml");
        OriginalBean originalBean = new OriginalBean();
        JSONObject jsonObject = (JSONObject) JSON.toJSON(originalBean);
        IndexRequest indexRequest = new IndexRequest("xuchuangbin", "rule", originalBean.getRiskFlowNo())
                .source(_buildSource(jsonObject));
        ElasticSearchClient.client().index(indexRequest);
    }

    private static XContentBuilder _buildSource(Map<String, Object> params) throws IOException{
        XContentBuilder build = jsonBuilder().startObject();
        for (String field : params.keySet()) {
            build.field(field, params.get(field));
        }
        build.endObject();
        return build;
    }

}