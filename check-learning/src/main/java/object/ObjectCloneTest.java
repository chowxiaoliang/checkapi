package object;

import commonbeans.People;

public class ObjectCloneTest {
    public static void main(String[] args){
        People people = new People();
        people.setAge(21);
        people.setName("json");
        people.setSex("male");

        System.out.println(people.toString());
        System.out.println(people.hashCode());

    }

}
