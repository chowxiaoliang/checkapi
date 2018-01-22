package com.zl.checkapi.reptile;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class FirstTest {

    public static void main(String[] args){
        Connection con = Jsoup.connect("http://www.csszengarden.com/tr/zh-cn/");
        con.header("usr-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36");
        Document doc = null;
        try {
            doc = con.post();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements e = doc.getElementsByTag("section");
        System.out.println("总的文档内容是：");
        System.out.println(e);



    }
}
