package com.zl.checkapi.pojo;

import org.springframework.stereotype.Component;

@Component
public class People {

    private String certNo;

    private String name;

    private String mobile;

    private String address;

    public People(){
        this.address = "南山大道";
        this.certNo = "1234567894561230";
        this.mobile = "13227136694";
        this.name = "json";
    }

    public String sayMessage(){
        System.out.println("the address is "+address+",the certNo is "+certNo+",the mobile is "+mobile+",the name is "+name);
        return "baseInfo is printed successfully";
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
