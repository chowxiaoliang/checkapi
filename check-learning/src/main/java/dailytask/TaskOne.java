package dailytask;

import commonbeans.Person;

/**
 * @author zhouliang
 * @since 2018-06-12 10:02
 **/
public class TaskOne {
    public static void main(String[] args) {
        Person person = new Person("123", "zhouliang", "13227136694", "9975SU289", "广东省深圳市");
        Person copyPerson = new Person("123", "zhouliang", "13227136694", "9975SU289", "广东省深圳市");
        Person person1 = new Person("321", "yangxiaoxiao", "15872151893", "9987NA", "浙江省平湖市");
        System.out.println(person.equals(person1));

        System.out.println(person.equals(copyPerson));
        System.out.println(person.hashCode());
        System.out.println(copyPerson.hashCode());
        System.out.println(person.hashCode()==copyPerson.hashCode());
    }
}
