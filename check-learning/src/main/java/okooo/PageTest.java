package okooo;

import org.jsoup.nodes.Document;

public class PageTest {

    public static void main(String[] args) {
        String url = "http://www.okooo.com/zucai/19111/";
        Document document = OkoooConnecUtil.postConnect(url);
        System.out.println(document.toString());
    }

}
