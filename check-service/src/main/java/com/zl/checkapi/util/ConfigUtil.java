package com.zl.checkapi.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created by zhouliang on 2017/9/11.
 */
public class ConfigUtil {

    private final static String filePath = "/properties/config.properties";

    private static Properties prop = null;

    private static Long lastModified = 0L;

    private final static Logger LOG = LoggerFactory.getLogger(ConfigUtil.class);

    static{
        loadProperties();
    }

    private static void loadProperties(){
        InputStream inputStream = null;
        try{
            prop = new Properties();
            inputStream = ConfigUtil.class.getResourceAsStream(filePath);
            prop.load(inputStream);
        }catch (Exception e){
            LOG.error("load config.properties failed!", e);
        }finally {
            try{
                if(null != inputStream){
                    inputStream.close();
                }
            }catch (Exception e){
                LOG.error("close resources failed!", e);
            }
        }
        LOG.info("load config.properties success!");
    }

    public static String getValueByKey(String key){
        if(StringUtils.isEmpty(key)){
            return null;
        }
        String value = prop.getProperty(key);
        if(null == value){
            return null;
        }else{
            return value.trim();
        }
    }

    public static void main(String[] agrs){
        System.out.println("execute services start...");
        ApplicationContext ctx = new ClassPathXmlApplicationContext("application.xml");
        System.out.println(ConfigUtil.getValueByKey("dubbo.registry.address"));
    }
}
