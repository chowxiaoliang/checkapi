package io.nio.socket;

import java.io.*;
import java.net.Socket;

/**
 * @author zhouliang
 * @since 2019-02-23 21:02
 */
public class Client {
    public static void main(String[] args) throws IOException {
        System.out.println("正启动客户端");
        Socket socket = new Socket("localhost", 8888);
        OutputStream outputStream = socket.getOutputStream();
        PrintWriter printWriter = new PrintWriter(outputStream);
        printWriter.write("用户名：admin，密码：123456");
        printWriter.flush();
        socket.shutdownOutput();

        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String data ;
        while ((data = bufferedReader.readLine())!= null){
            System.out.println("data from server is =>" + data);
        }
        bufferedReader.close();
        inputStream.close();
        printWriter.close();
        outputStream.close();
        socket.close();
    }
}
