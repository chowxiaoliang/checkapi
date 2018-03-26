package com.zl.checkapi.springrelated.jdkorcglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MyMethodIntercepretor implements MethodInterceptor {

    private Object target;

    private Enhancer enhancer = new Enhancer();

    public MyMethodIntercepretor(Object object){
        this.target = object;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("enter into intercept");
        Object object = methodProxy.invokeSuper(o, objects);
        System.out.println("the result is => "+object);
        return "success";
    }

    public Object getProxyObject(Class clazz){
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return enhancer.create();
    }
}
