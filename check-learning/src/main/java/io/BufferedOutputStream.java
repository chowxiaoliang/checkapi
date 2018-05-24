package io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.io.BufferedInputStream;

/**
 * @author zhouliang
 * @since 2018-05-24 17:42
 **/
public class BufferedOutputStream {

    private final static Logger LOGGER = LoggerFactory.getLogger(BufferedOutputStream.class);

    public static void main(String[] args) throws IOException {
        //先读再写
        String originalPath = "F:\\github\\checkapi\\check-learning\\src\\main\\resources\\original.text";

        String destinaPath = "F:\\github\\checkapi\\check-learning\\src\\main\\resources\\destination.text";
        //读文件内容
        File file = new File(originalPath);
        InputStream inputStream = new FileInputStream(file);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

        //写文件内容
        File file1 = new File(destinaPath);
        OutputStream outputStream = new FileOutputStream(file1);
        java.io.BufferedOutputStream bufferedOutputStream = new java.io.BufferedOutputStream(outputStream);

        byte[] bytes = new byte[(int)file.length()];
        LOGGER.info("file length is => {}", bytes.length);
        int result = bufferedInputStream.read(bytes, 0, bytes.length);

        bufferedOutputStream.write(bytes);
        bufferedOutputStream.flush();
        bufferedInputStream.close();
        bufferedOutputStream.close();
    }
}
