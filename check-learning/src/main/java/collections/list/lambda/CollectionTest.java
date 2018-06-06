package collections.list.lambda;

import java.util.*;

public class CollectionTest {

    public native void sta();

    public static void main(String[] args){

        Map<String, String> testMap = new HashMap<>(16);

        testMap.put("name", "yangxiaoxiao");
        testMap.put("age", "21");
        testMap.put("sex", "female");
        testMap.put(null, null);
        Iterator<String> iterator = testMap.keySet().iterator();
        System.out.println("map的大小是:"+testMap.size());
        while(iterator.hasNext()){
            String key = iterator.next();
            String value = testMap.get(key);
            System.out.println("key:"+key);
            System.out.println("value:"+value);
        }

        try{
            Map<String, String> test = new Hashtable<>();
            test.put(null, null);
            test.put("name", "zhouliang");
            System.out.println("test的大小是=>"+test.size());
            for(Map.Entry<String, String> entry : test.entrySet()){
                System.out.println(entry.getKey());
                System.out.println(entry.getValue());
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        try{
            Set<String> set = new HashSet();
            set.add(null);
            set.add("zhouliang");
            System.out.println(set.size());
            Iterator<String> iterator1 = set.iterator();
            while (iterator1.hasNext()){
                System.out.println(iterator1.next());
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
