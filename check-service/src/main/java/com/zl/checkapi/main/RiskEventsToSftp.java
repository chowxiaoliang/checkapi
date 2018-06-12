package com.zl.checkapi.main;

import com.alibaba.fastjson.JSONObject;
import com.bqs.risk.datamarket.hbaseapi.entity.ReqResult;
import com.zl.checkapi.elasticsearch.ElasticSearchClient;
import com.zl.checkapi.elasticsearch.EventSearchConsts;
import com.zl.checkapi.elasticsearch.EventUtil;
import com.zl.checkapi.phoenix.EventQueryServiceImpl;
import com.zl.checkapi.util.ZipUtil;
import net.lingala.zip4j.exception.ZipException;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchScrollRequest;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author zhouliang
 * @since 2018-06-05 19:31
 * @desc 合作方推事件历史数据到指定sftp服务器
 * 数据来源 1.es 2.hbase
 * 时间点：2018-01-01 00:00:00–2018-05-31 23:59:59 (01-01 00:00:00 至3-31 23:59:59数据从hbase出 4-1 00:00:00 至 5-31 23:59:59数据从es出)
 * 合作方：华夏信财 partnerId: huaxiafinance appId: huacaiOnline
 * 数据字段：姓名，身份证，手机号，流水号，事件类型，评估结果，平台，事件时间，来源IP，IP城市，应用名称
 **/
public class RiskEventsToSftp {

    private static final Logger LOGGER = LoggerFactory.getLogger(RiskEventsToSftp.class);

    /**
     * 每次滚动查询的数量
     */
    private static final int SCROLL_SIZE = 1000;

    private static final Long OCCUR_TIME_START = 1514736000000L;

    private static final Long OCCUR_TIME_MIDDLE = 1522511999000L;

    private static final Long OCCUR_TIME_END = 1527782399000L;

    private static final String PARTNER_ID = "huaxiafinance";

    private static final String APP_ID = "huacaiOnline";

    private static final String FILE_PATH = "/app/huaxiafinance_eventHistory_2018-01-01_2018-05-31.txt";

    private static final String ZIP_PATH= "/app/huaxiafinance_eventHistory_2018-01-01_2018-05-31.zip";

    private static final String ENCRYPTION_KEY = "Fage3902#iew31";

    private static final List<RiskEvent> LIST = new ArrayList<>();

    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.xml");
        EventQueryServiceImpl eventQueryService = applicationContext.getBean("eventQueryServiceImpl", EventQueryServiceImpl.class);

        LOGGER.info("等待15s后开始导数据...");
        try {
            TimeUnit.SECONDS.sleep(15);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Map<String, Object> queryMap = new HashMap<>(16);
        queryMap.put("occurTimeStart", String.valueOf(OCCUR_TIME_START));
        queryMap.put("occurTimeEnd", String.valueOf(OCCUR_TIME_MIDDLE));
        queryMap.put("partnerId", PARTNER_ID);
        queryMap.put("appId", APP_ID);
        int totalLength = 0;
        //得到总条数
        ReqResult<List<Map<String, Object>>> testResult = eventQueryService.queryEvent(queryMap, 1, 0);
        if(testResult!=null && testResult.getData()!=null && testResult.getData().size()>0){
            totalLength = testResult.getTotal();
        }
        LOGGER.info("Phoenix中存在总数据的条数是=>{}", totalLength);
        int beginIndex = 0;
        int currentSize = 0;

        //1.从Phoenix出数(分段取)
        LOGGER.info("开始从Phoenix取数...");
        ReqResult<List<Map<String, Object>>> reqResult ;
        while (beginIndex < totalLength){
            reqResult = eventQueryService.queryEvent(queryMap, SCROLL_SIZE, beginIndex);
            if(reqResult!=null && reqResult.getData()!=null && reqResult.getData().size()>0){
                currentSize = reqResult.getData().size();
                List<Map<String, Object>> list = reqResult.getData();
                if(list.size()>0){
                    LOGGER.info("从Phoenix取数{}条", list.size());
                    dataTransfer(list, LIST);
                    LOGGER.info("已经读取的记录条数是=>{}", LIST.size());
                    list = null;
                }
            }
            beginIndex += currentSize;
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                LOGGER.error("error occur!");
                e.printStackTrace();
            }
        }
        LOGGER.info("从Phoenix取数结束，计算的到的总条数是=>{}", LIST.size());

