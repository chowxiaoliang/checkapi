package collections.list.lambda;

import com.alibaba.fastjson.JSONObject;
import commonbeans.People;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaFiltersTest {

    public static void main(String[] args){

        List<String> testList = new ArrayList<>(3);
        testList.add("zhouliang");
        testList.add("yangxiaoxiao");
        testList.add("wangwenqi");
        testList.add("huiyuan");

        List<People> peopleList =  new ArrayList<>();
        People p1 = new People();
        People p2 = new People();
        People p3 = new People();
        People p4 = new People();
        p1.setAge(21);
        p1.setName("zhouliang");
        p1.setSex("male");
        p2.setAge(22);
        p2.setSex("female");
        p2.setName("wangwenqi");

        p3.setAge(23);
        p3.setSex("female");
        p3.setName("yangxiaoxiao");

        p4.setAge(24);
        p4.setSex("female");
        p4.setName("huiyuan");

        peopleList.add(p1);
        peopleList.add(p2);
        peopleList.add(p3);
        peopleList.add(p4);

        //filter
        List<People> peopleList1 = peopleList.stream().filter(people -> people.getAge()>21).collect(Collectors.toList());
        System.out.println(JSONObject.toJSONString(peopleList1));
        //distinct
        List<People> peopleList2 = peopleList.stream().distinct().collect(Collectors.toList());
        System.out.println(JSONObject.toJSONString(peopleList2));
        //max min
        People resultPeople = peopleList.stream().max(Comparator.comparing(People::getAge)).get();
        System.out.println(resultPeople);
//        List<People> peopleList3 = peopleList.stream().collections.map(People::getAge).collect;

        Long resultList = testList.stream().filter( str -> str.length()>7).count();
        System.out.println(JSONObject.toJSONString(resultList));

        List<String> finalList = testList.stream().filter("zhouliang"::equalsIgnoreCase).map(String::toUpperCase).collect(Collectors.toList());

        System.out.println(JSONObject.toJSONString(finalList));


        List<Integer> nums = Arrays.asList(1,1,null,2,3,4,null,5,6,7,8,9,10);
        List<String> nus = Arrays.asList("1","2");
        System.out.println("sum is:"+nums.stream().filter(num -> num != null).

        distinct().mapToInt(num -> num * 2).

        peek(System.out::println).skip(2).limit(4).sum());

        Stream<List<Integer>> inputStream = Stream.of(
                Arrays.asList(1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5, 6)
        );
        Stream<Integer> outputStream = inputStream.
                flatMap((childList) -> childList.stream());
    }
}
