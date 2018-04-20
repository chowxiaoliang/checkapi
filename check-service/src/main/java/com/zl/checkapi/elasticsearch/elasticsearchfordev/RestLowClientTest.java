package com.zl.checkapi.elasticsearch.elasticsearchfordev;

import com.alibaba.fastjson.JSONObject;
import com.zl.checkapi.elasticsearch.ElasticSearchClient;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

public class RestLowClientTest {
    public static void main(String[] args) throws Exception{
        ApplicationContext ctx = new ClassPathXmlApplicationContext("application.xml");
        RestClient restClient = ElasticSearchClient.client().getLowLevelClient();
        String index = "xuchuangbin";
        String type = "rule";
        //使用RestLowLevelClient创建索引
        TimeValue timeout = TimeValue.timeValueSeconds(60);
        // 预定义一个索引
        JSONObject settings = new JSONObject();
        JSONObject maps = new JSONObject();
        maps.put("number_of_shards",5);
        settings.put("settings", maps);
        String settingJson = settings.toJSONString();
        HttpEntity entity = new NStringEntity(settingJson, ContentType.APPLICATION_JSON);
        restClient.performRequest("PUT", index, Collections.singletonMap("timeout",timeout.toString()), entity);

        TimeUnit.SECONDS.sleep(60);//刚创建index再创建mapping容易出错

        XContentBuilder mapping = jsonBuilder().startObject().startObject(type);
        mapping.startObject("properties")
                    .startObject("sys_date_time")
                        .field("type", "date")
                    .endObject()
                    .startObject("sys_sort_time") //时间
                        .field("type", "integer")
                    .endObject()
                    .startObject("riskFlowNo")
                        .field("type", "keyword")
                        .field("index", "true")
                        .field("ignore_above",256)
                    .endObject()
                    .startObject("partnerId")
                        .field("type", "keyword")
                        .field("index", "true")
                        .field("ignore_above",256)
                    .endObject()
                    .startObject("appId")
                        .field("type", "keyword")
                        .field("index", "true")
                        .field("ignore_above",256)
                    .endObject()
                    .startObject("eventType")
                        .field("type", "keyword")
                        .field("index", "true")
                        .field("ignore_above",256)
                    .endObject()
                    .startObject("ruleId")
                        .field("type", "keyword")
                        .field("index", "true")
                        .field("ignore_above",256)
                    .endObject()
                    .startObject("ruleName")
                        .field("type", "keyword")
                        .field("index", "true")
                        .field("ignore_above",256)
                    .endObject()
                    .startObject("template")
                        .field("type", "keyword")
                        .field("index", "true")
                        .field("ignore_above",64)
                    .endObject()
                    .startObject("strategyId")
                        .field("type", "keyword")
                        .field("index", "true")
                        .field("ignore_above",256)
                    .endObject()
                    .startObject("strategyName")
                        .field("type", "keyword")
                        .field("index", "true")
                        .field("ignore_above",256)
                    .endObject()
                    .startObject("strategySetId")
                        .field("type", "keyword")
                        .field("index", "true")
                        .field("ignore_above",256)
                    .endObject()
                    .startObject("strategySetName")
                        .field("type", "keyword")
                        .field("index", "true")
                        .field("ignore_above",256)
                    .endObject()
                    .startObject("riskType")
                        .field("type", "keyword")
                        .field("index", "true")
                        .field("ignore_above",256)
                    .endObject()
                    .startObject("score")
                        .field("type", "integer")
                    .endObject()
                    .startObject("logTime")
                        .field("type", "keyword")
                        .field("index", "true")
                        .field("ignore_above",256)
                    .endObject()
                    .startObject("informStatus")
                        .field("type", "keyword")
                        .field("index", "true")
                        .field("ignore_above",256)
                    .endObject()
                    .startObject("strategyMode")
                        .field("type", "keyword")
                        .field("index", "true")
                        .field("ignore_above",256)
                    .endObject()
                    .startObject("reviewType")
                        .field("type", "keyword")
                        .field("index", "true")
                        .field("ignore_above",256)
                    .endObject()
                    .startObject("refFlowNo")
                        .field("type", "keyword")
                        .field("index", "true")
                        .field("ignore_above",256)
                    .endObject()
                    .startObject("decision")
                        .field("type", "keyword")
                        .field("index", "true")
                        .field("ignore_above",256)
                    .endObject()
                    .startObject("blackListId")
                        .field("type", "keyword")
                        .field("index", "true")
                        .field("ignore_above",256)
                    .endObject()
                    .startObject("rejectValue")
                        .field("type", "integer")
                    .endObject()
                    .startObject("reviewValue")
                        .field("type", "integer")
                    .endObject()
                .endObject();
        mapping.endObject().endObject();

        Map<String, String> params = Collections.emptyMap();

        HttpEntity mappingEntity = new NStringEntity(mapping.string(), ContentType.APPLICATION_JSON);
        restClient.performRequest("PUT", index+"/_mapping/"+type, params, mappingEntity);
    }
}