        //2.从es出数(默认使用滚动查询)
        LOGGER.info("开始从es取数...");
        String[] indices = EventUtil.getIndices(EventSearchConsts.EventItem.event, PARTNER_ID, OCCUR_TIME_MIDDLE, OCCUR_TIME_END);

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.filter(QueryBuilders.termQuery("partnerId", PARTNER_ID));
        boolQueryBuilder.filter(QueryBuilders.termQuery("appId", APP_ID));
        boolQueryBuilder.filter(QueryBuilders.rangeQuery("sys_date_time").from(OCCUR_TIME_MIDDLE).to(OCCUR_TIME_END));

        //在这之后将之前滚动的数据内容清掉
        TimeValue timeValue = TimeValue.timeValueSeconds(10);
        SearchRequest searchRequest = new SearchRequest().indices(indices).indicesOptions(IndicesOptions.fromOptions(true, true, true, true));
        searchRequest.scroll(timeValue);

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(boolQueryBuilder);
        searchSourceBuilder.size(SCROLL_SIZE);

        searchRequest.source(searchSourceBuilder);

        LOGGER.info("termAndRangeQuery is => {}", boolQueryBuilder.toString());
        SearchResponse searchResponse = null;

        try {
            searchResponse = ElasticSearchClient.client().search(searchRequest);
        } catch (IOException e) {
            LOGGER.error("search errors occur!", e);
        }

        //总条数
        int totalSize = 0;
        if(searchResponse!=null && searchResponse.getHits().totalHits>0){
            totalSize = (int)searchResponse.getHits().totalHits;
            LOGGER.info("从es取数总条数是=>{}", totalSize);
        }

        int fromIndex = 0;
        int lastIndex = totalSize;

        int from = 0;
        int size = totalSize;

        //每次查询的数量
        int hitSize ;

        //记录总条数
        int num = 1;

