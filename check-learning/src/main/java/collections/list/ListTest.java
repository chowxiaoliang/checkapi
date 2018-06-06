package collections.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ListTest {

    public static void main(String[] args){

        List<String> linkedList = new LinkedList<>();
        linkedList.add("a");
        linkedList.add("b");
        linkedList.add("c");
        linkedList.add("a");
        linkedList.add("a");
        int a = Collections.frequency(linkedList, "a");
        System.out.println(a);

        List<String> list = new ArrayList<>();
        list.add(null);
        list.add("zhouliang");
        list.add(null);
        list.add("yangxiaoxiao");
        for(String str : list){
            System.out.println(str);
        }
    }
}
