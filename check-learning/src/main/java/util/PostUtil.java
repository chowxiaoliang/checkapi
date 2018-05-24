package util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by lenovo on 2017/6/16.
 */
public class PostUtil {
    private static final Integer timeOut = 10000;
    public static String sendPost(String url, String params){

        PrintWriter out = null;
        BufferedReader in = null;
        URL targetUrl = null;
        StringBuffer result = new StringBuffer();

        try {
            targetUrl = new URL(url);

            // 打开和URL之间的连接
            HttpURLConnection httpConnection = (HttpURLConnection)targetUrl.openConnection();
            // 设置超时
            httpConnection.setConnectTimeout(timeOut);
            httpConnection.setReadTimeout(timeOut);
            // 设置通用的请求属性
            httpConnection.setRequestProperty("connection", "Keep-Alive");
            httpConnection.setRequestProperty("Charset", "UTF-8");
            httpConnection.setRequestProperty("Content-Type", "application/json");
            // 发送POST请求
            httpConnection.setRequestMethod("POST");
            httpConnection.setDoOutput(true);
            httpConnection.setDoInput(true);

            out = new PrintWriter(httpConnection.getOutputStream());
            out.print(params);
            out.flush();

            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(httpConnection.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        }catch (Exception e){

        }
        //使用finally块来关闭输出流、输入流
        finally {
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return result.toString();
    }
}
