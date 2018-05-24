package classloader;

import java.lang.*;
import java.net.URL;

/**
 * Created by Administrator on 2017/11/20 0020.
 */
public class ReadByURL {

    public String testRoute(){
        URL url = java.lang.ClassLoader.getSystemResource("/yangxiaoxiao.html");
        return url.toString();
    }

    public static void main(String[] args){

        ReadByURL readByURL = new ReadByURL();
        System.out.println(readByURL.testRoute());
    }
}
