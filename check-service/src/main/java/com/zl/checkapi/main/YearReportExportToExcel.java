package com.zl.checkapi.main;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.bqs.risk.datamarket.hbaseapi.entity.Response;
import com.bqs.risk.datamarket.hbaseapi.service.HbaseUtilService;
import com.zl.checkapi.mysql.dao.DictItemMapper;
import com.zl.checkapi.mysql.domain.DictItem;
import com.zl.checkapi.mysql.domain.DictItemExample;
import com.zl.checkapi.util.ExcelUtils;
import com.zl.checkapi.util.SpringContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author zhouliang
 * @desc 华瑞银行2017.3-2018.3 部分数据导出为Excel
 */
public class YearReportExportToExcel {

    private final static Logger LOG = LoggerFactory.getLogger(YearReportExportToExcel.class);

    private final static String PARTNER_ID = "shrbank";

    private final static String TABLE = "PR_PARTNERINFO";

    private final static String FILE_PATH = "/app/shrbank_result.xlsx";

    private final static List<String> TIME_RANGE = Arrays.asList("2017-3", "2017-4", "2017-5",

            "2017-6", "2017-7", "2017-8", "2017-9", "2017-10", "2017-11", "2017-12", "2018-1", "2018-2", "2018-3");

    public static void main(String[] args){

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.xml");

        HbaseUtilService hbaseUtilService = SpringContext.getBean("hbaseUtilService", HbaseUtilService.class);

        DictItemMapper dictItemMapper = SpringContext.getBean("dictItemMapper", DictItemMapper.class);

        LOG.info("spring容器已经启动，等待15s后开始导数据...");
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //最后存储的数据
        Map<String, List<Map<String, String>>> resultMap = new HashMap<>(16);
        //里面同步存储
        List<Map<String, String>> innerList = Collections.synchronizedList(new ArrayList<>(16));

        //事件活动时段统计
        try{
            //第一行
            innerList.add(new HashMap<String, String>(1){{put("COLUMN_0", "事件活动时段统计");}});
            //第二行
            innerList.add(new HashMap<String, String>(16){{
                put("COLUMN_0", "");
                for(int i=0;i<24;i++){
                    put("COLUMN_"+(i+1), ""+i);
                }
            }});
            //获取所有事件类型
            DictItemExample dictItemExample = new DictItemExample();
            DictItemExample.Criteria criteria = dictItemExample.createCriteria();
            criteria.andEntryCodeEqualTo("event_type");
            List<DictItem> list = dictItemMapper.selectByExample(dictItemExample);
            if(list!=null && list.size()>0){
                for(int i=0;i<list.size();i++){
                    //所有的事件
                    String eventType = list.get(i).getItemCode();
                    String eventTypeDisName = list.get(i).getItemValue();
                    //每个事件12个月的数据汇总
                    Map<Integer, Integer> resultPerEventType = new HashMap<>(16);
                    for(int k=1;k<13;k++){
                        resultPerEventType.put(k, 0);
                    }
                    //对每个事件求对应12个月的数据和
                    for(String string : TIME_RANGE){
                        String[] str = string.split("-");
                        String year = str[0];
                        String month = str[1];
                        String rowKey = PARTNER_ID + "_" + eventType + "_" + year + "_" + month;
                        Response response = hbaseUtilService.queryByRowKey(TABLE, rowKey);
                        if(response.getData()!=null && response.getData().size()>0){
                            Map<String, String> map = response.getData().get(0);
                            String resultStr = map.get("INFO:HOUR_COUNTS");
                            if(StringUtils.isNotEmpty(resultStr)){
                                //分割得到各个小时的数据 （结构为：时_数量）
                                String[] strs = resultStr.split(",");
                                for(String entryStr : strs){
                                    String[] finalStrs = entryStr.split("_");
                                    String hour = finalStrs[0];
                                    String num = finalStrs[1];
                                    resultPerEventType.put(Integer.valueOf(hour), (resultPerEventType.get(Integer.valueOf(hour))!=null?resultPerEventType.get(Integer.valueOf(hour)):0) + Integer.valueOf(num));
                                }
                            }
                        }
                    }
                    //其他行
                    //按事件分行写到Excel
                    innerList.add(new HashMap<String, String>(16){{
                        LOG.info("事件活动时段统计的标题是=>{}", eventTypeDisName);
                        LOG.info("事件活动时段统计的数据是=>{}", JSONObject.toJSONString(resultPerEventType));
                        put("COLUMN_0", eventTypeDisName);
                        for(int i=0;i<24;i++){
                            Integer num = resultPerEventType.get(i);
                            if(null == num){
                                num = 0;
                            }
                            put("COLUMN_"+(i+1), String.valueOf(num));
                        }
                    }});
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            LOG.error("事件活动时段统计获取失败", e);
        }

        //事件属性关联阈值分布(全量数据：2018.3-2017.2即可)
        try{
            //第一行
            innerList.add(new HashMap<String, String>(1){{put("COLUMN_0", "事件属性关联阈值分布");}});

            String rowKeyOne = PARTNER_ID +"_" + "INFORELATION_" + 2018 + "_" + 3;
            String rowKeyTwo = PARTNER_ID +"_" + "INFORELATION_" + 2017 + "_" + 2;
            Response responseOne = hbaseUtilService.queryByRowKey(TABLE, rowKeyOne);
            Response responseTwo = hbaseUtilService.queryByRowKey(TABLE, rowKeyTwo);
            //resultMapOne数据的集合大于resultMapTwo
            Map<String, String> resultMapOne = getMapValue(responseOne);
            Map<String, String> resultMapTwo = getMapValue(responseTwo);
            //最终的结果集
            Map<String, Map<Integer, Integer>> finalMap = new HashMap<>(16);
            //遍历封装数据
            if(resultMapOne.size()>0 && resultMapTwo.size()>0){
                for(Map.Entry<String, String> entry : resultMapOne.entrySet()){
                    String relationNameOne = entry.getKey();
                    String relationValOne = entry.getValue();
                    for(Map.Entry<String, String> entry1 : resultMapTwo.entrySet()){
                        String relationNameTwo = entry1.getKey();
                        String relationValTwo = entry1.getValue();
                        if(relationNameOne.equals(relationNameTwo)){
                            String title = getRelationDisName(relationNameOne);
                            LOG.info("事件属性关联阈值分布要封装的标题是=>{}", title);
                            //处理取出来的数据，eg：2_33代表同一个手机号关联两个设备的计数有33个
                            calRelationNum(title, relationValOne, relationValTwo, finalMap);
                        }
                    }
                }
            }
            LOG.info("事件属性关联阈值分布最终得到的数据是=>{}", JSONObject.toJSONString(finalMap));
            //第二行以及其他行(转成的数据的格式是：设备_IP 2/3 4/2 等)
            for(Map.Entry<String, Map<Integer, Integer>> entry : finalMap.entrySet()){
                innerList.add(new HashMap<String, String>(16){{
                    LOG.info("关联的类型是=>"+entry.getKey());
                    put("COLUMN_0", entry.getKey());
                    int i = 1;
                    for(Map.Entry<Integer, Integer> entry1 : entry.getValue().entrySet()){
                        put("COLUMN_"+i, entry1.getKey()+"/"+entry1.getValue());
                        i++;
                    }
                }});
            }
        }catch (Exception e){
            e.printStackTrace();
            LOG.error("设备信息top20排行获取失败", e);
        }

        //事件属性Top20统计
        try{
            //特定的表
            String tableName = "U_RANKHISTORY_INFO";
            //第一行
            innerList.add(new HashMap<String, String>(1){{put("COLUMN_0", "事件属性Top20统计");}});
            //存储最终的结果
            Map<String, Map<String, String>> finalResult = new HashMap<>(16);
            //每个月的数据都需要加起来
            for(String time : TIME_RANGE){
                String year = time.split("-")[0];
                String month = time.split("-")[1];
                String lastDayOfMonth = getLastDayOfMonth(Integer.valueOf(year), Integer.valueOf(month));
                String rowKey = PARTNER_ID + "_30D_" + lastDayOfMonth + "_ALL_ALL";
                Response response = hbaseUtilService.queryByRowKey(tableName, rowKey);
                if(response.getData()!=null && response.getData().size()>0){
                    Map<String, String> map = response.getData().get(0);
                    String ipTitle = "INFO:IP";
                    String devTitle = "INFO:DEV";
                    String idTitle = "INFO:ID";
                    String mobileTitle = "INFO:USERMOBILENO";
                    //获取四个方面的数据
                    String ipVal = map.get(ipTitle);
                    String devVal = map.get(devTitle);
                    String idVal = map.get(idTitle);
                    String mobileVal = map.get(mobileTitle);
                    //累加数据
                    calFinalEventTop(getEventTopDisName(ipTitle), ipVal, finalResult);
                    calFinalEventTop(getEventTopDisName(devTitle), devVal, finalResult);
                    calFinalEventTop(getEventTopDisName(idTitle), idVal, finalResult);
                    calFinalEventTop(getEventTopDisName(mobileTitle), mobileVal, finalResult);
                }
            }
            LOG.info("事件属性top20最终的到的数据是=>{}", JSONObject.toJSONString(finalResult));
            //降序排列
            Comparator<CommonBean> comparator = ((p1, p2) -> p2.getCount().compareTo(p1.getCount()));
            //写数据，第二行以及其他行
            for(Map.Entry<String, Map<String, String>> entry : finalResult.entrySet()){
                LOG.info("事件属性top20写入的标题是=>{}", entry.getKey());
                LOG.info("事件属性top20写入的数据是=>{}", entry.getValue());
                innerList.add(new HashMap<String, String>(16){{
                    put("COLUMN_0", entry.getKey());
                }});
                //找出top20
                List<CommonBean> list = new ArrayList<>();
                for(Map.Entry<String, String> entry1 : entry.getValue().entrySet()){
                    CommonBean eventTop = new CommonBean();
                    eventTop.setName(entry1.getKey());
                    eventTop.setCount(Integer.valueOf(entry1.getValue()));
                    list.add(eventTop);
                }
                list.sort(comparator);
                //获取前20个
                for(int j=0;j<20;j++){
                    int finalJ = j;
                    innerList.add(new HashMap<String, String>(16){{
                        if(list.get(finalJ)!=null){
                            put("COLUMN_0", list.get(finalJ).getName());
                            put("COLUMN_1", String.valueOf(list.get(finalJ).getCount()));
                        }
                    }});
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            LOG.error("星网关联统计获取失败", e);
        }

        //星网关联统计
        try{
            String rowKeyOne = PARTNER_ID +"_" + "STARTSNETWORK_" + 2018 + "_" + 3 + "_COUNTS";
            String rowKeyTwo = PARTNER_ID +"_" + "STARTSNETWORK_" + 2017 + "_" + 9 + "_COUNTS";
            Response responseOne = hbaseUtilService.queryByRowKey(TABLE, rowKeyOne);
            Response responseTwo = hbaseUtilService.queryByRowKey(TABLE, rowKeyTwo);
            Map<String, String> resultMapOne = new HashMap<>(16);
            Map<String, String> resultMapTwo = new HashMap<>(16);
            if(responseOne.getData()!=null && responseOne.getData().size()>0){
                Map<String, String> map = responseOne.getData().get(0);
                for(Map.Entry<String, String> entry : map.entrySet()){
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            LOG.error("星网关联统计获取失败", e);
        }

        //归属地信息分布(身份证和手机号归属地)
        try {
            //第一行
            innerList.add(new HashMap<String, String>(1){{put("COLUMN_0", "归属地信息分布");}});
            //2018.3数据加上2017.9数据
            String rowKeyOne = PARTNER_ID +"_" + "INFOLOCATION_" + 2018 + "_" + 3 ;
            String rowKeyTwo = PARTNER_ID +"_" + "INFOLOCATION_" + 2017 + "_" + 9 ;
            Response responseOne = hbaseUtilService.queryByRowKey(TABLE, rowKeyOne);
            Response responseTwo = hbaseUtilService.queryByRowKey(TABLE, rowKeyTwo);
            //身份证
            Map<String, Integer> innerMapOne = new HashMap<>(16);
            //手机号
            Map<String, Integer> innerMapTwo = new HashMap<>(16);
            //2018.3数据
            if(responseOne.getData()!=null && responseOne.getData().size()>0){
                Map<String, String> map = responseOne.getData().get(0);
                for(Map.Entry<String, String> entry : map.entrySet()){
                    //身份证
                    if(entry.getKey().contains("CERTNOPROVINCE")){
                        String val = entry.getValue();
                        if(StringUtils.isNotEmpty(val)){
                            String[] strs = val.split(",");
                            for(String str : strs){
                                innerMapOne.put(str.split("_")[0], Integer.valueOf(str.split("_")[1]));
                            }
                        }
                    }
                    //手机号
                    if(entry.getKey().contains("MOBILEPROVINCE")){
                        String val = entry.getValue();
                        if(StringUtils.isNotEmpty(val)){
                            String[] strs = val.split(",");
                            for(String str : strs){
                                innerMapTwo.put(str.split("_")[0], Integer.valueOf(str.split("_")[1]));
                            }
                        }
                    }
                }
            }
            //2017.9数据
            if(responseTwo.getData()!=null && responseTwo.getData().size()>0){
                Map<String, String> map = responseTwo.getData().get(0);
                for(Map.Entry<String, String> entry : map.entrySet()){
                    //身份证
                    if(entry.getKey().contains("CERTNOPROVINCE")){
                        String val = entry.getValue();
                        if(StringUtils.isNotEmpty(val)){
                            String[] strs = val.split(",");
                            for(String str : strs){
                                if(innerMapOne.get(str.split("_")[0])!=null){
                                    innerMapOne.put(str.split("_")[0], innerMapOne.get(str.split("_")[0])+Integer.valueOf(str.split("_")[1]));
                                }else{
                                    innerMapOne.put(str.split("_")[0], 0);
                                }
                            }
                        }
                    }
                    //手机号
                    if(entry.getKey().contains("MOBILEPROVINCE")){
                        String val = entry.getValue();
                        if(StringUtils.isNotEmpty(val)){
                            String[] strs = val.split(",");
                            for(String str : strs){
                                if(innerMapTwo.get(str.split("_")[0])==null){
                                    innerMapTwo.put(str.split("_")[0], 0);
                                }else{
                                    innerMapTwo.put(str.split("_")[0], innerMapTwo.get(str.split("_")[0])+Integer.valueOf(str.split("_")[1]));
                                }
                            }
                        }
                    }
                }
            }
            //其他行写数据
            //降序排列
            Comparator<CommonBean> comparator = ((p1, p2) -> p2.getCount().compareTo(p1.getCount()));
            //写身份证归属地信息
            innerList.add(new HashMap<String, String>(1){{put("COLUMN_0", "身份证归属地分布");}});
            List<CommonBean> certNoList = new ArrayList<>();
            for(Map.Entry<String, Integer> entry : innerMapOne.entrySet()){
                CommonBean commonBean = new CommonBean();
                commonBean.setName(entry.getKey());
                commonBean.setCount(entry.getValue());
                certNoList.add(commonBean);
            }
            LOG.info("身份证归属地最终的到的数据是=>{}", JSONObject.toJSONString(certNoList));
            certNoList.sort(comparator);
            for(int i=0;i<certNoList.size();i++){
                int finalI = i;
                innerList.add(new HashMap<String, String>(2){{
                    put("COLUMN_0", certNoList.get(finalI).getName());
                    put("COLUMN_1", String.valueOf(certNoList.get(finalI).getCount()));
                }});
            }
            //写手机号归属地信息
            innerList.add(new HashMap<String, String>(1){{put("COLUMN_0", "手机号归属地分布");}});
            List<CommonBean> mobileList = new ArrayList<>();
            for(Map.Entry<String, Integer> entryA : innerMapTwo.entrySet()){
                CommonBean commonBean = new CommonBean();
                commonBean.setCount(entryA.getValue());
                commonBean.setName(entryA.getKey());
                mobileList.add(commonBean);
            }
            LOG.info("手机号归属地最终得到的数据是=>{}", JSONObject.toJSONString(mobileList));
            mobileList.sort(comparator);
            for(int i=0;i<mobileList.size();i++){
                int finalI = i;
                innerList.add(new HashMap<String, String>(2){{
                    put("COLUMN_1", String.valueOf(mobileList.get(finalI).getCount()));
                    put("COLUMN_0", mobileList.get(finalI).getName());
                }});
            }
        }catch (Exception e){
            e.printStackTrace();
            LOG.error("身份证归属地信息获取失败", e);
        }

        try {
            resultMap.put("Sheet1", innerList);
            ExcelUtils.writeExcel(resultMap, FILE_PATH);
            LOG.info("Excel文件成功...即将退出系统");
            System.exit(0);
        } catch (Exception e) {
            LOG.error("生成Excel文件失败");
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * 计算事件属性top20最终的结果值
     * @param data
     * @param finalResult
     */
    private static void calFinalEventTop(String disName, String data, Map<String, Map<String, String>> finalResult){
        if(StringUtils.isNotEmpty(data)){
            Map<String, String> innerMap = finalResult.get(disName);
            if(innerMap==null){
                innerMap = new HashMap<>(16);
            }
            String[] strs = data.split(",");
            for(String str : strs){
                String name = str.split("_")[0];
                String count = str.split("_")[1];
                innerMap.put(name, innerMap.get(name)!=null?String.valueOf(Integer.valueOf(count)+Integer.valueOf(innerMap.get(name))):count);
            }
            finalResult.put(disName, innerMap);
        }
    }

    /**
     * 事件属性top20获取相关别名
     * @param eventTopName
     * @return
     */
    private static String getEventTopDisName(String eventTopName){
        String resultData = "";
        switch (eventTopName){
            case "INFO:DEV":
                resultData = "设备号top20";
                break;
            case "INFO:ID":
                resultData = "身份证号top20";
                break;
            case "INFO:IP":
                resultData = "IPtop20";
                break;
            case "INFO:USERMOBILENO":
                resultData = "手机号top20";
                break;
            default:
                break;
        }
        return resultData;
    }

    /**
     * 事件属性关联阈值分布解析最终的结果
     * @param title
     * @param relationValOne
     * @param relationValTwo
     * @param finalMap
     */
    private static void calRelationNum(String title, String relationValOne, String relationValTwo, Map<String, Map<Integer, Integer>> finalMap){
        if(StringUtils.isNotEmpty(relationValOne) && StringUtils.isNotEmpty(relationValTwo)){
            String[] strOnes = relationValOne.split(",");
            String[] strTwos = relationValTwo.split(",");
            Map<Integer, Integer> mapOne = new HashMap<>(16);
            Map<Integer, Integer> mapTwo = new HashMap<>(16);
            for(String str : strOnes){
                String[] innerStr = str.split("_");
                mapOne.put(Integer.valueOf(innerStr[0]), Integer.valueOf(innerStr[1]));
            }
            for(String str : strTwos){
                String[] innerStr = str.split("_");
                mapTwo.put(Integer.valueOf(innerStr[0]), Integer.valueOf(innerStr[1]));
            }
            //数据全部写回到第一个map中(第一个中数据永远比第二个多)
            for(Map.Entry<Integer, Integer> entry : mapOne.entrySet()){
                int keyOne = entry.getKey();
                int valOne = entry.getValue();
                for(Map.Entry<Integer, Integer> entry1 : mapTwo.entrySet()){
                    int keyTwo = entry1.getKey();
                    int valTwo = entry1.getValue();
                    if(keyOne==keyTwo){
                        mapOne.put(keyOne, valOne-valTwo);
                    }
                }
            }
            finalMap.put(title, mapOne);
        }
    }

    /**
     * 事件属性关联阈值分布解析得到的数据
     * @param response
     * @return
     */
    private static Map<String, String> getMapValue(Response response){
        Map<String, String> resultMap = new HashMap<>(16);
        if(response.getData()!=null && response.getData().size()>0){
            Map<String, String> map = response.getData().get(0);
            for(Map.Entry<String, String> entry : map.entrySet()){
                String relationName = entry.getKey();
                String relationVal = entry.getValue();
                resultMap.put(relationName, relationVal);
            }
        }
        return resultMap;
    }

    /**
     * 事件属性关联阈值分布解析关联名称
     * @param relation
     * @return
     */
    private static String getRelationDisName(String relation){
        String resultData = "";
        switch (relation){
            case "INFO:DEVICE_IP_RELATIONNUM":
                resultData = "设备_IP";
                break;
            case "INFO:DEVICE_CERTNO_RELATIONNUM":
                resultData = "设备_身份证";
                break;
            case "INFO:DEVICE_MOBILE_RELATIONNUM":
                resultData = "设备_手机号";
                break;
            case "INFO:MOBILE_IP_RELATIONNUM":
                resultData = "手机号_IP";
                break;
            case "INFO:MOBILE_CERTNO_RELATIONNUM":
                resultData = "手机号_身份证";
                break;
            case "INFO:MOBILE_DEVICE_RELATIONNUM":
                resultData = "手机号_设备";
                break;
            case "INFO:CERTNO_DEVICE_RELATIONNUM":
                resultData = "身份证_设备";
                break;
            case "INFO:CERTNO_IP_RELATIONNUM":
                resultData = "身份证_IP";
                break;
            case "INFO:CERTNO_MOBILE_RELATIONNUM":
                resultData = "身份证_手机号";
                break;
            default:
                break;
        }
        return resultData;
    }

    /**
     * 根据年月获取某个月的最后一天
     * @param year
     * @param month
     * @return
     */
    private static String getLastDayOfMonth(int year,int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR,year);
        //设置月份
        cal.set(Calendar.MONTH, month-1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }

    /**
     * 公共bean
     */
    static class CommonBean{

        private String name;

        private Integer count;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }
    }

}
