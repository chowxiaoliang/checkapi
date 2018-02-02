package com.zl.checkapi.reptile.ioutil;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ConnectUtil {

    private static final Logger logger = LoggerFactory.getLogger(ConnectUtil.class);

    /**
     * 获取url连接
     * 20超时
     * @param url url
     * @return
     */
    public static Document getConnect(String url){
        Connection con = Jsoup.connect(url).timeout(20000);
        con.header("usr-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");
        Document doc = null;
        try {
            doc = con.post();
        } catch (IOException e) {
            logger.error("连接获取失败=>{}", con, e);
        }
        if(doc!=null){
            return doc;
        }
        return null;
    }
}
