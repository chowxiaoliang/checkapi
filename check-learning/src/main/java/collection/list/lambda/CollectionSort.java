package collection.list.lambda;

import commonbeans.Info;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionSort {

    public static void main(String[] args){


        List<Info> testList1 = new ArrayList<>();
        Info info1 = new Info();
        info1.setAge(21);
        info1.setName("yangxiaoxiao");
        info1.setSex("female");

        Info info2 = new Info();
        info2.setAge(22);
        info2.setName("zhouliang");
        info2.setSex("male");

        testList1.add(info1);
        testList1.add(info2);

        Collections.sort(testList1);

        for(int i=0;i<testList1.size();i++){

            System.out.println(testList1.get(i).getName());
        }



    }

}
