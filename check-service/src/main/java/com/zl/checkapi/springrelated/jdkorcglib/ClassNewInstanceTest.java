package com.zl.checkapi.springrelated.jdkorcglib;

/**
 * @author zhouliang
 * @since 2018-04-24 11:10
 **/
public class ClassNewInstanceTest {
    public static void main(String[] args) {
        Class<?> clazz = InnerClass.class;
        System.out.println(clazz.getPackage());
        System.out.println(CglibProxyTest.class);
    }
    class InnerClass{
        private String name;

        @Override
        public String toString() {
            return "InnerClass{}";
        }

        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
