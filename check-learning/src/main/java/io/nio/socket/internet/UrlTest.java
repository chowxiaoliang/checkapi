package io.nio.socket.internet;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author zhouliang
 * @since 2019-02-23 13:32
 * @desc
 * URL中文名称为统一资源定位符（Uniform Resource Locator），表示Internet上某一资源的地址。
    比如说大家经常访问我们的imooc网站，那么在我们的网站上提供了非常多的资源，在访问网站的时候，
    我们要输入一个地址，也就是网址 www.imooc.com，它呢实际上就是一个url，标识着我们imooc网站上大家共享的资源信息。
    URL由两部分组成：协议名称和资源名称，中间用冒号隔开。我们所说的网址，实际就是HTTP协议，
    超文本传输协议：Http：//www.imooc.com/learn/85 这就是浏览网页所使用到的一个协议。像HTTP就是协议名称，后面的就是资源名称。
    在java当中，java.net包，提供了URL类来表示URL。

    构造方法，可以根据字符串方式来创建，参数要求不同，也可以根据协议名，主机、端口号、和文件的信息来创建URL。也可以根据一个已存在的URL，以及相应的字符串信息来创建一个新的URL，也就是说根据一个父URL来创建一个子URL，这是我们看到的它的构造方法，这个类还提供了许多其他方法来获取URL的信息。
    关于getPort()方法返回值问题：

    HTTP协议在当初制定时规定，其默认端口号为80，即未申明（省略）端口号的情况下，浏览器自动为其补充上URL中缺失的“:80”部分。关于HTTP协议的其它详情，可查阅RFC 2616。
    java.net.URL.getPort()规定，若URL的实例未申明（省略）端口号，则返回值为-1。
    两件事情请区分开来。
 */
public class UrlTest {
    /**
     * 使用URL读取网页内容，通过URL对象的openStream()方法可以得到指定资源的输入流，通过输入流可以读取、访问网络上的数据。
     * @param args
     * @throws MalformedURLException
     */
    public static void main(String[] args) throws IOException {
        URL url = new URL("https://www.qq.com");
        System.out.println("authority："+url.getAuthority());
        System.out.println("content："+url.getContent());
        System.out.println("defaultPort："+url.getDefaultPort());
        System.out.println("file："+url.getFile());
        System.out.println("host："+url.getHost());
        System.out.println("path："+url.getPath());
        System.out.println("port："+url.getPort());
        System.out.println("protocol："+url.getProtocol());
        System.out.println("query："+url.getQuery());
        System.out.println("ref："+url.getRef());
        System.out.println("userInfo："+url.getUserInfo());

        System.out.println("获取网页里面的内容");
        UrlTest urlTest = new UrlTest();
        urlTest.getContent(url);

    }

    private void getContent(URL url) throws IOException {
        InputStream inputStream = url.openStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String data = bufferedReader.readLine();
        while (data != null){
            System.out.println(data);
            data = bufferedReader.readLine();
        }
        bufferedReader.close();
        inputStreamReader.close();
        inputStream.close();
    }
}
