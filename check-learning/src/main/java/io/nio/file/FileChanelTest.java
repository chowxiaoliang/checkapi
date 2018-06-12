package io.nio.file;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author zhouliang
 * @since 2018-06-11 11:47
 **/
public class FileChanelTest {
    public static void main(String[] args) throws IOException {
        String filePath = "C:\\Users\\lenovo\\Desktop\\file.txt";
        String resultPath = "C:\\Users\\lenovo\\Desktop\\result.txt";

        long startTime = System.currentTimeMillis();
        FileInputStream fileInputStream = new FileInputStream(filePath);
        FileOutputStream fileOutputStream = new FileOutputStream(resultPath);
        FileChannel inputFileChannel = fileInputStream.getChannel();
        FileChannel outPutFileChannel = fileOutputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);

        try{
            while (inputFileChannel.read(byteBuffer)!=-1){
                //切换成读取数据模式
                byteBuffer.flip();
                //将缓冲区中的数据写入通道中
                outPutFileChannel.write(byteBuffer);
                //清空缓冲区
                byteBuffer.clear();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            outPutFileChannel.close();
            inputFileChannel.close();
            fileInputStream.close();
            fileOutputStream.close();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("total time is => "+(endTime-startTime)+"ms");

    }
}
