package okooo;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class OkoooConnecUtil {

    private static final Logger logger = LoggerFactory.getLogger(OkoooConnecUtil.class);

    /**
     * 获取url连接
     * 20超时
     * post请求的方式获取
     * @param url url
     * @return
     */
    public static Document postConnect(String url){
        Connection con = Jsoup.connect(url).timeout(100000);
        // 设置头部信息
        con.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3");
        con.header("Accept-Encoding", "gzip, deflate");
        con.header("Accept-Language", "zh-CN,zh;q=0.9");
        con.header("Connection", "keep-alive");
        con.header("Cookie", "Hm_lvt_5ffc07c2ca2eda4cc1c4d8e50804c94b=1566979022; BAIDU_SSP_lcr=https://www.baidu.com/link?url=I9fPE2Xpto8-7IwUaIKw9itdlxhQsoGxPXZpnVZY1Iy&wd=&eqid=e47c2ed30013aa22000000065d6633a1; __utma=56961525.1652593389.1566979022.1566979022.1566979022.1; __utmc=56961525; __utmz=56961525.1566979022.1.1.utmcsr=baidu|utmccn=(organic)|utmcmd=organic; pm=; LStatus=N; LoginStr=%7B%22welcome%22%3A%22%u60A8%u597D%uFF0C%u6B22%u8FCE%u60A8%22%2C%22login%22%3A%22%u767B%u5F55%22%2C%22register%22%3A%22%u6CE8%u518C%22%2C%22TrustLoginArr%22%3A%7B%22alipay%22%3A%7B%22LoginCn%22%3A%22%u652F%u4ED8%u5B9D%22%7D%2C%22tenpay%22%3A%7B%22LoginCn%22%3A%22%u8D22%u4ED8%u901A%22%7D%2C%22qq%22%3A%7B%22LoginCn%22%3A%22QQ%u767B%u5F55%22%7D%2C%22weibo%22%3A%7B%22LoginCn%22%3A%22%u65B0%u6D6A%u5FAE%u535A%22%7D%2C%22renren%22%3A%7B%22LoginCn%22%3A%22%u4EBA%u4EBA%u7F51%22%7D%2C%22baidu%22%3A%7B%22LoginCn%22%3A%22%u767E%u5EA6%22%7D%2C%22weixin%22%3A%7B%22LoginCn%22%3A%22%u5FAE%u4FE1%u767B%u5F55%22%7D%2C%22snda%22%3A%7B%22LoginCn%22%3A%22%u76DB%u5927%u767B%u5F55%22%7D%7D%2C%22userlevel%22%3A%22%22%2C%22flog%22%3A%22hidden%22%2C%22UserInfo%22%3A%22%22%2C%22loginSession%22%3A%22___GlobalSession%22%7D; acw_tc=7d4d8e2815669790230542806e300bcaa187219545e9845e768e7ef716; PHPSESSID=9825796cb623cc1a36b56aed4ca3e3c063941df0; LastUrl=; Hm_lpvt_5ffc07c2ca2eda4cc1c4d8e50804c94b=1566979102; __utmb=56961525.11.9.1566980681786");
        con.header("Host", "www.okooo.com");
        con.header("Upgrade-Insecure-Requests", "1");
        con.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36");
        Document doc = null;
        try {
            doc = con.post();
        } catch (IOException e) {
            logger.error("连接第一次获取失败，url=>{}", url, e);
            try{
                doc = con.post();
            }catch (IOException e1){
                logger.error("连接第二次获取失败,url=>{}", url, e1);
                try{
                    doc = con.post();
                }catch (IOException e2){
                    logger.error("连接第三次获取失败,url=>{}", url, e1);
                }
            }
        }
        if(doc!=null){
            return doc;
        }
        return null;
    }
}
