//package com.zl.checkapi.phoenix;
//
//import com.bqs.risk.datamarket.hbaseapi.entity.Partner;
//import com.bqs.risk.datamarket.hbaseapi.entity.Response;
//import com.bqs.risk.datamarket.hbaseapi.service.HbaseUtilService;
//import org.apache.commons.lang3.StringUtils;
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.hbase.HBaseConfiguration;
//import org.apache.hadoop.hbase.KeyValue;
//import org.apache.hadoop.hbase.client.Get;
//import org.apache.hadoop.hbase.client.HTableInterface;
//import org.apache.hadoop.hbase.client.HTablePool;
//import org.apache.hadoop.hbase.client.Result;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * Created by zhaochenxi on 2017/6/8.
// */
//public class HbaseUtilEventServiceImpl implements HbaseUtilService {
//
//    private static Configuration configuration = null;
//
//    private static Logger LOG = LoggerFactory.getLogger(HbaseUtilEventServiceImpl.class);
//
//    static {
//        configuration = HBaseConfiguration.create();
//        // configuration.setLong(HConstants.HBASE_REGIONSERVER_LEASE_PERIOD_KEY,
//        // 120000);
//        File workaround = new File(".");
//        System.getProperties().put("hadoop.home.dir",
//                workaround.getAbsolutePath());
//        new File("./bin").mkdirs();
//        try {
//            new File("./bin/winutils.exe").createNewFile();
//        } catch (IOException e) {
//            LOG.error("执行 winutils.exe失败", e);
//        }
//        // init config files
//        ConfigMap.INSTANCE.init();
//    }
//
//    private static HTablePool pool = new HTablePool(configuration, 1000);
//
//    @Override
//    public Response queryByRowKey(String tableName, String rowKey) {
//        long time1 = System.currentTimeMillis();
//        Response response = new Response();
//
//        //1.参数有效性校验
//        if (tableName == null || StringUtils.isBlank(tableName)) {
//            response.setQueryTypeDesc("query error,tableName is null");
//            response.setQueryCode("-1");
//            return response;
//        }
//        if (rowKey == null || StringUtils.isBlank(rowKey)) {
//            response.setQueryTypeDesc("query error,rowKey is null");
//            response.setQueryCode("-1");
//            return response;
//        }
//
//        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
////        LOG.info("query begin,rowKey="+rowKey+",table="+tableName);
//
//
//        if (tableName == null || StringUtils.isBlank(tableName)) {
//            response.setQueryTypeDesc("query error,table isn't exist in config file");
//            response.setQueryCode("-1");
//            return response;
//        }
//
//        HTableInterface htable = pool.getTable(tableName);
//
//        Map<String,String> map = new HashMap<String, String>();
//        Get get = new Get(rowKey.getBytes());
//        get.addFamily("INFO".getBytes());
//        try {
//            Result row = htable.get(get);
//            if (row.size() == 0 && row.isEmpty()) {
//                response.setQueryTypeDesc("query result is null");
//                response.setQueryCode("-1");
//                return response;
//            }else{
//                for (KeyValue kv : row.raw()) {
//                    String key = new String(kv.getQualifier());
//                    String value = new String(kv.getValue());
//                    map.put(key,value);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally {
//            try {
//                htable.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        if (map != null && map.size() > 0) {
//            list.add(map);
////            LOG.info("query (rowKey,tableName)="+HeplerUtils.genKey(rowKey, tableName)+",result="+ JSON.toJSON(list));
//            response.setData(list);
//            response.setQueryTypeDesc("success");
//            response.setQueryCode("1");
//        } else {
//            LOG.info("result is empty,tableName="+tableName+",rowKey="+rowKey);
//            response.setQueryTypeDesc("query result is empty");
//            response.setQueryCode("-1");
//        }
//        long time2 = System.currentTimeMillis();
//        LOG.info("==>queryByRowKey End.Total time used:" + (time2 - time1) / 1000000);
////        LOG.info("response="+ JSON.toJSON(response));
//        return response;
//    }
//
//    public Response queryByColumn(String tableName, String culumn){
//
//        return null;
//    }
//    @Override
//    public Response queryOrderByRowKey(Long aLong, Long aLong1, String s) {
//        return null;
//    }
//
//    @Override
//    public Response queryByRowKey(String s, String s1, String s2) {
//        return null;
//    }
//
//    @Override
//    public Response upsertPartner(String flowNo, Partner partner) {
//        return null;
//    }
//
//    @Override
//    public String insertData(String tableName, String columnFamily, String rowKey, String json, String prefix) throws Exception {
//        return null;
//    }
//}