        do{
            hitSize = searchResponse.getHits().getHits().length;
            LOGGER.info("current hitSize is => {}", hitSize);
            if((fromIndex + hitSize >= from) && fromIndex < lastIndex){
                int startIndex = Math.max(fromIndex, from);
                int endIndex = Math.min((fromIndex + hitSize), (from + size));

                //本次查询hits中取数的开始下标
                int startHitIndex ;
                if (fromIndex < from) {
                    startHitIndex = from - fromIndex;
                } else {
                    startHitIndex = 0;
                }
                //本次查询hits中共取数条数
                int subLength = endIndex - startIndex;
                LOGGER.info("current subLength is => {}", subLength);
                LOGGER.info("current startHitIndex is => {}", startHitIndex);

                for (int i = startHitIndex; i < startHitIndex + subLength; i++) {
                    SearchHit searchHit = searchResponse.getHits().getAt(i);
                    Map<String, Object> map = searchHit.getSourceAsMap();
                    LOGGER.info("从es取的第{}条数据是=>{}", num, map);
                    if(map!=null){
                        RiskEvent riskEvent = new RiskEvent();
                        beanTransfer(map, riskEvent, LIST);
                        num ++ ;
                        riskEvent = null;
                        map = null;
                        searchHit = null;
                    }
                }

                fromIndex += hitSize;
                LOGGER.info("current fromIndex is => {}", fromIndex);
                if (fromIndex >= lastIndex) {
                    break;
                }
                if (hitSize < SCROLL_SIZE) {
                    break;
                }

                SearchScrollRequest searchScrollRequest = new SearchScrollRequest();
                searchScrollRequest.scrollId(searchResponse.getScrollId());
                LOGGER.info("current scrollId is => {}", searchResponse.getScrollId());
                searchScrollRequest.scroll(timeValue);
                try {
                    searchResponse = ElasticSearchClient.client().searchScroll(searchScrollRequest);
                    searchScrollRequest = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }while (searchResponse.getHits().getHits().length>0);

        searchResponse = null;

        LOGGER.info("从es取数结束! 得到数据的总条数是=>{}", num-1);

        LOGGER.info("取数结束，总的条数是=>", LIST.size());

        //写数据到文件
        File file = new File(FILE_PATH);
        Writer writer = null;
        BufferedWriter bufferedWriter = null;
        try {
            writer = new FileWriter(file);
            bufferedWriter = new BufferedWriter(writer);
            for(int i=0;i<LIST.size();i++){
                if(i==LIST.size()-1){
                    bufferedWriter.write(JSONObject.toJSONString(LIST.get(i)));
                }else{
                    bufferedWriter.write(JSONObject.toJSONString(LIST.get(i)));
                    bufferedWriter.newLine();
                }
            }
            bufferedWriter.flush();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(null!=bufferedWriter){
                try {
                    bufferedWriter.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            if(null!=writer){
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        long endTime = System.currentTimeMillis();
        LOGGER.info("数据写入成功！共耗时=>{} minutes", (endTime - startTime)/(1000*60));

        //生成zip文件
        try {
            ZipUtil.zip(FILE_PATH, ZIP_PATH, ENCRYPTION_KEY);
            LOGGER.info("文件压缩成功!");
        } catch (ZipException e) {
            LOGGER.info("zip压缩文件生成失败！");
            e.printStackTrace();
        }

        LOGGER.info("导数完成，正退出程序...");
        System.gc();
        System.exit(0);
    }

    /**
     * 数据转换
     * @param originList
     * @param destinationList
     */
    private static void dataTransfer(List<Map<String, Object>> originList, List<RiskEvent> destinationList){
        for(Map<String, Object> map : originList){
            RiskEvent riskEvent = new RiskEvent();
            riskEvent.setName(map.get("name")!=null?map.get("name").toString():null);
            riskEvent.setCertNo(map.get("certNo")!=null?map.get("certNo").toString():null);
            riskEvent.setMobile(map.get("mobile")!=null?map.get("mobile").toString():null);
            riskEvent.setRiskFlowNo(map.get("riskFlowNo")!=null?map.get("riskFlowNo").toString():null);
            riskEvent.setEventType(map.get("eventType")!=null?map.get("eventType").toString():null);
            riskEvent.setFinalDecision(map.get("finalDecision")!=null?map.get("finalDecision").toString():null);
            riskEvent.setPlatform(map.get("platform")!=null?map.get("platform").toString():null);
            riskEvent.setOccurTime(map.get("occurTime")!=null?Long.valueOf(map.get("occurTime").toString()):null);
            riskEvent.setIp(map.get("ip")!=null?map.get("ip").toString():null);
            riskEvent.setIpCity(map.get("ipCity")!=null?map.get("ipCity").toString():null);
            riskEvent.setAppName(map.get("appName")!=null?map.get("appName").toString():null);
            destinationList.add(riskEvent);
            riskEvent = null;
        }
    }

    /**
     * 中间数据直接入到list
     * @param map
     * @param riskEvent
     * @param finalList
     */
    private static void beanTransfer(Map<String, Object> map, RiskEvent riskEvent, List<RiskEvent> finalList){
        riskEvent.setName(map.get("name")!=null?map.get("name").toString():null);
        riskEvent.setCertNo(map.get("certNo")!=null?map.get("certNo").toString():null);
        riskEvent.setMobile(map.get("mobile")!=null?map.get("mobile").toString():null);
        riskEvent.setRiskFlowNo(map.get("riskFlowNo")!=null?map.get("riskFlowNo").toString():null);
        riskEvent.setEventType(map.get("eventType")!=null?map.get("eventType").toString():null);
        riskEvent.setFinalDecision(map.get("finalDecision")!=null?map.get("finalDecision").toString():null);
        riskEvent.setPlatform(map.get("platform")!=null?map.get("platform").toString():null);
        riskEvent.setOccurTime(map.get("occurTime")!=null?Long.valueOf(map.get("occurTime").toString()):null);
        riskEvent.setIp(map.get("ip")!=null?map.get("ip").toString():null);
        riskEvent.setIpCity(map.get("ipCity")!=null?map.get("ipCity").toString():null);
        riskEvent.setAppName(map.get("appName")!=null?map.get("appName").toString():null);
        finalList.add(riskEvent);
    }

    static class RiskEvent{

        private String name;

        private String certNo;

        private String mobile;

        private String riskFlowNo;

        private String eventType;

        private String finalDecision;

        private String platform;

        private long occurTime;

        private String ip;

        private String ipCity;

        private String appName;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCertNo() {
            return certNo;
        }

        void setCertNo(String certNo) {
            this.certNo = certNo;
        }

        public String getMobile() {
            return mobile;
        }

        void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getRiskFlowNo() {
            return riskFlowNo;
        }

        void setRiskFlowNo(String riskFlowNo) {
            this.riskFlowNo = riskFlowNo;
        }

        public String getEventType() {
            return eventType;
        }

        void setEventType(String eventType) {
            this.eventType = eventType;
        }

        public String getFinalDecision() {
            return finalDecision;
        }

        void setFinalDecision(String finalDecision) {
            this.finalDecision = finalDecision;
        }

        public String getPlatform() {
            return platform;
        }

        void setPlatform(String platform) {
            this.platform = platform;
        }

        public long getOccurTime() {
            return occurTime;
        }

        void setOccurTime(long occurTime) {
            this.occurTime = occurTime;
        }

        public String getIp() {
            return ip;
        }

        void setIp(String ip) {
            this.ip = ip;
        }

        public String getIpCity() {
            return ipCity;
        }

        void setIpCity(String ipCity) {
            this.ipCity = ipCity;
        }

        public String getAppName() {
            return appName;
        }

        void setAppName(String appName) {
            this.appName = appName;
        }
    }
}
