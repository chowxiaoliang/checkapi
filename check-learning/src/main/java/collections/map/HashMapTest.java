package collections.map;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class HashMapTest {
    public static void main(String[] args){
        Map<String, String> map = new ConcurrentHashMap<>(16);
        for(int i=0;i>-1;i++){
            new Thread(()->{
                map.put(UUID.randomUUID().toString(),UUID.randomUUID().toString());
            }).start();
            System.out.println("容器中线程数是=>"+i);
        }
    }
}
