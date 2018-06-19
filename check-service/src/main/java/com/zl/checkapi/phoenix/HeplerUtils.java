package com.zl.checkapi.phoenix;


import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.hbase.util.Bytes;

import java.nio.ByteBuffer;


public class HeplerUtils {
    private static ByteBuffer buffer = ByteBuffer.allocate(8);    

    
    public static byte[] long2Bytes(long l) {
        byte[] result = new byte[8];
        for (int i = 7; i >= 0; i--) {
            result[i] = (byte)(l & 0xFF);
            l >>= 8;
        }
        return result;
    }

    public static long bytes2Long(byte[] b) {
        long result = 0;
        for (int i = 0; i < 8; i++) {
            result <<= 8;
            result |= (b[i] & 0xFF);
        }
        return result;
    }
    
    public static String genKey(String rowkey,String table) {
        String[] arr = new String[] {
        		rowkey,
        		table
        };
        return StringUtils.join(arr, ":");
    }
    
    public static byte[] LongtoBytesWithString(long input) {
        String str=Long.toString(input);
        return Bytes.toBytes(str);
    }
    
    public static long ByteTolongWithString(byte[] a) {
        return  Long.parseLong(new String(a));
    }
    
    
    
    public static void main(String args[]){
    	//System.out.println(HeplerUtils.long2Bytes(5L));
    	
    	System.out.println(LongtoBytesWithString(12354568l));
    	
    	System.out.println(ByteTolongWithString(LongtoBytesWithString(12354568l)));
    	
    	
    	
    }

}