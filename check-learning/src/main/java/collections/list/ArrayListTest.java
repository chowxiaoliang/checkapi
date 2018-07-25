package collections.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhouliang
 * @since 2018-05-24 15:29
 **/
public class ArrayListTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("zhouliang");
        list.add("yangxiaoxiao");
        list.add("wangwenqi");
        for(int i=0;i<list.size();i++){
            if(i==1){
                String result = list.remove(1);
                System.out.printf("current element is => "+ result);
            }
        }
        for(String str : list){
            System.out.printf("current str is =>"+str);
        }
    }
}
