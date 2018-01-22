package com.zl.checkapi.main;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.DBObject;
import com.zl.checkapi.mongodb.MongodbOperService;
import com.zl.checkapi.mysql.dao.RiskBqsDataFeedbackMapper;
import com.zl.checkapi.mysql.domain.RiskBqsDataFeedback;
import com.zl.checkapi.mysql.domain.RiskBqsDataFeedbackExample;
import com.zl.checkapi.util.Constants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 从MongoDB清洗数据到mysql(只清洗总表数据)
 */
public class ClearDataFromMongoToMysql {

    private static final Logger logger = LoggerFactory.getLogger(ClearDataFromMongoToMysql.class);

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private static final Long CALDAYS = 24*60*60*1000L;

    public static void main(String[] args){

        ApplicationContext cxt = new ClassPathXmlApplicationContext("application.xml");

        RiskBqsDataFeedbackMapper riskBqsDataFeedbackMapper = cxt.getBean(RiskBqsDataFeedbackMapper.class);
        MongodbOperService mongodbOperService = cxt.getBean("mongodbOperService", MongodbOperService.class);
        //先删除原有数据
        RiskBqsDataFeedbackExample riskBqsDataFeedbackExample = new RiskBqsDataFeedbackExample();
        RiskBqsDataFeedbackExample.Criteria criteria = riskBqsDataFeedbackExample.createCriteria();
        criteria.andPartnerIdEqualTo("qiansudai98");
        int result = riskBqsDataFeedbackMapper.deleteByExample(riskBqsDataFeedbackExample);
        if(result == 0){
            System.out.println("数据库暂无该合作方数据...");
        }else{
            System.out.println("共删除脏数据"+result+"条!");
        }
        try{
            logger.info("等待30s后开始清洗数据...");
            Thread.sleep(30000);
            Long startTime = System.currentTimeMillis();
            List<DBObject> resultList = mongodbOperService.getMongodbData();
            int size = resultList.size();
            System.out.println("本次清洗的数据大小是："+size);
            for(int i=0;i<size;i++){
                try{
                    DBObject dbObject = resultList.get(i);
                    JSONObject jsonObject = (JSONObject) JSON.toJSON(dbObject);

                    System.out.println("进行第"+(i+1)+"次清洗...");

                    String flowNo = jsonObject.getString("flowNo");
                    String partnerId = jsonObject.getString("partnerId");

                    JSONObject recordsObject = JSONObject.parseObject(jsonObject.getString("file"));
                    JSONArray recordsArray = recordsObject.getJSONArray("records");
                    System.out.println("本次清洗的records的大小是=>"+recordsArray.size());
                    for(Object object : recordsArray){
                        JSONObject resultObject = (JSONObject)JSON.toJSON(object);
                        String certNo = resultObject.getString("certNo");
                        String name = resultObject.getString("name");
                        System.out.println("该条记录的身份证是=>"+certNo+"，该条记录的姓名是=>"+name);
                        if(StringUtils.isEmpty(certNo)){
                            continue;
                        }
                        String amount = resultObject.getString("amount");
                        String bizDate = resultObject.getString("bizDate");
                        String overdueAmt = resultObject.getString("overdueAmt");
                        String gmtOvdDate = resultObject.getString("gmtOvdDate");
                        String memo = resultObject.getString("memo");
                        //以下字段数据备份到memo信息中
                        String certType = resultObject.getString("certType");
                        String orderId = resultObject.getString("orderId");
                        String eventType = resultObject.getString("eventType");
                        String eventDesc = resultObject.getString("eventDesc");
                        String eventStatus = resultObject.getString("eventStatus");
                        String installmentDueDate = resultObject.getString("installmentDueDate");
                        String rectifyFlag = resultObject.getString("rectifyFlag");

                        JSONObject bakJson = new JSONObject();
                        bakJson.put("memo", memo);
                        bakJson.put("certType", certType);
                        bakJson.put("orderId", orderId);
                        bakJson.put("eventType", eventType);
                        bakJson.put("eventDesc", eventDesc);
                        bakJson.put("eventStatus", eventStatus);
                        bakJson.put("installmentDueDate", installmentDueDate);
                        bakJson.put("rectifyFlag", rectifyFlag);

                        //逐条插入到数据库
                        RiskBqsDataFeedback riskBqsDataFeedback = new RiskBqsDataFeedback();
                        riskBqsDataFeedback.setRiskFlowNo(flowNo);
                        riskBqsDataFeedback.setPartnerId(partnerId);
                        riskBqsDataFeedback.setCertNo(certNo);
                        riskBqsDataFeedback.setName(name);
                        riskBqsDataFeedback.setAmount(amount);
                        riskBqsDataFeedback.setBizDate(bizDate);
                        riskBqsDataFeedback.setOverDueAmt(overdueAmt);
                        riskBqsDataFeedback.setGmtOvdDate(gmtOvdDate);
                        riskBqsDataFeedback.setMemo(JSONObject.toJSONString(bakJson));
                        riskBqsDataFeedback.setReviewStatus(Constants.FeedBackReviewStatus.UNREVIEWED);
                        riskBqsDataFeedback.setUploadDataSource(Constants.FeedBackReviewStatus.FEEDBACK_UNLINE);
                        //计算过期时间
                        if(StringUtils.isNotEmpty(bizDate) && StringUtils.isNotEmpty(gmtOvdDate)){
                            try{
                                Date starDate = simpleDateFormat.parse(gmtOvdDate.replaceAll("/", "-"));
                                Date endDate = simpleDateFormat.parse(bizDate.replaceAll("/", "-"));
                                long subTime = endDate.getTime()-starDate.getTime();
                                if(subTime>0){
                                    long days = subTime/CALDAYS;
                                    System.out.println("计算所得的过期天数是=>"+days);
                                    riskBqsDataFeedback.setGmtOvdDays(String.valueOf(days));
                                    riskBqsDataFeedback.setOvdDaysCalways("02");
                                }
                            }catch (Exception e){
                                logger.error("过期天数计算异常=>{},{}", bizDate, gmtOvdDate);
                            }
                        }
                        RiskBqsDataFeedbackExample subRiskBqsDataFeedbackExample = new RiskBqsDataFeedbackExample();
                        RiskBqsDataFeedbackExample.Criteria subCriteria = subRiskBqsDataFeedbackExample.createCriteria();
                        subCriteria.andRiskFlowNoEqualTo(flowNo);
                        subCriteria.andPartnerIdEqualTo(partnerId);
                        subCriteria.andCertNoEqualTo(certNo);
                        subCriteria.andNameEqualTo(name);
                        List<RiskBqsDataFeedback> existList = riskBqsDataFeedbackMapper.selectByExample(subRiskBqsDataFeedbackExample);
                        if(existList!=null && existList.size()>0){
                            continue;
                        }
                        riskBqsDataFeedbackMapper.insertSelective(riskBqsDataFeedback);
                    }
                    System.out.println("第"+(i+1)+"次清洗结束...");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            Long endTime = System.currentTimeMillis();
            logger.info("清洗数据耗时：{}ms", endTime-startTime);
        }catch (Exception e){
            e.printStackTrace();
            System.exit(0);
        }
        logger.info("数据清洗成功！");
        System.exit(0);
    }
}
