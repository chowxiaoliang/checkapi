package Test;

import java.util.Set;
import java.util.TreeSet;

public class HashCodeTest {

    public static void main(String[] args){

        String a = "zhouliang";
        String b = new String("zhouliang");
        String c = a;
        int acode = a.hashCode();
        int bcode = b.hashCode();
        int ccode = c.hashCode();
        System.out.println(acode);
        System.out.println(bcode);
        System.out.println(ccode);
        if(a.equals(b)){
            System.out.println("equals");
        }
        Set set = new TreeSet();
        set.add(a);
        set.add(b);
        set.add(c);
        System.out.println("the size of set is=>"+set.size());
    }
}
