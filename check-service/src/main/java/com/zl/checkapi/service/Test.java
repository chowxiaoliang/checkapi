package com.zl.checkapi.service;

import com.bqs.risk.serviceapi.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;

public class Test {

    @Autowired
    private TokenService tokenService;

    public void test(){
        String params = "{\"partnerId\":\"demo\",\"verifyKey\":\"8eed45f6d6897fa5ce98681d99edc09f\"}";
        String resultTest = tokenService.getToken(params);
        System.out.println(resultTest);
    }

    public static void main(String[] args){
        Test test = new Test();
        test.test();
    }

}
