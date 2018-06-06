package collections.set;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author zhouliang
 * @since 2018-05-22 10:55
 **/
public class LinkedHashSetTets {
    public static void main(String[] args) {
        Set<String> set = new LinkedHashSet<>(16);
        for(int i=0;i<10;i++){
            set.add(""+i);
        }
        System.out.println(set.size());
        for(String str : set){
            System.out.println(str);
        }
    }
}
