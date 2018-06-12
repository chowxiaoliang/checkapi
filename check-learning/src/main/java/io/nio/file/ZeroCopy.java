package io.nio.file;

import java.io.*;
import java.nio.channels.FileChannel;

/**
 * @author zhouliang
 * @since 2018-06-11 20:04
 * @desc 零拷贝技术 java api提供两种方式（1.transferTo 2.transferFrom）
 **/
public class ZeroCopy {
    public static void main(String[] args) throws IOException {
        String inputPath = "C:\\Users\\lenovo\\Desktop\\file.txt";
        String outputPath = "C:\\Users\\lenovo\\Desktop\\zhouliang.txt";

        long startTime = System.currentTimeMillis();

        FileInputStream fileInputStream = new FileInputStream(inputPath);
        FileOutputStream fileOutputStream = new FileOutputStream(outputPath);

        FileChannel inputChanel = fileInputStream.getChannel();
        FileChannel outputChanel = fileOutputStream.getChannel();

        long result = inputChanel.transferTo(0, inputChanel.size(), outputChanel);

        long endTime = System.currentTimeMillis();

        System.out.println("total time is => "+(endTime-startTime)+"ms");

        System.out.println("zero copy finished! result is => "+ result);
    }
}
