package com.zl.checkconsumer;

import org.springframework.stereotype.Component;

/**
 * Created by lenovo on 2017/9/13.
 */
@Component
public class CommonBean {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private String name;

    private String sex;

    private int age;
}
