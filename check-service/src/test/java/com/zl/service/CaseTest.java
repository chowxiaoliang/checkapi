package com.zl.service;

/**
 * Created by lenovo on 2017/9/11.
 */
public class CaseTest {

    public static void main(String[] args){
        try{
//            String url = "http://192.168.1.203:20882/case/getCase";
            String url = "http://192.168.1.162:20881/case/getCase";
            String params = "new Test";
            String result = PostUtil.sendPost(url, params);
            System.out.println(result);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
