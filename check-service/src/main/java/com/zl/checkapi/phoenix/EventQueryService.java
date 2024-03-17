//package com.zl.checkapi.phoenix;
//
//import com.bqs.risk.datamarket.hbaseapi.entity.ReqResult;
//
//import java.util.List;
//import java.util.Map;
//
///**
// * Created by zhaochenxi on 2017/6/9.
// * 事件历史查询接口
// */
//public interface EventQueryService {
//    public ReqResult<List<Map<String,Object>>> queryEvent(Map<String, Object> conditions, int limit, int offset);
//    public ReqResult<Map<String,Object>> queryRiskEventsByRiskFlowNo(String partnerId, String riskFlowNo);
//    public ReqResult<List<Map<String,Object>>> riskHitRuleListByRiskFlowNo(String partnerId, String riskFlowNo);
//    public ReqResult<List<Map<String,Object>>> riskHitStrategyListByRiskFlowNo(String partnerId, String riskFlowNo);
//}
