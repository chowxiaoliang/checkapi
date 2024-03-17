//package com.zl.checkapi.phoenix;
//
//import com.alibaba.fastjson.JSON;
//import com.bqs.risk.datamarket.hbaseapi.entity.ReqResult;
//import com.bqs.risk.datamarket.hbaseapi.entity.Response;
//import com.bqs.risk.datamarket.hbaseapi.service.EventQueryService;
//import com.bqs.risk.datamarket.hbaseapi.service.HbaseUtilService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import java.sql.SQLException;
//import java.util.*;
//
///**
// * Created by zhaochenxi on 2017/6/8.
// */
//@Service
//public class EventQueryServiceImpl implements EventQueryService {
//
//    private static final Logger logger = LoggerFactory.getLogger(EventQueryServiceImpl.class);
//
//    @Value("${hbaseFieldDict}")
//    private static String hbaseFieldDict;
//
//    private static Map<String,String> hbaseFiledDictMap= new HashMap<String,String>();
//
//    static {
//        init();
//    }
//
//    @Value("${phoenix.table.risk.event}")
//    private static String PHOENIX_TABLE_NAME;
//
//    //分表依据字段
//    private static final String TIME_FIELD = "PRMI_OCCURTIME";
//    private static final String HBASE_RISK_EVENT_TABLE_NAME = "PRMI_RISK_EVENT";
//    private static final String HBASE_HIT_RULE_LOG_TABLE_NAME = "HIT_RULE_LOG";
//    private static final String HBASE_HIT_STRATAGE_LOG_TABLE_NAME="HIT_STRATAGE_LOG";
//
//    /**
//     * phoenix中有以下两个索引，这个索引只能做前缀匹配
//     * (PRMI_PARTNERID,PRMI_APPID,PRMI_OCCURTIME, PRMI_MOBILE)
//     * (PRMI_PARTNERID,PRMI_APPID,PRMI_OCCURTIME, PRMI_CERTNO)
//     */
//    private static final String PRMI_PARTNERID = "PRMI_PARTNERID";
//    private static final String PRMI_APPID = "PRMI_APPID";
//    private static final String PRMI_OCCURTIME = "PRMI_OCCURTIME";
//    private static final String PRMI_MOBILE = "PRMI_MOBILE";
//    private static final String PRMI_CERTNO = "PRMI_CERTNO";
//
//    /**
//     * 初始化字段转化字典
//     */
//    public static void init(){
//        if(hbaseFieldDict == null){
//            ConfigMap.INSTANCE.init();
//            hbaseFieldDict = ConfigMap.INSTANCE.getValue("hbaseFieldDict");
//        }
//        String [] fieldDictArray = hbaseFieldDict.split("\\,");
//        for(String str:fieldDictArray){
//            String [] array = str.split(":");
//            hbaseFiledDictMap.put(array[0],array[1]);
//        }
//        if(PHOENIX_TABLE_NAME == null){
//            PHOENIX_TABLE_NAME = ConfigMap.INSTANCE.getValue("phoenix.table.risk.event");
//        }
//
//    }
//
//    /**
//     * 查询事件历史
//     * @param conditions
//     * @param offset
//     * @param offset
//     * @return
//     * @throws SQLException
//     * @throws ClassNotFoundException
//     */
//    @Override
//    public ReqResult<List<Map<String,Object>>> queryEvent(Map<String, Object> conditions, int limit, int offset){
//        long startTime = System.currentTimeMillis();
//        String queryParams = JSON.toJSONString(conditions);
//        logger.info("[queryEvent] start query queryParams="+queryParams);
//        ReqResult<List<Map<String,Object>>> response = new ReqResult<List<Map<String,Object>>>();
//        //1.从Phoenix中查询出RowKey
//        List<Map<String,String>> pkMapList = null;
//        //2.查询满足条件的数据总数
//        long count = 0;
//        try {
//            pkMapList = queryEventRowKeyFromPhoenix(conditions,limit,offset);
//            count = selectCount(conditions,limit,offset);
//        } catch (SQLException e) {
//            response.setQueryCode(Constants.RESULTCODE_FAILED);
//            response.setQueryTypeDesc("Phoenix Exception");
//            logger.error("[queryEvent] phoenix SQLException");
//            e.printStackTrace();
//            return response;
//        } catch (ClassNotFoundException e) {
//            response.setQueryCode(Constants.RESULTCODE_FAILED);
//            response.setQueryTypeDesc("Phoenix Exception");
//            logger.error("[queryEvent] phoenix ClassNotFoundException");
//            e.printStackTrace();
//            return response;
//        }
//
//        //3.根据RowKey在Hbase中查询详情
//        response = queryEventFromHbase(pkMapList);
//        response.setOffset(offset);
//        response.setStart(limit);
//        response.setTotal((int)count);
//        response.setQueryCode(Constants.RESULTCODE_SUCESS);
//        long endTime = System.currentTimeMillis();
//        logger.info(String.format("[queryEvent] query cost %s ms,query=%s",endTime-startTime,queryParams));
//        return response;
//    }
//
//    @Override
//    public ReqResult<Map<String, Object>> queryRiskEventsByRiskFlowNo(String partnerId, String riskFlowNo) {
//        long startTime = System.currentTimeMillis();
//        logger.info("[queryRiskEventsByRiskFlowNo] start query riskFlowNo="+riskFlowNo);
//        ReqResult<Map<String, Object>> reqResult = new ReqResult<Map<String, Object>>();
//        String rowKey = CommonUtils.createRowKey(partnerId,riskFlowNo);
//        HbaseUtilService hbaseService = new HbaseUtilEventServiceImpl();
//        //TODO 测试代码，一定要记得去掉
////        if(riskFlowNo.equals("17060515099D07BD921C2840AEAD542DF5B484485A")){
////            rowKey = "000d440eDONGBO_17060515099D07BD921C2840AEAD542DF5B484485A";
////        }
//        Response response = hbaseService.queryByRowKey(HBASE_RISK_EVENT_TABLE_NAME,rowKey);
//        if(Constants.QUERY_SUCCESS.equals(response.getQueryCode())){
//            Map<String,Object> tmp = convertMapKey(response.getData()).get(0);
//            reqResult.setData(tmp);
//            reqResult.setQueryCode(Constants.RESULTCODE_SUCESS);
//            reqResult.setQueryTypeDesc("SUCCESS");
//        }else{
//            reqResult.setData(null);
//            reqResult.setQueryCode(Constants.RESULTCODE_FAILED);
//            reqResult.setQueryTypeDesc(response.getQueryTypeDesc());
//            logger.error("[queryRiskEventsByRiskFlowNo] query error,riskFlowNo="+riskFlowNo);
//        }
//        long endTime = System.currentTimeMillis();
//        logger.info(String.format("[queryRiskEventsByRiskFlowNo] query cost %s ms,riskFlowNo=%s",endTime-startTime,riskFlowNo));
//        return reqResult;
//    }
//
//    /**
//     * 查询事件的击中规则
//     * @param partnerId
//     * @param riskFlowNo
//     * @return
//     */
//    @Override
//    public ReqResult<List<Map<String, Object>>> riskHitRuleListByRiskFlowNo(String partnerId, String riskFlowNo) {
//        long startTime = System.currentTimeMillis();
//        logger.info("[riskHitRuleListByRiskFlowNo] start query riskFlowNo="+riskFlowNo);
//        ReqResult<List<Map<String, Object>>> reqResult = new ReqResult<List<Map<String, Object>>>();
//
//        String rowKey = CommonUtils.createRowKey(partnerId,riskFlowNo);
//        HbaseUtilService hbaseService = new HbaseUtilEventServiceImpl();
//        //从事件历史表中查询出事件
//        Response response = hbaseService.queryByRowKey(HBASE_RISK_EVENT_TABLE_NAME,rowKey);
//        if(Constants.QUERY_SUCCESS.equals(response.getQueryCode())){
//            if(response.getData() == null){
//                reqResult.setData(null);
//                reqResult.setQueryCode(Constants.RESULTCODE_EMPTY);
//                reqResult.setQueryTypeDesc(response.getQueryTypeDesc());
//                logger.error("EventQueryServiceImpl.riskHitRuleListByRiskFlowNo,not found this data from PRMI_RISK_EVENT rowKey="+ rowKey);
//                return reqResult;
//            }
//
//            //从事件历史中提取规则ID
//            Set<String> ruleIdSet = getHitRuleId(response.getData().get(0));
//            if(ruleIdSet==null||ruleIdSet.isEmpty()){
//                //事件没有击中规则
//                reqResult.setData(null);
//            }else{
//                //在HIT_RULE_LOG中查询规则详情
//                List<Map<String, Object>> ruleMapList=queryRule(partnerId,riskFlowNo,ruleIdSet);
//                reqResult.setData(ruleMapList);
//            }
//            reqResult.setQueryCode(Constants.RESULTCODE_SUCESS);
//            reqResult.setQueryTypeDesc("SUCCESS");
//        }else{
//            reqResult.setData(null);
//            reqResult.setQueryCode(Constants.RESULTCODE_FAILED);
//            reqResult.setQueryTypeDesc(response.getQueryTypeDesc());
//            logger.error("EventQueryServiceImpl.riskHitRuleListByRiskFlowNo,query error rowKey="+ rowKey);
//        }
//        long endTime = System.currentTimeMillis();
//        logger.info(String.format("[riskHitRuleListByRiskFlowNo] query cost %s ms,riskFlowNo=%s",endTime-startTime,riskFlowNo));
//        return reqResult;
//    }
//
//    /**
//     * 查询事件的击中策略
//     * @param partnerId
//     * @param riskFlowNo
//     * @return
//     */
//    @Override
//    public ReqResult<List<Map<String, Object>>> riskHitStrategyListByRiskFlowNo(String partnerId, String riskFlowNo) {
//        long startTime = System.currentTimeMillis();
//        logger.info("[riskHitRuleListByRiskFlowNo] start query riskFlowNo="+riskFlowNo);
//        ReqResult<List<Map<String, Object>>> reqResult = new ReqResult<List<Map<String, Object>>>();
//        String rowKey = CommonUtils.createRowKey(partnerId,riskFlowNo);
//        HbaseUtilService hbaseService = new HbaseUtilEventServiceImpl();
//        //从事件表中查询判断命中的策略
//        Response response = hbaseService.queryByRowKey(HBASE_RISK_EVENT_TABLE_NAME,rowKey);
//        if(Constants.QUERY_SUCCESS.equals(response.getQueryCode())){
//
//            if(response.getData() == null){
//                reqResult.setData(null);
//                reqResult.setQueryCode(Constants.RESULTCODE_EMPTY);
//                reqResult.setQueryTypeDesc(response.getQueryTypeDesc());
//                logger.error("[riskHitStrategyListByRiskFlowNo],not found this data from PRMI_RISK_EVENT rowKey="+ rowKey);
//                return reqResult;
//            }
//
//            Set<String> strategyIdSet = getHitStrategyId(response.getData().get(0));
//            if (strategyIdSet==null||strategyIdSet.isEmpty()){
//                //没有击中策略
//                reqResult.setData(null);
//            }else{
//                List<Map<String, Object>> strategyMapList= queryStrategy(partnerId,riskFlowNo,strategyIdSet);
//                reqResult.setData(strategyMapList);
//                reqResult.setTotal(strategyMapList.size());
//            }
//            reqResult.setQueryCode(Constants.RESULTCODE_SUCESS);
//            reqResult.setQueryTypeDesc("SUCCESS");
//        }else{
//            reqResult.setData(null);
//            reqResult.setQueryCode(Constants.RESULTCODE_FAILED);
//            reqResult.setQueryTypeDesc(response.getQueryTypeDesc());
//            logger.error("[riskHitStrategyListByRiskFlowNo],query error rowKey="+ rowKey);
//        }
//        long endTime = System.currentTimeMillis();
//        logger.info(String.format("[riskHitStrategyListByRiskFlowNo] query cost %s ms,riskFlowNo=%s",endTime-startTime,riskFlowNo));
//        return reqResult;
//    }
//
//    /**
//     * 从Phoenix中查询事件的RowKey
//     * @param conditions
//     * @param limit
//     * @param offset
//     * @return
//     * @throws SQLException
//     * @throws ClassNotFoundException
//     */
//    public List<Map<String,String>> queryEventRowKeyFromPhoenix(Map<String, Object> conditions,int limit, int offset) throws SQLException, ClassNotFoundException {
//        List<String> queryColumns =  createQueryColumns();
//        PhoenixUtils phoenixUtils = PhoenixUtils.instance();
//        long startTime = Long.valueOf((String)conditions.get("occurTimeStart"));
//        long endTime = Long.valueOf((String)conditions.get("occurTimeEnd"));
//        String partnerId = (String)conditions.get("partnerId");
//        String appId = (String)conditions.get("appId");
//        String key = null;
//        String field = "";
//        //mobile和certNo只会传一个过来，同时传两个不能匹配，也可以一个都不传
//        if(conditions.containsKey("mobile")){
//            key = (String)conditions.get("mobile");
//            field = "PRMI_MOBILE";
//        }
//        if(conditions.containsKey("certNo")){
//            key = (String)conditions.get("certNo");
//            field = "PRMI_CERTNO";
//        }
//        return phoenixUtils.select(PHOENIX_TABLE_NAME,queryColumns,partnerId,appId,startTime,endTime,key,field,TIME_FIELD, OrderType.DESC,limit,offset);
//    }
//
//    /**
//     * 查询满足条件的事件条数
//     * @param conditions
//     * @param start
//     * @param offset
//     * @return
//     */
//    public long selectCount(Map<String, Object> conditions,int start, int offset) throws SQLException, ClassNotFoundException {
//        PhoenixUtils phoenixUtils = PhoenixUtils.instance();
//        long startTime = Long.valueOf((String)conditions.get("occurTimeStart"));
//        long endTime = Long.valueOf((String)conditions.get("occurTimeEnd"));
//        String partnerId = (String)conditions.get("partnerId");
//        String appId = (String)conditions.get("appId");
//        String key = null;
//        String field = "";
//        //mobile和certNo只会传一个过来，同时传两个不能匹配，也可以一个都不传
//        if(conditions.containsKey("mobile")){
//            key = (String)conditions.get("mobile");
//            field = "PRMI_MOBILE";
//        }
//        if(conditions.containsKey("certNo")){
//            key = (String)conditions.get("certNo");
//            field = "PRMI_CERTNO";
//        }
//        return phoenixUtils.selectCount(PHOENIX_TABLE_NAME,partnerId,appId,startTime,endTime,key,field);
//    }
//
//    /**
//     * 从Hbase中查询事件
//     * @param rowList
//     * @return
//     */
//    public ReqResult<List<Map<String,Object>>> queryEventFromHbase(List<Map<String,String>> rowList){
//        ReqResult<List<Map<String,Object>>> response = new ReqResult<List<Map<String,Object>>>();
//        HbaseUtilEventServiceImpl hbaseService =new HbaseUtilEventServiceImpl();
//        if(rowList==null||rowList.isEmpty()){
//            response.setData(null);
//            response.setQueryCode(Constants.RESULTCODE_INVALID);
//            response.setQueryTypeDesc("rowKeyList is null");
//            return response;
//        }
//        List<Map<String, Object>> resList = new ArrayList<Map<String, Object>>();
//        for(Map<String,String> map:rowList){
//            Response res=hbaseService.queryByRowKey(HBASE_RISK_EVENT_TABLE_NAME,map.get("PK"));
//            if(res==null || res.getData()==null){
//                logger.error("rowkey="+map.get("PK")+"returnDesc:"+res.getQueryTypeDesc()+",hbase or phoenix data exception,not fond this data in hbase");
//                //throw new SystemException("数据库数据异常，在phoenix中找到了PK="+map.get("PK")+"的数据，但是hbase中没有该数据");
//                continue;
//            }
//            List<Map<String, String>> list = res.getData();
//            List<Map<String, Object>> resultMapList = convertMapKey(list);
//            resList.addAll(resultMapList);
//        }
//        response.setData(resList);
//        if(resList.size() != rowList.size()){
//            int n = rowList.size() - resList.size();
//            response.setQueryCode(Constants.RESULTCODE_SUCCESS_DATA_LOST);
//            response.setQueryTypeDesc(Constants.RESULTMSG_SUCCESS_DATA_LOST);
//            logger.error("[queryEventFromHbase] DataLostException ,"+n+" data not found");
//        }else{
//            response.setQueryCode(Constants.RESULTCODE_SUCESS);
//            response.setQueryTypeDesc("SUCCESS");
//        }
//        return response;
//    }
//
//
//    /**
//     * 查询规则
//     * @param partnerId
//     * @param riskFlowNo
//     * @param ruleIdSet
//     * @return
//     */
//    public List<Map<String, Object>> queryRule(String partnerId,String riskFlowNo,Set<String> ruleIdSet){
//        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
//        for(String ruleId:ruleIdSet){
//            String rowKey = CommonUtils.createRuleOrStrategyRowKey(partnerId,riskFlowNo,ruleId);
//            //TODO 测试数据记得删除
//            //rowKey = "0125a668ZHOULEI_1705252117237D72DB41F84B3AAB1DAFA823435949_11772";
//            HbaseUtilService hbaseService = new HbaseUtilEventServiceImpl();
//            //从规则表中查询
//            Response response = hbaseService.queryByRowKey(HBASE_HIT_RULE_LOG_TABLE_NAME,rowKey);
//            if(response!=null&&response.getData()!=null){
//                resultList.addAll(convertRuleMapKey(response.getData()));
//            }else{
//                logger.error("[queryRule] error,hbase not found data");
//            }
//        }
//        return resultList;
//    }
//
//    /**
//     * 查询策略
//     * @param parnterId
//     * @param riskFlowNo
//     * @param strategyIdSet
//     * @return
//     */
//    public List<Map<String, Object>> queryStrategy(String parnterId,String riskFlowNo,Set<String> strategyIdSet){
//        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
//        for(String strategyId:strategyIdSet){
//            String rowKey = CommonUtils.createRuleOrStrategyRowKey(parnterId,riskFlowNo,strategyId);
//            HbaseUtilService hbaseService = new HbaseUtilEventServiceImpl();
//            //从策略表中查询
//            Response response = hbaseService.queryByRowKey(HBASE_HIT_STRATAGE_LOG_TABLE_NAME,rowKey);
//            if(response!=null&&response.getData()!=null){
//                resultList.addAll(convertStrategyeMapKey(response.getData()));
//            }else{
//                logger.error("[queryStrategy] error,hbase not found data");
//            }
//        }
//        return resultList;
//    }
//
//
//    public List<Map<String, Object>> convertMapKey(List<Map<String, String>> maplist){
//        if(maplist==null||maplist.isEmpty()){
//            return null;
//        }
//        List<Map<String,Object>> resultMapList = new ArrayList<>();
//
//        for(Map<String,String> map:maplist){
//            Map<String,Object> tmpMap = new HashMap<String,Object>();
//            for(Map.Entry entry:map.entrySet()){
//                if("PRMI_FINALSCORE".equals(entry.getKey())
//                        || "PRMI_CREATETIME".equals(entry.getKey()) || "PRMI_COSTTIME".equals(entry.getKey()) || "PRMI_CERTNOAGE".equals(entry.getKey())){
//                    if(hbaseFiledDictMap.containsKey(entry.getKey())){
//                        tmpMap.put(hbaseFiledDictMap.get(entry.getKey()),Long.parseLong((String)entry.getValue()));
//                    }else{
//                        tmpMap.put((String)entry.getKey(),Long.parseLong((String)entry.getValue()));
//                    }
//                }if("PRMI_OCCURTIME".equals(entry.getKey())){
//                    String value = (String)entry.getValue();
//                    String time = value.substring(0,10);
//                    long occurTime = Long.parseLong(value);
//                    tmpMap.put(hbaseFiledDictMap.get("PRMI_SYS_SORT_TIME"),entry.getValue());
//                    tmpMap.put(hbaseFiledDictMap.get(entry.getKey()),occurTime);
//                    tmpMap.put(hbaseFiledDictMap.get("PRMI_SYS_DATE_TIME"),occurTime);
//                }else{
//                    if(hbaseFiledDictMap.containsKey(entry.getKey())){
//                        tmpMap.put(hbaseFiledDictMap.get(entry.getKey()),entry.getValue());
//                    }else{
//                        tmpMap.put((String)entry.getKey(),entry.getValue());
//                    }
//                }
//            }
//            if(tmpMap.containsKey("PRMI_LATITUDE")){
//                tmpMap.put("sys_geo_point",tmpMap.get("PRMI_LATITUDE")+","+tmpMap.get("PRMI_LONGITUDE"));
//            }
//            resultMapList.add(tmpMap);
//
//        }
//        return resultMapList;
//    }
//
//    /**
//     * 转换查询出的规则字段
//     * @param maplist
//     * @return
//     */
//    List<Map<String,Object>> convertRuleMapKey(List<Map<String, String>> maplist){
//        if(maplist==null||maplist.isEmpty()){
//            return null;
//        }
//        List<Map<String,Object>> resultMapList = new ArrayList<>();
//        for(Map<String,String> map:maplist) {
//            Map<String, Object> tmpMap = new HashMap<String, Object>();
//            for (Map.Entry entyt : map.entrySet()) {
//                if ("PARTNERID".equals(entyt.getKey())) {
//                    tmpMap.put("partnerId",(String) entyt.getValue());
//                }
//                if ("APPID".equals(entyt.getKey())) {
//                    tmpMap.put("appId",(String) entyt.getValue());
//                }
//                if ("RULEID".equals(entyt.getKey())) {
//                    tmpMap.put("ruleId",(String) entyt.getValue());
//                }
//                if ("RULENAME".equals(entyt.getKey())) {
//                    tmpMap.put("ruleName", (String) entyt.getValue());
//                }
//                if ("RISKFLOWNO".equals(entyt.getKey())) {
//                    tmpMap.put("riskFlowNo",(String) entyt.getValue());
//                }
//                if ("LOGTIME".equals(entyt.getKey())) {
//                    tmpMap.put("logTime", Long.parseLong((String) entyt.getValue()));
//                }
//                if ("LOGFIELD".equals(entyt.getKey())) {
//                    tmpMap.put("logField",(String) entyt.getValue());
//                }
//                if ("STRATEGYMODE".equals(entyt.getKey())) {
//                    tmpMap.put("strategyMode", (String) entyt.getValue());
//                }
//                if ("STRATEGYSETID".equals(entyt.getKey())) {
//                    tmpMap.put("strategyId",(String) entyt.getValue());
//                }
//                if ("STRATEGYNAME".equals(entyt.getKey())) {
//                    tmpMap.put("strategyName", (String) entyt.getValue());
//                }
//                if ("STRATEGYSETNAME".equals(entyt.getKey())) {
//                    tmpMap.put("strategySetName",(String) entyt.getValue());
//                }
//                if ("STRATEGYSETID".equals(entyt.getKey())) {
//                    tmpMap.put("strategySetId", (String) entyt.getValue());
//                }
//                if ("DECISION".equals(entyt.getKey())) {
//                    tmpMap.put("decision",(String) entyt.getValue());
//                }
//                if ("SCORE".equals(entyt.getKey())) {
//                    tmpMap.put("score", Integer.parseInt((String) entyt.getValue()));
//                }
//                if ("RISKHITRULECOL".equals(entyt.getKey())) {
//                    tmpMap.put("riskHitRulecol",(String) entyt.getValue());
//                }
//                if ("EVENTTYPE".equals(entyt.getKey())) {
//                    tmpMap.put("eventType", (String) entyt.getValue());
//                }
//                if ("BLACKLISTID".equals(entyt.getKey())) {
//                    tmpMap.put("blacklistId", (String) entyt.getValue());
//                }
//                if ("REFFLOWNO".equals(entyt.getKey())) {
//                    tmpMap.put("refFlowNo",(String) entyt.getValue());
//                }
//
//            }
//            resultMapList.add(tmpMap);
//        }
//        return resultMapList;
//    }
//
//    /**
//     * 转换查询出的策略字段
//     * @param maplist
//     * @return
//     */
//    List<Map<String,Object>> convertStrategyeMapKey(List<Map<String, String>> maplist){
//        if(maplist==null||maplist.isEmpty()){
//            return null;
//        }
//        List<Map<String,Object>> resultMapList = new ArrayList<>();
//        for(Map<String,String> map:maplist) {
//            Map<String, Object> tmpMap = new HashMap<String, Object>();
//            for (Map.Entry entyt : map.entrySet()) {
//                if ("PARTNERID".equals(entyt.getKey())) {
//                    tmpMap.put("partnerId",(String) entyt.getValue());
//                }
//                if ("APPID".equals(entyt.getKey())) {
//                    tmpMap.put("appId",(String) entyt.getValue());
//                }
//                if ("HITNUM".equals(entyt.getKey())) {
//                    tmpMap.put("hitNum",Integer.valueOf((String) entyt.getValue()));
//                }
//                if ("EVENTTYPE".equals(entyt.getKey())) {
//                    tmpMap.put("eventType", (String) entyt.getValue());
//                }
//                if ("STRATEGYID".equals(entyt.getKey())) {
//                    tmpMap.put("strategyId",(String) entyt.getValue());
//                }
//                if ("STRATEGYNAME".equals(entyt.getKey())) {
//                    tmpMap.put("strategyName", (String) entyt.getValue());
//                }
//                if ("RISKFLOWNO".equals(entyt.getKey())) {
//                    tmpMap.put("riskFlowNo",(String) entyt.getValue());
//                }
//                if ("LOGTIME".equals(entyt.getKey())) {
//                    tmpMap.put("logTime", Long.parseLong((String) entyt.getValue()));
//                }
//                if ("STRATEGYDECISION".equals(entyt.getKey())) {
//                    tmpMap.put("strategyDecision",(String) entyt.getValue());
//                }
//                if ("STRATEGYSETNAME".equals(entyt.getKey())) {
//                    tmpMap.put("strategySetName",(String) entyt.getValue());
//                }
//                if ("STRATEGYSETID".equals(entyt.getKey())) {
//                    tmpMap.put("strategySetId", (String) entyt.getValue());
//                }
//                if ("STRATEGYSCORE".equals(entyt.getKey())) {
//                    tmpMap.put("strategyScore", Integer.parseInt((String) entyt.getValue()));
//                }
//            }
//            resultMapList.add(tmpMap);
//        }
//        return resultMapList;
//    }
//    /**
//     * 提取击中的规则
//     * @param map
//     * @return
//     */
//    public  Set<String> getHitRuleId(Map<String, String> map){
//        if(map==null||map.isEmpty()){
//            return null;
//        }
//        Set<String> ruleIdSet = new HashSet<String>();
//        for (Map.Entry entyt : map.entrySet()) {
//            //提取规则字段
//            String key = (String)entyt.getKey();
//            if(key.startsWith("RULE_")){
//                ruleIdSet.add(key.split("\\_")[1]);
//            }
//        }
//        return ruleIdSet;
//    }
//
//    /**
//     * 从查询结果中提取策略ID，如果没有表示没有命中策略
//     * @param map
//     * @return
//     */
//    public  Set<String> getHitStrategyId(Map<String, String> map){
//        if(map==null||map.isEmpty()){
//            return null;
//        }
//        Set<String> strategyIdSet = new HashSet<String>();
//        for (Map.Entry entyt : map.entrySet()) {
//            //提取规则字段
//            String key = (String)entyt.getKey();
//            if(key.startsWith("STRATAGE_")){
//                strategyIdSet.add(key.split("\\_")[1]);
//            }
//        }
//        return strategyIdSet;
//    }
//
//    /**
//     * 转换策略查询结果字段为小写
//     * @param maplist
//     * @return
//     */
//    public List<Map<String,Object>> convertStrategyMapKey(List<Map<String, String>> maplist){
//        if(maplist==null||maplist.isEmpty()){
//            return null;
//        }
//        List<Map<String,Object>> resultMapList = new ArrayList<>();
//        for(Map<String,String> map:maplist) {
//            Map<String, Object> tmpMap = new HashMap<String, Object>();
//            for (Map.Entry entyt : map.entrySet()) {
//                if ("PARTNERID".equals(entyt.getKey())) {
//                    tmpMap.put("partnerId",(String) entyt.getValue());
//                }
//                if ("APPID".equals(entyt.getKey())) {
//                    tmpMap.put("appId",(String) entyt.getValue());
//                }
//                if ("RISKFLOWNO".equals(entyt.getKey())) {
//                    tmpMap.put("riskFlowNo",(String) entyt.getValue());
//                }
//                if ("LOGTIME".equals(entyt.getKey())) {
//                    tmpMap.put("logTime", Long.parseLong((String) entyt.getValue()));
//                }
//                if ("LOGFIELD".equals(entyt.getKey())) {
//                    tmpMap.put("strategyId",(String) entyt.getValue());
//                }
//                if ("STRATEGYMODE".equals(entyt.getKey())) {
//                    tmpMap.put("strategyMode", (String) entyt.getValue());
//                }
//                if ("STRATEGYSETID".equals(entyt.getKey())) {
//                    tmpMap.put("strategyId",(String) entyt.getValue());
//                }
//                if ("STRATEGYNAME".equals(entyt.getKey())) {
//                    tmpMap.put("strategyName", (String) entyt.getValue());
//                }
//                if ("STRATEGYSETNAME".equals(entyt.getKey())) {
//                    tmpMap.put("strategySetName",(String) entyt.getValue());
//                }
//                if ("STRATEGYSETID".equals(entyt.getKey())) {
//                    tmpMap.put("strategySetId", (String) entyt.getValue());
//                }
//                if ("DECISION".equals(entyt.getKey())) {
//                    tmpMap.put("decision",(String) entyt.getValue());
//                }
//                if ("SCORE".equals(entyt.getKey())) {
//                    tmpMap.put("score", Integer.parseInt((String) entyt.getValue()));
//                }
//                if ("SCORE".equals(entyt.getKey())) {
//                    tmpMap.put("score",(String) entyt.getValue());
//                }
//                if ("RISKHITRULECOL".equals(entyt.getKey())) {
//                    tmpMap.put("riskHitRulecol",(String) entyt.getValue());
//                }
//                if ("EVENTTYPE".equals(entyt.getKey())) {
//                    tmpMap.put("eventType", (String) entyt.getValue());
//                }
//                if ("REFFLOWNO".equals(entyt.getKey())) {
//                    tmpMap.put("refFlowNo",(String) entyt.getValue());
//                }
//
//            }
//            resultMapList.add(tmpMap);
//        }
//        return resultMapList;
//    }
//
//    public String convertUpKey(String s){
//        return "PRMI_"+ s.toUpperCase();
//    }
//
//    /**
//     * 创建查询返回的列,这里只返回PK和用來排序的PRMI_OCCURTIME
//     * @return
//     */
//    public List<String> createQueryColumns(){
//        List<String> columns = Arrays.asList("PK","PRMI_OCCURTIME");
//        return columns;
//    }
//
//    /**
//     * 创建查询条件，由于查询必须按照索引的顺序组织
//     * @param conditions
//     * @return
//     */
//    public String createQueryCondition(Map<String, Object> conditions){
//
//        StringBuffer sb = new StringBuffer(PRMI_PARTNERID+"='"+conditions.get("partnerId")+"'");
//        if(conditions.containsKey("appId")){
//            sb.append(" and "+PRMI_APPID+"='"+conditions.get("appId")+"'");
//        }
//        if(conditions.containsKey("occurTimeStart")&&conditions.containsKey("occurTimeEnd")){
//            sb.append(" and "+PRMI_OCCURTIME+" between "+ conditions.get("occurTimeStart") +" and "+conditions.get("occurTimeEnd"));
//        }
//        //mobile和certNo只会传一个过来，同时传两个不能匹配，也可以一个都不传
//        if(conditions.containsKey("mobile")){
//            sb.append(" and "+PRMI_MOBILE+"='"+conditions.get("mobile")+"'");
//            return sb.toString();
//        }
//        if(conditions.containsKey("certNo")){
//            sb.append(" and "+PRMI_CERTNO+"='"+conditions.get("certNo")+"'");
//            return sb.toString();
//        }
//        return sb.toString();
//    }
//
//    /**
//     * 创建根据流水号查询的查询条件
//     * @param partnerID
//     * @param riskFlowNo
//     * @param occurTime
//     * @return
//     */
//    public String createRiskFlowNoQueryCondition(String partnerID, String riskFlowNo, long occurTime){
//        String conditon = "PRMI_PARTNERID='"+partnerID+"'"+" and PRMI_RISKFLOWNO='"+riskFlowNo+"'"+" and PRMI_OCCURTIME="+occurTime;
//        return conditon;
//    }
//    public static void main(String [] args){
//        EventQueryServiceImpl service = new EventQueryServiceImpl();
//        Map<String, Object> conditions = new HashMap<String, Object>();
//        conditions.put("appId","credit");
//        //conditions.put("eventType","应用层.txt");
//        conditions.put("occurTimeStart","1494323760000");
//        conditions.put("occurTimeEnd","1496829419999");
//        conditions.put("partnerId","demo");
//        conditions.put("certNo","dfsafdsaf3efdsafdsafdasfdsa");
//        conditions.put("mobile","18285111452");
//        String sql = service.createQueryCondition(conditions);
//        System.out.println(sql);
//
////        service.queryEvent(conditions,0,20);
////        try {
////            List<Map<String,String>> resMap=service.queryEventRowKeyFromPhoenix(conditions,0,20);
////            System.out.println();
////            service.queryEventFromHbase(resMap);
////        } catch (SQLException e) {
////            e.printStackTrace();
////        } catch (ClassNotFoundException e) {
////            e.printStackTrace();
////        }
//    }
//
//}
