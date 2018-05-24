package io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.io.BufferedInputStream;

/**
 * @author zhouliang
 * @since 2018-05-24 18:07
 **/
public class BufferedWriter {

    private final static Logger LOGGER = LoggerFactory.getLogger(BufferedWriter.class);

    public static void main(String[] args) throws IOException {
        String originalPath = "F:\\github\\checkapi\\check-learning\\src\\main\\resources\\original.text";

        String destinaPath = "F:\\github\\checkapi\\check-learning\\src\\main\\resources\\destinationTwo.text";

        //先读再写
        File file = new File(originalPath);
        InputStream inputStream = new FileInputStream(file);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

        byte[] bytes = new byte[(int)file.length()];
        inputStream.read(bytes, 0, bytes.length);
        File file1 = new File(destinaPath);
        Writer writer = new FileWriter(file1);
        java.io.BufferedWriter bufferedWriter = new java.io.BufferedWriter(writer);
        bufferedWriter.write(new String(bytes));
        bufferedWriter.flush();
        bufferedWriter.close();
    }
}
