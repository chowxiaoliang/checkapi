package com.zl.checkapi.springrelated.jdkorcglib;

import com.zl.checkapi.pojo.People;

public class CglibProxyTest {
    public static void main(String[] args){
        People people = new People();
        MyMethodIntercepretor myMethodIntercepretor = new MyMethodIntercepretor(people);
        People peopleProxy = (People) myMethodIntercepretor.getProxyObject(People.class);
        peopleProxy.sayMessage();
    }
}
