package io.nio.socket;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author zhouliang
 * @since 2019-02-23 13:55
 */
public class Server {
    public static void main(String[] args) throws IOException {
        System.out.println("服务端已经启动");
        ServerSocket serversocket = new ServerSocket(8888);
        Socket socket ;
        int count = 0;
        while (true){
            // 一旦调用这个方法它就会处于阻塞的状态，等待客户端的请求信息
            socket = serversocket.accept();
            // 如果客户端发送连接请求，这个时候我们会接收客户端的请求，并且我们看到accpet方法会返回一个socket的实例
            // 用来与客户端进行通信
            // 一旦与客户端建立socket通信以后，我们下面就需要在客户端和服务器端实现数据的交互，获取客户端提交的登陆
            // 信息，那么如何获取呢？需要通过输入输出流来实现。
            // 3.获取一个输入流，然后来读取客户信息
            InputStream is = socket.getInputStream();
            // 为了提升效率可以把它包装成一个字符输入流
            InputStreamReader isr = new InputStreamReader(is);
            // 我们可以为字符流添加缓冲，以缓冲的方式去进行数据的读取
            BufferedReader br = new BufferedReader(isr);
            String info ;
            while((info = br.readLine())!= null){
                System.out.println("我是服务器，客户端说"+info);
            }
            socket.shutdownInput();//关闭输入流
            // 4.获取输出流，用来服务器端响应客户端的信息
            OutputStream os=socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os);
            pw.write("欢迎您！");
            pw.flush();

            InetAddress inetAddress = socket.getInetAddress();
            System.out.println("client ip is =>" + inetAddress.getHostAddress());
            count ++;
            System.out.println("current client num is =>" + count);
            // 5.关闭相关的资源
            pw.close();
            os.close();
            br.close();
            isr.close();
            is.close();
            socket.close();
            // serverSocket.close();有一个死循环所以无法关闭也不会进展到这一步，所以删掉
        }
    }

    class SocketThread extends Thread{

        private Socket socket;

        public SocketThread(Socket socket){
            this.socket = socket;
        }
        @Override
        public void run() {
            InputStream is = null;//字节输入流
            InputStreamReader isr=null;
            BufferedReader br=null;
            OutputStream os=null;
            PrintWriter pw=null;
            try {
                is = socket.getInputStream();
                //3.获取输入流读取客户端的信息
                //为了提升效率可以把它包装成一个字符输入流
                isr = new InputStreamReader(is);
                //我们可以为字符流添加缓冲，以缓冲的方式去进行数据的读取
                br = new BufferedReader(isr);
                String info ;
                while ((info = br.readLine()) != null) {
                    System.out.println("我是服务器，客户端说" + info);
                }
                socket.shutdownInput();//关闭输入流
                //4.获取输出流，用来服务器端响应客户端的信息
                os = socket.getOutputStream();
                pw = new PrintWriter(os);
                pw.write("欢迎您！");
                pw.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                //5.关闭相关的资源,finally是一定会被执行的
                try {
                    if(pw!=null){
                        pw.close();
                    }
                    if(os!=null){
                        os.close();
                    }
                    if(br!=null){
                        br.close();
                    }
                    if(isr!=null){
                        isr.close();
                    }
                    if(is!=null){
                        is.close();
                    }
                    if(socket!=null){
                        socket.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
