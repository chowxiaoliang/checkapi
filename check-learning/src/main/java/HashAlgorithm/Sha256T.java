package HashAlgorithm;

import com.alibaba.fastjson.JSONObject;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha256T {

    public static void main(String[] args) {
        String name = "zhouliang";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] result = messageDigest.digest(name.getBytes(StandardCharsets.UTF_8));
            System.out.println(JSONObject.toJSONString(result));

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
