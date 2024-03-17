//package com.zl.checkapi.main;
//
//import com.alibaba.fastjson.JSONObject;
//import com.bqs.risk.datamarket.hbaseapi.entity.ReqResult;
//import com.zl.checkapi.phoenix.EventQueryServiceImpl;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.TimeUnit;
//
///**
// * @author zhouliang
// * @since 2018-06-05 19:31
// * @desc 合作方推事件历史数据到指定sftp服务器
// * 数据来源 1.es 2.hbase
// * 时间点：2018-01-01 00:00:00–2018-05-31 23:59:59 (01-01 00:00:00 至3-31 23:59:59数据从hbase出 4-1 00:00:00 至 5-31 23:59:59数据从es出)
// * 合作方：华夏信财 partnerId: huaxiafinance appId: huacaiOnline
// * 数据字段：姓名，身份证，手机号，流水号，事件类型，评估结果，平台，事件时间，来源IP，IP城市，应用名称
// **/
//public class RiskFlowNoToSftp {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(RiskFlowNoToSftp.class);
//
//    /**
//     * 每次滚动查询的数量
//     */
//    private static final int SCROLL_SIZE = 1000;
//
//    private static final Long OCCUR_TIME_START = 1517241600000L;
//
//    private static final Long OCCUR_TIME_END = 1517327999000L;
//
//    private static final String PARTNER_ID = "crnet";
//
//    private static final String APP_ID = "sysAppId";
//
//    private static final String FILE_PATH = "/app/crnet_2018-01-30(1).txt";
//
////    private static final String FILE_PATH = "C:\\Users\\lenovo\\Desktop\\crnet_2018-01-30.txt";
//
//    private static final List<RiskEvents> LIST = new ArrayList<>();
//
//    public static void main(String[] args) {
//
//        long startTime = System.currentTimeMillis();
//
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.xml");
//        EventQueryServiceImpl eventQueryService = applicationContext.getBean("eventQueryServiceImpl", EventQueryServiceImpl.class);
//
//        LOGGER.info("等待15s后开始导数据...");
//        try {
//            TimeUnit.SECONDS.sleep(15);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//
//        Map<String, Object> queryMap = new HashMap<>(16);
//        queryMap.put("occurTimeStart", String.valueOf(OCCUR_TIME_START));
//        queryMap.put("occurTimeEnd", String.valueOf(OCCUR_TIME_END));
//        queryMap.put("partnerId", PARTNER_ID);
//        queryMap.put("appId", APP_ID);
//        int totalLength = 0;
//        //得到总条数
//        ReqResult<List<Map<String, Object>>> testResult = eventQueryService.queryEvent(queryMap, 1, 0);
//        if(testResult!=null && testResult.getData()!=null && testResult.getData().size()>0){
//            totalLength = testResult.getTotal();
//        }
//        LOGGER.info("Phoenix中存在总数据的条数是=>{}", totalLength);
//        int beginIndex = 0;
//        int currentSize = 0;
//
//        //1.从Phoenix出数(分段取)
//        LOGGER.info("开始从Phoenix取数...");
//        ReqResult<List<Map<String, Object>>> reqResult ;
//        while (beginIndex < totalLength){
//            reqResult = eventQueryService.queryEvent(queryMap, SCROLL_SIZE, beginIndex);
//            if(reqResult!=null && reqResult.getData()!=null && reqResult.getData().size()>0){
//                currentSize = reqResult.getData().size();
//                List<Map<String, Object>> list = reqResult.getData();
//                if(list.size()>0){
//                    LOGGER.info("从Phoenix取数{}条", list.size());
//                    dataTransfer(list, LIST);
//                    LOGGER.info("已经读取的记录条数是=>{}", LIST.size());
//                    list = null;
//                }
//            }
//            beginIndex += currentSize;
//            try {
//                TimeUnit.MILLISECONDS.sleep(100);
//            } catch (InterruptedException e) {
//                LOGGER.error("error occur!");
//                e.printStackTrace();
//            }
//        }
//        LOGGER.info("从Phoenix取数结束，计算的到的总条数是=>{}", LIST.size());
//
//        //写数据到文件
//        File file = new File(FILE_PATH);
//        Writer writer = null;
//        BufferedWriter bufferedWriter = null;
//        try {
//            writer = new FileWriter(file);
//            bufferedWriter = new BufferedWriter(writer);
//            for(int i=0;i<LIST.size();i++){
//                if(i==LIST.size()-1){
//                    bufferedWriter.write(JSONObject.toJSONString(LIST.get(i)));
//                }else{
//                    bufferedWriter.write(JSONObject.toJSONString(LIST.get(i)));
//                    bufferedWriter.newLine();
//                }
//            }
//            bufferedWriter.flush();
//            writer.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally {
//            if(null!=bufferedWriter){
//                try {
//                    bufferedWriter.close();
//                }catch (IOException e){
//                    e.printStackTrace();
//                }
//            }
//            if(null!=writer){
//                try {
//                    writer.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        long endTime = System.currentTimeMillis();
//        LOGGER.info("数据写入成功！共耗时=>{} minutes", (endTime - startTime)/(1000*60));
//
//        LOGGER.info("导数完成，正退出程序...");
//        System.gc();
//        System.exit(0);
//    }
//
//    private static void dataTransfer(List<Map<String, Object>> list, List<RiskEvents> resultList){
//        for(Map<String, Object> map : list){
//            RiskEvents riskEvent = new RiskEvents();
//            riskEvent.setRiskFlowNo(map.get("riskFlowNo")!=null?map.get("riskFlowNo").toString():null);
//            riskEvent.setAppId(map.get("appId")!=null?map.get("appId").toString():null);
//            riskEvent.setEventType(map.get("eventType")!=null?map.get("eventType").toString():null);
//            riskEvent.setName(map.get("name")!=null?map.get("name").toString():null);
////            riskEvent.setCertNo(map.get("certNo")!=null?map.get("certNo").toString():null);
//            //手机号脱敏
//            riskEvent.setMobile(map.get("mobile")!=null?map.get("mobile").toString().replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2"):null);
//            resultList.add(riskEvent);
//        }
//    }
//
//    static class RiskEvents{
//        private String riskFlowNo;
//        private String appId;
//        private String eventType;
//        private String certNo;
//        private String mobile;
//        private String name;
//
//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public String getRiskFlowNo() {
//            return riskFlowNo;
//        }
//
//        public void setRiskFlowNo(String riskFlowNo) {
//            this.riskFlowNo = riskFlowNo;
//        }
//
//        public String getAppId() {
//            return appId;
//        }
//
//        public void setAppId(String appId) {
//            this.appId = appId;
//        }
//
//        public String getEventType() {
//            return eventType;
//        }
//
//        public void setEventType(String eventType) {
//            this.eventType = eventType;
//        }
//
//        public String getCertNo() {
//            return certNo;
//        }
//
//        public void setCertNo(String certNo) {
//            this.certNo = certNo;
//        }
//
//        public String getMobile() {
//            return mobile;
//        }
//
//        public void setMobile(String mobile) {
//            this.mobile = mobile;
//        }
//    }
//
//}
