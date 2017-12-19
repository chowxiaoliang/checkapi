package com.zl.service;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class ZhiMaFeedBack {

    private static void httpDataBatchFeedbackTest() {
        try{
            String url = "https://api.baiqishi.com/credit/zhima/search";
            JSONObject req = new JSONObject();

            req.put("partnerId", "fengbangleasing");
            req.put("verifyKey", "dec37d4c5ea64e6c89ceb02cbbb5628a");
            req.put("linkedMerchantId", "2088621838967215");
            req.put("productId","102003");

            JSONObject extParam = new JSONObject();
            extParam.put("fileCharset", "UTF-8");
            extParam.put("records", "31020");
            extParam.put("primaryKeyColumns","order_no");
            extParam.put("bizExtParams","");
            extParam.put("file", ZhiMaFeedBack.toByteArray3("F:\\github\\checkapi\\check-service\\src\\main\\resources\\files\\芝麻返回详细数据20171128(4).json"));
            extParam.put("columns","biz_date,linked_merchant_id，user_credentials_type,user_credentials_no,user_name,order_no,scene_type,scene_desc,scene_status,create_amt,installment_due_date,overdue_amt,gmt_ovd_date,rectify_flag,memo");
            req.put("extParam", extParam);

            System.out.println(req.toJSONString());

            String resp = PostUtil.sendPost(url, req.toJSONString());

            System.out.println(resp);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }


    }


    public static byte[] toByteArray3(String filename) throws IOException {

        FileChannel fc = null;
        try {
            fc = new RandomAccessFile(filename, "r").getChannel();
            MappedByteBuffer byteBuffer = fc.map(FileChannel.MapMode.READ_ONLY, 0,fc.size()).load();
            System.out.println(byteBuffer.isLoaded());
            byte[] result = new byte[(int) fc.size()];
            if (byteBuffer.remaining() > 0) {
                // System.out.println("remain");
                byteBuffer.get(result, 0, byteBuffer.remaining());
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                fc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){

        ZhiMaFeedBack.httpDataBatchFeedbackTest();
    }
}
