package com.zl.checkapi.reptile.ioutil;

import com.alibaba.fastjson.JSONObject;
import com.zl.checkapi.reptile.citypartition.FinalSingleBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WriteToText {

    private static final Logger logger = LoggerFactory.getLogger(WriteToText.class);

    /**
     * 写入文件
     * type:one, two, three...,eight
     * @param resultList
     * @param type
     */
    public static void write(List<FinalSingleBean> resultList, String type){
        try{
            String path = "C:\\Users\\lenovo\\Desktop\\finaltext\\"+type;

            FileWriter fileWriter = new FileWriter(path);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            if(resultList!=null && resultList.size()>0){
                for(int i = 0; i < resultList.size(); i ++){
                    logger.info("当前写入数据是=>{}", JSONObject.toJSONString(resultList.get(i)));
                    if(i == resultList.size()-1){
                        bufferedWriter.write(JSONObject.toJSONString(resultList.get(i)));
                    }else{
                        bufferedWriter.write(JSONObject.toJSONString(resultList.get(i)));
                        bufferedWriter.newLine();
                    }
                }
                bufferedWriter.flush();
                bufferedWriter.close();
                fileWriter.close();
            }
        }catch (IOException e){
            logger.error("数据写入失败=>{}", JSONObject.toJSONString(resultList), e);
        }
    }
}
