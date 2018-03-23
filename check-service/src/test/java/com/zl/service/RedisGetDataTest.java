package com.zl.service;

public class RedisGetDataTest {
    public static void main(String[] args){
        try{
            String url = "http://192.168.1.162:20881/redis/getData";
            String secondParams = "get";
            String getResult = PostUtil.sendPost(url, secondParams);
            System.out.println(getResult);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
