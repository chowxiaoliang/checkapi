package com.zl.checkapi.phoenix;

import org.apache.hadoop.hbase.util.MD5Hash;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CommonUtils {

    public static String[] getRangeWeeks(long startMill, long endMill) {

        if (startMill > endMill) {
            return null;
        }
        DateTime start = new DateTime(startMill);
        DateTime end = new DateTime(endMill);
        List<String> list = new ArrayList<>();

        String week = null;
        do {
            week = getWeekYear(start);
            list.add(week);
            start = start.plusWeeks(1);
        } while (start.isBefore(end));

        String endWeek = getWeekYear(end);
        if (!endWeek.equals(week)) {
            list.add(getWeekYear(end));
        }
        return list.toArray(new String[]{});
    }

    public static String getWeekYear(DateTime time) {
        return time.getWeekyear() + "_" + time.getWeekOfWeekyear();
    }

    public static String md5HashRowKey(String key) throws Exception {
        return MD5Hash.getMD5AsHex(key.getBytes()).substring(0, 8) + key;
    }

    /**
     * 根据riskFlowNo和partnerId生成hbase的rowKey
     * @param partnerId
     * @param riskFlowNo
     * @return
     */
    public static String createRowKey(String partnerId,String riskFlowNo){
        String rowKey = (partnerId + "_" + riskFlowNo).toUpperCase();
        try {
            return md5HashRowKey(rowKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String createRuleOrStrategyRowKey(String partnerId,String riskFlowNo,String id){
        String rowKey = (partnerId + "_" + riskFlowNo+"_"+id).toUpperCase();
        try {
            return md5HashRowKey(rowKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 创建kafka consumer配置文件
     * @return
     */
//    public static ConsumerConfig createConsumerConfig() {
//        Properties props = new Properties();
//        // zookeeper 配置
//
//        props.put("zookeeper.connect",
//                ConfigMap.INSTANCE.getValue("zookeeper.connect"));
//
//        // group 代表一个消费组
//        props.put("group.id", ConfigMap.INSTANCE.getValue("group.id"));
//
//        // zk连接超时
//        props.put("zookeeper.session.timeout.ms",
//                ConfigMap.INSTANCE.getValue("zookeeper.session.timeout.ms"));
//        props.put("zookeeper.sync.time.ms",
//                ConfigMap.INSTANCE.getValue("zookeeper.sync.time.ms"));
//        props.put("auto.commit.interval.ms",
//                ConfigMap.INSTANCE.getValue("auto.commit.interval.ms"));
//        props.put("auto.offset.reset",
//                ConfigMap.INSTANCE.getValue("auto.offset.reset"));
//        // 序列化类
//        props.put("serializer.class",
//                ConfigMap.INSTANCE.getValue("serializer.class"));
//
//        return new ConsumerConfig(props);
//    }

    /**
     * 生成不包括"-"的UUID
     * @return
     */
    public static String getUuidString() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static void main(String[] args) {
//        try {
//            String key = createRowKey("demo","17051110161747DD67BA934A1C974B73734F78DA48");
//            System.out.println(key);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        String rowKey = ("dongbo" + "_" + "1706291443BBC5BFC052BB4146AE9A397D985D4EB9"+"_11647").toUpperCase();
        try {
            String key= md5HashRowKey(rowKey);
            System.out.println(key);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
