package io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * @author zhouliang
 * @since 2018-05-24 16:33
 **/
public class BufferedInputStream {

    private final static Logger LOGGER  = LoggerFactory.getLogger(BufferedInputStream.class);

    public static void main(String[] args) throws IOException {
        String filePath = "F:\\github\\checkapi\\check-learning\\src\\main\\resources\\original.text";
        File file = new File(filePath);
        LOGGER.info("file length is => {}", file.length());
        InputStream inputStream = new FileInputStream(file);
        java.io.BufferedInputStream bufferedInputStream = new java.io.BufferedInputStream(inputStream);
        byte[] bytes = new byte[(int)file.length()];
        int resultOne = bufferedInputStream.read(bytes, 0, bytes.length);
        int resultTwo = bufferedInputStream.read();
        LOGGER.info("size of result is => {}", resultOne);
        LOGGER.info("size of result is => {}", resultTwo);
        LOGGER.info("content of resultOne is => {}", new String(bytes));
    }
}
