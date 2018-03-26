package com.zl.checkapi.springrelated.jdkorcglib;

import java.lang.annotation.Annotation;

public class JdkProxyTest {
    public static void main(String[] args){
        UserService userService = new UserServiceImpl();
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler(userService);
        UserService proxy = (UserService)myInvocationHandler.getPorxyObject();
        proxy.basicInfo("123456789", "yangxiaoxiao", "13227136694");
        proxy.sayMessage("today the weather if nice");
        proxy.toString();
        Annotation[] annotation = userService.getClass().getAnnotations();
        for(Annotation str : annotation){
            System.out.println("annotation is = >"+str);
        }
    }
}
