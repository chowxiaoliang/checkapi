/*
 * Copyright (C) 2014-2017 LS Information Technology Co. Ltd.
 *
 * All right reserved.
 *
 * This software is the confidential and proprietary information of LS Company of China.
 * ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the contract agreement you entered into with LS inc.
 *
 * $Id: SceneChannelMapping.java  2015年2月11日  ljn $
 *
 * Create on 2015年2月11日 上午11:10:02
 *
 * Description:
 *
 */

package com.zl.checkapi.phoenix;

import com.alibaba.dubbo.common.utils.ConfigUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

/**
 * 
 * <b>类说明：加载config.properties文件，以Map<String, String>的形式存储，方便在其他地方使用</b>
 * 
 * <p>
 * <b>详细描述：</b>
 * 
 * <p>
 * 
 * @author ljn
 * @since 2015年2月11日
 */
public enum ConfigMap {

    INSTANCE;

    private static final Logger logger = LoggerFactory.getLogger(ConfigMap.class);

    private Map<String, String> map;
    
    public void init() {
        if (map == null) {
            reload();
        }
    }
    
    private void reload() {
        map = new HashMap<String, String>();
        Properties prop = ConfigUtils.loadProperties("properties/config.properties");
        for (Entry<Object, Object> item : prop.entrySet()) {
            String key = (String) item.getKey();
            String value = (String) item.getValue();
            map.put(key, value);
        }
        logger.info("load config.properties to Map<String, String> ok");

    }

    public Map<String, String> getAll() {
        return Collections.unmodifiableMap(map);
    }

    public String getValue(String key) {
        return map.get(key);
    }
    
    public int getAsInteger(String key) {
        return Integer.parseInt(map.get(key));
    }

    public static void main(String[] args) {
        ConfigMap.INSTANCE.reload();
        System.out.println(ConfigMap.INSTANCE.getAll());
    }

}
