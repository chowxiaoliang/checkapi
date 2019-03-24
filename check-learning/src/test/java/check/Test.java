package check;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Comparator;

public class Test {
    public static void main(String[] args) {
        ArrayList<People> list = new ArrayList();
        People people = new People();
        people.setCertNo("123456");
        people.setName("zhouliang");
        people.setMobile("15872151893");
        people.setAge(10);
        list.add(people);

        People people1 = new People();
        people1.setName("xiaoliang");
        people1.setMobile("123456");
        people1.setAge(11);
        list.add(people1);

        System.out.println("before execute => " + JSONObject.toJSONString(list));

        MyComparator myComparator = new MyComparator();
        System.out.println(myComparator.compare(people1, people));
        list.sort(myComparator);

        System.out.println("after execute => " + JSONObject.toJSONString(list));
    }

    static class MyComparator implements Comparator {

        @Override
        public int compare(Object o1, Object o2) {
            People people1 = (People)o1;
            People people2 = (People)o2;
            if(people1.getAge() < people2.getAge()){
                return 1;
            }
            return 0;
        }
    }
    static class People {

        int age;

        String certNo;

        String name;

        String mobile;

        static void sayMessage(){
            System.out.println("this is TestMain message ! ");
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
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

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
    }
}
