package io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * @author zhouliang
 * @since 2018-05-24 16:52
 **/
public class BufferedReader {

    private final static Logger LOGGER = LoggerFactory.getLogger(BufferedReader.class);

    public static void main(String[] args) throws IOException {
        String filePath = "F:\\github\\checkapi\\check-learning\\src\\main\\resources\\original.text";

        File file = new File(filePath);

        Reader reader = new FileReader(file);

        java.io.BufferedReader bufferedReader = new java.io.BufferedReader(reader);

        String line ;
        while((line=bufferedReader.readLine())!=null){
            System.out.println(line);
        }

        bufferedReader.close();
    }
}
