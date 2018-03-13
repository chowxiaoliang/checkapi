package com.zl.checkapi.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhouliang on 2017/9/11.
 */
public class CheckapiMain {

    private static Logger LOG;

    static{
        LOG = LoggerFactory.getLogger(CheckapiMain.class);
    }
    public static void main(String[] args){
        LOG.info("["+ new SimpleDateFormat("yyyy-MM-dd : HH:mm:ss").format(new Date())+"] com.zl.checkapi service start...");
        init(args);
    }
    static void init(String[] args){
        com.alibaba.dubbo.container.Main.main(args);
    }
}
