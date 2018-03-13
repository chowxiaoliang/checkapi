package com.zl.checkapi.pojo;

import org.springframework.stereotype.Component;

@Component
public class Company {
    private String name;

    private String location;

    private int age;

    public Company(){
        this.age = 1;
        this.location = "南山大道";
        this.name = "大数据金融服务平台";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
