package map;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author zhouliang
 * @since 2018-05-22 10:03
 * @desc 自定义排序方式（升序 降序）
 **/
public class TreeMapTestTwo {

    public static void main(String[] args) {
        Map<String, Integer> treeMap = new TreeMap<>(Comparator.reverseOrder());

        treeMap.put("zhouliang", 1);
        treeMap.put("wangwenqi", 3);
        treeMap.put("yangxiaoxiao", 2);

        treeMap.putIfAbsent("zhouliang", 4);

        for(Map.Entry<String, Integer> entry : treeMap.entrySet()){
            System.out.println("key =>"+entry.getKey());
            System.out.println("valu =>"+entry.getValue());
            System.out.println("----------------------");
        }
    }
}
