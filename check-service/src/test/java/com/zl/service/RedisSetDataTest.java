package com.zl.service;

public class RedisSetDataTest {
    public static void main(String[] args){
        try{
            String url = "http://192.168.1.162:20881/redis/getData";
            String firstParams = "set";
            String setResult = PostUtil.sendPost(url, firstParams);
            System.out.println(setResult);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
