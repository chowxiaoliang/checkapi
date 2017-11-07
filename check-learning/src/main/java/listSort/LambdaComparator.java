package listSort;

import commonbeans.People;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LambdaComparator {

    public static void main(String[] args){

        List<People> testList = new ArrayList<>();

        People people1 = new People();
        people1.setAge(21);
        people1.setName("yangxiaoxiao");
        people1.setSex("female");

        People people2 = new People();
        people2.setAge(22);
        people2.setName("zhouliang");
        people2.setSex("male");

        testList.add(people2);
        testList.add(people1);

        for(People people : testList){
            System.out.println("old name list:"+people.getName());
        }
        Comparator<People> comparator = (t1, t2) -> t1.getName().compareTo(t2.getName());
        Collections.sort(testList, comparator);

        for(People people : testList){
            System.out.println("new name list:"+people.getName());
        }



    }
}
