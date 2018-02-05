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
        Connection con = Jsoup.connect(url).timeout(100000);
        con.header("usr-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");
        con.header("Upgrade-Insecure-Requests", "1");
        con.header("Host", "www.stats.gov.cn");
        con.header("Cookie", "_trs_uv=jd5j3u60_6_5riu; AD_RS_COOKIE=20080919");
        con.header("Connection", "keep-alive");
        con.header("Accept-Language", "zh-CN,zh;q=0.8");
        con.header("Accept-Encoding", "gzip, deflate");
        con.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        Document doc = null;
        try {
            doc = con.post();
        } catch (IOException e) {
            logger.error("连接第一次获取失败=>{},url=>{}", con, url, e);
            try{
                doc = con.post();
            }catch (IOException e1){
                logger.error("连接第二次获取失败=>{},url=>{}", con, url, e1);
                try{
                    doc = con.post();
                }catch (IOException e2){
                    logger.error("连接第三次获取失败=>{},url=>{}", con, url, e1);
                }
            }
        }
        if(doc!=null){
            return doc;
        }
        return null;
    }
}
