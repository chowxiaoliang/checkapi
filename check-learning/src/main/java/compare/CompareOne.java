package compare;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CompareOne {

    public static void main(String[] args) {
        Person person1 = new Person("123", "yangxiaoxiao", "321", 29);
        Person person2 = new Person("123", "wangwenqi", "321", 25);
        Person person3 = new Person("123", "zhouliang", "321", 28);

        List<Person> list = new ArrayList<>();
        list.add(person1);
        list.add(person2);
        list.add(person3);

        Collections.sort(list);
        System.out.println(JSONObject.toJSONString(list));

    }

    static class Person implements Comparable<Person>{

        private String certNo;

        private String name;

        private String phoneNo;

        private int age;

        public Person(String certNo, String name, String phoneNo, int age){
            this.certNo = certNo;
            this.name = name;
            this.phoneNo = phoneNo;
            this.age = age;
        }

        public String getCertNo() {
            return certNo;
        }

        public void setCertNo(String certNo) {
            this.certNo = certNo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhoneNo() {
            return phoneNo;
        }

        public void setPhoneNo(String phoneNo) {
            this.phoneNo = phoneNo;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public int compareTo(Person person) {

            if (person == null){
                return 0;
            }
            return Integer.compare(age, person.getAge());
        }
    }
}
