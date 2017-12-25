package Test;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JavaFiltersTest {

    public static void main(String[] args){

        List<String> testList = new ArrayList<>(3);
        testList.add("zhouliang");
        testList.add("yangxiaoxiao");
        testList.add("wangwenqi");
        testList.add("huiyuan");
        Long resultList = testList.stream().filter( str -> str.length()>7).count();
        System.out.println(JSONObject.toJSONString(resultList));


        List<Integer> nums = Arrays.asList(1,1,null,2,3,4,null,5,6,7,8,9,10);

        System.out.println("sum is:"+nums.stream().filter(num -> num != null).

        distinct().mapToInt(num -> num * 2).

        peek(System.out::println).skip(2).limit(4).sum());

    }
}
