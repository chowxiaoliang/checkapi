package collections.map;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TreeMapSortTwo {

    public static void main(String[] args) {
        Person person1 = new Person("1", "zhouliang", "56");
        Person person2 = new Person("3", "yangxiaoxiao", "46");
        Person person3 = new Person("4", "wangwenqi", "98");
        List<Person> list = new ArrayList<>();
        list.add(person1);
        list.add(person3);
        list.add(person2);

        list.sort(Comparator.comparingInt(o -> Integer.parseInt(o.getCertNo())));

        System.out.println(JSONObject.toJSONString(list));

    }

    static class Person{

        private String certNo;
        private String name;
        private String phoneNo;

        public Person(String certNo, String name, String phoneNo){
            this.certNo = certNo;
            this.name = name;
            this.phoneNo = phoneNo;
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
    }
}
