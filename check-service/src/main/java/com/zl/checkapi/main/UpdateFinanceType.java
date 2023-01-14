//package com.zl.checkapi.main;
//
//import com.alibaba.fastjson.JSONObject;
//import com.bqs.risk.datamarket.hbaseapi.entity.Partner;
//import com.bqs.risk.datamarket.hbaseapi.entity.Response;
//import com.bqs.risk.datamarket.hbaseapi.service.HbaseUtilService;
//import com.zl.checkapi.mysql.dao.UsrPartnerMapper;
//import com.zl.checkapi.mysql.domain.UsrPartner;
//import com.zl.checkapi.mysql.domain.UsrPartnerExample;
//import com.zl.checkapi.util.ExcelUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.util.StringUtils;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.UUID;
//import java.util.concurrent.TimeUnit;
//
///**
// * @author zhouliang
// * @since 2018/4/9
// * @desc 合作方金融类别更新
// */
//public class UpdateFinanceType {
//    private final static Logger logger = LoggerFactory.getLogger(UpdateFinanceType.class);
//
//    //获取打印日志
//
//    static String getCommonLog(String partnerId) {
//        JSONObject jso = new JSONObject();
//        jso.put("notifyId", UUID.randomUUID().toString().replace("-", ""));
//        jso.put("userEmail", "super@baiqishi.com");
//        jso.put("partnerId", partnerId);
//        return jso.toJSONString();
//    }
//
//    public static void main(String[] args){
//        String filePath = "/app/partner_finance.xlsx";
////        String filePath = "C:\\Users\\lenovo\\Desktop\\partner_finance(1).xlsx";
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.xml");
//        Map<String, Integer> countMap = new HashMap<>(16);
//        int count = 0;
//        int successCount = 0;
//        int failCount = 0;
//        countMap.put("success", count);
//        countMap.put("fail", count);
//        logger.info("spring容器已经启动， 等待15s后开始清洗数据");
//
//        UsrPartnerMapper usrPartnerMapper = applicationContext.getBean(UsrPartnerMapper.class);
//        HbaseUtilService hbaseUtilService = applicationContext.getBean("hbaseUtilService", HbaseUtilService.class);
//        try{
//            TimeUnit.SECONDS.sleep(15);
//        }catch (InterruptedException e){
//            e.printStackTrace();
//        }
//        //解析excel
//        Map<String, List<Map<String, String>>> map = new HashMap<>(16);
//        try {
//            map = ExcelUtils.readExcel(filePath);
//        } catch (Exception e) {
//            e.printStackTrace();
//            logger.error("Excel解析异常", e);
//        }
//        assert map != null;
//        List<Map<String, String>> list = map.get("Sheet1");
//
//        if(list!=null && list.size()>0){
//            logger.info("本次更新的数据量大小是=>{}", list.size());
//            for(int i=1;i<list.size();i++){
//                try{
//                    logger.info("开始清洗第{}条数据", i);
//                    Map<String, String> innerMap = list.get(i);
//                    String partnerId = innerMap.get("COLUMN_0");
//                    String financeType = innerMap.get("COLUMN_6");
//                    if(StringUtils.isEmpty(partnerId) || StringUtils.isEmpty(financeType)){
//                        logger.info("跳过该条记录=>partnerId=>{},financtType=>{}", partnerId, financeType);
//                        continue;
//                    }
//                    UsrPartnerExample usrPartnerExample = new UsrPartnerExample();
//                    UsrPartnerExample.Criteria criteria = usrPartnerExample.createCriteria();
//                    criteria.andPartnerIdEqualTo(partnerId);
//
//                    //记录
//                    List<UsrPartner> resultList = usrPartnerMapper.selectByExample(usrPartnerExample);
//
//                    UsrPartner usrPartner = new UsrPartner();
//                    usrPartner.setFinanceType(financeType);
//                    int result = usrPartnerMapper.updateByExampleSelective(usrPartner, usrPartnerExample);
//                    if(result>0){
//                        logger.info("第{}条记录更新成功=>partnerId={}", i, partnerId);
//                    }
//
//                    //通知hbase(先得到四要素)
//                    if(resultList!=null && resultList.size()>0){
//                        UsrPartner usrPartner1 = resultList.get(0);
//                        String partnerName = usrPartner1.getPartnerName();
//                        String operationId = usrPartner1.getOperationId();
//                        int partnerStatus = usrPartner1.getPartnerStatus();
//                        int status = usrPartner1.getStatus();
//
//                        Partner partner = new Partner(partnerId, operationId, String.valueOf(partnerStatus), String.valueOf(status), partnerName, financeType, null);
//                        try{
//                            if(hbaseUtilService==null){
//                                hbaseUtilService = applicationContext.getBean("hbaseUtilService", HbaseUtilService.class);
//                            }
//                            TimeUnit.MILLISECONDS.sleep(100);
//                            Response response = hbaseUtilService.upsertPartner(UpdateFinanceType.getCommonLog(partnerId), partner);
//                            if("1".equals(response.getQueryCode())){
//                                successCount ++;
//                                countMap.put("success", successCount);
//                                logger.info("数据更新通知Hbase成功=>partnerId={}", partnerId);
//                            }else{
//                                failCount ++;
//                                countMap.put("fail", failCount);
//                                logger.info("数据更新通知Hbase失败=>partnerId={}", partnerId);
//                            }
//                        }catch (Exception e){
//                            logger.error("数据更新通知Hbase异常partnerId=>{}", partnerId, e);
//                        }
//
//                    }
//                }catch (Exception e){
//                    logger.error("清洗数据异常", e);
//                }
//            }
//        }
//        logger.info(JSONObject.toJSONString(successCount));
//        logger.info(JSONObject.toJSONString(failCount));
//        logger.info("数据清洗结束,正退出...");
//        System.exit(0);
//    }
//}
