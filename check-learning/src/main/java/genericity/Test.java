package genericity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Test {

    public static void main(String[] args){
        Map<String, String> map1 = new HashMap<>(16);

        map1.put("name", "yangxiaoxiao");
        map1.put("sex","female");
        map1.put("age", "22");

        Map<String, String> map2 = new HashMap<>(16);
        map2 = map1;
        Iterator iterator = map2.entrySet().iterator();
        while(iterator.hasNext()){
            String key = iterator.next().toString();
            String value = map2.get(key);
            System.out.println(value);
        }
    }
}
