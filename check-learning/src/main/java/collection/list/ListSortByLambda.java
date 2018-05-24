package collection.list;

import commonbeans.People;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author zhouliang
 * @since 2018-05-24 15:32
 **/
public class ListSortByLambda {
    public static void main(String[] args) {
        List<People> testList = new ArrayList<>();

        People people1 = new People();
        people1.setAge(21);
        people1.setName("yangxiaoxiao");
        people1.setSex("female");

        People people2 = new People();
        people2.setAge(22);
        people2.setName("zhouliang");
        people2.setSex("male");

        testList.add(people1);
        testList.add(people2);

        for(People people : testList){
            System.out.println("old name list:"+people.getAge());
        }
        //升序
        Comparator<People> comparator1 = Comparator.comparing(People::getAge);
        //降序
        Comparator<People> comparator2 = ((p1, p2) -> p2.getAge().compareTo(p1.getAge()));
        //升序
        Comparator<People> comparator3 = ((p1, p2)->{return p1.getAge()-p2.getAge();});
        //升序
        Comparator<People> comparator4 = Comparator.comparingInt(People::getAge);
        //降序
        Comparator<People> comparator5 = ((p1, p2)-> p2.getAge()-p1.getAge());

//        testList.sort(comparator1);
//        testList.sort(comparator2);
//        testList.sort(comparator3);
//        testList.sort(comparator4);
        testList.sort(comparator5);


        for(People people : testList){
            System.out.println("new name list:"+people.getAge());
        }
    }
}
