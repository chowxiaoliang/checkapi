package collections.map;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * 1.针对key排序
 * 传入一个比较器
 * o1-o2为升序
 * o2-o1为降序
 *
 * 2.针对value排序
 * 将TreeMap初始化成一个list
 * 每个list存的是一个个entry
 * 直接对list进行排序即可
 */
public class TreeMapSort {

    public static void main(String[] args) {


        Map<Integer, Integer> treeMap = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        });

        treeMap.put(1,2);
        treeMap.put(3,4);
        treeMap.put(2,9);
        treeMap.put(4,5);

        List<Map.Entry<Integer,Integer>> list = new ArrayList<>(treeMap.entrySet());

        list.sort(new Comparator<Map.Entry<Integer, Integer>>() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o2.getValue()-o1.getValue();
            }
        });

        System.out.println(JSONObject.toJSONString(list));
    }
}
