package com.zl.checkapi.springrelated.jdkorcglib;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public String basicInfo(String certNo, String name, String mobile) {
        System.out.println("the certNo is "+certNo+",the name is "+name+",the mobile is "+mobile);
        return certNo;
    }

    @Override
    public String sayMessage(String message){
        System.out.println("the message is printed "+message);
        return message;
    }

    @Override
    public String toString(){
        System.out.println("override toString method");
        return "toString method is overrided";
    }
}
