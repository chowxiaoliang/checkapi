package io.nio.socket.internet;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * @author zhouliang
 * @since 2019-02-23 12:52
 * @desc
 * .JAVA中InetAddress的应用，了解了网络基础知识以后，我们来了解一下InetAddress类InetAddress类用于标识网络上的硬件资源，
 * 标识互联网协议（IP）地址的相关信息。打开API查看该类，没有构造方法，
 * 也就是没有办法直接通过new的方式来创建一个它的对象，我们接着往下看一下方法摘要，它提供了一些静态方法。如：方法体作用
    getLocalHost（） 返回本地主机
    getByName（）	在给定主机名的情况下，确定主机的IP地址
    getByAdress（）	在给定原始IP地址的情况下，返回InetAddress的对象
    getByAdress（）	根据提供的主机名和IP地址，创建InetAddress对象
    getHostName（）	获取此IP地址的主机名
    getHostAddress（）	返回IP地址的字符串（以文本表现形式）
 */
public class InetAddressTest {
    public static void main(String[] args) throws UnknownHostException, UnsupportedEncodingException {
        // 获取本机的InetAddress实例
        InetAddress inetAddress = InetAddress.getLocalHost();
        System.out.println("计算机名称是:" + inetAddress.getHostName());
        System.out.println("IP地址是:" + inetAddress.getHostAddress());
        byte[] bytes = inetAddress.getAddress();
        System.out.println("字节形式的数据IP是:" + Arrays.toString(bytes));
        System.out.println(inetAddress);

        // 根据主机名获取InetAddress实例
        InetAddress inetAddress1 = InetAddress.getByName("Liang");
        System.out.println(inetAddress1);
        // 根据IP地址获取InetAddress实例
        InetAddress inetAddress2 = InetAddress.getByName("192.168.1.102");
        System.out.println(inetAddress2);
    }
}
