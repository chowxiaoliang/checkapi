package map;

import java.util.HashMap;
import java.util.Map;

public class CopyValue {

    public static void main(String[] args){

        Map<String, String> testMap = new HashMap<>(16);


        testMap.put("certNo", "123456789874561230");
        testMap.put("name", "json");
        testMap.put("mobile", "13227136694");
        testMap.put("address", "南山大道");

        for(Map.Entry<String, String> entry : testMap.entrySet()){
            System.out.println("orginal data=>"+entry.getValue());
        }

        testMap.remove("certNo");
        testMap.remove("name");

        for(Map.Entry<String, String> entry : testMap.entrySet()){
            System.out.println("new data=>"+entry.getValue());
        }
    }
}
