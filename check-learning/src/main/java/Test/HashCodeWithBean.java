package Test;

import commonbeans.People;

import java.util.HashMap;
import java.util.Map;

public class HashCodeWithBean {

    public static void main(String[] args){

        People people1 = new People();
        people1.setAge(21);

        People people2 = new People();
        people2.setAge(22);
        int code1 = people1.hashCode();
        int code2 = people2.hashCode();

        System.out.println(code1);
        System.out.println(code2);
        Integer inta = new Integer(2);
        Integer intb = new Integer(2);
        int intc = 2;
        System.out.println(inta);
        System.out.println(intb);
        System.out.println(intc);
        Map hashMap = new HashMap<>();
    }
}
