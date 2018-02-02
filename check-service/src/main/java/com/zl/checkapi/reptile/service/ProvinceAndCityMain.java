package com.zl.checkapi.reptile.service;

import com.alibaba.fastjson.JSONObject;
import com.zl.checkapi.reptile.ioutil.ConnectUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import threadpool.ThreadPoolUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author zhouliang
 */
public class ProvinceAndCityMain {

    private static final Logger logger = LoggerFactory.getLogger(ProvinceAndCityMain.class);

    private static final String MAIN_URL = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2016/index.html";

    private static final String PREFIX_URL = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2016/";

    private static final Map<String, String> PROVINCE_MAP = new HashMap<>(31);

    private static final List<>

    public static void main(String[] args){

        getProvince();

        for(String provinceStr : PROVINCE_MAP.keySet()){
            String province = provinceStr;
            String provinceUrl = PROVINCE_MAP.get(province);
            //31个分8个线程
            ThreadPoolExecutor threadPoolExecutor = ThreadPoolUtil.newCPUPool("pcds");
        }
    }

    private static void getProvince(){
        Document mainDocument = ConnectUtil.getConnect(MAIN_URL);

        Element bodyElement = null;
        if(mainDocument!=null){
            bodyElement  = mainDocument.body();
        }else{
            logger.error("文档内容为空! 正退出");
            System.exit(0);
        }
        //class属性provincetable只有一个
        Elements mainElements = bodyElement.select(".provincetable");

        if(mainElements!=null && mainElements.size()>0){
            Element element = mainElements.get(0);
            //class属性provincetr共有四个
            Elements trElements = element.select(".provincetr");

            for (Element trElement : trElements) {
                Elements tdElement = trElement.children();
                for (Element aTdElement : tdElement) {
                    String province = aTdElement.select("a").get(0).html().replace("<br>", "");
                    String orgPath = aTdElement.select("a").attr("href");
                    String finalPath = PREFIX_URL + orgPath;
                    PROVINCE_MAP.put(province, finalPath);
                }
            }
            System.out.println(JSONObject.toJSONString(PROVINCE_MAP));
        }
    }
}
