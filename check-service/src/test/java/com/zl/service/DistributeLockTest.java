package com.zl.service;

import threadpool.ThreadPoolUtil;

import java.util.concurrent.ThreadPoolExecutor;

public class DistributeLockTest {
    private static ThreadPoolExecutor threadPoolExecutor = ThreadPoolUtil.newCPUPool("lock");

    public static void main(String[] args){
        threadPoolExecutor.execute(()->{
            try{
                String url = "http://192.168.1.162:20881/redis/testLock";
                String lockName = "yangxiaoxiao";
                String setResult = PostUtil.sendPost(url, lockName);
                System.out.println(setResult);
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        threadPoolExecutor.execute(()->{
            try{
                String url = "http://192.168.1.162:20881/redis/testLock";
                String lockName = "yangxiaoxiao";
                String setResult = PostUtil.sendPost(url, lockName);
                System.out.println(setResult);
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }
}
