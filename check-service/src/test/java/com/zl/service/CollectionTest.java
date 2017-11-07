package com.zl.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CollectionTest {

    public native void sta();

    public static void main(String[] args){

        Map<String, String> testMap = new HashMap<>(16);

        testMap.put("name", "yangxiaoxiao");
        testMap.put("age", "21");
        testMap.put("sex", "female");
        testMap.put(null, null);
        Iterator<String> iterator = testMap.keySet().iterator();
        System.out.println("map的大小是:"+testMap.size());
        while(iterator.hasNext()){
            String key = iterator.next();
            String value = testMap.get(key);
            System.out.println("key:"+key);
            System.out.println("value:"+value);
        }
    }
}
