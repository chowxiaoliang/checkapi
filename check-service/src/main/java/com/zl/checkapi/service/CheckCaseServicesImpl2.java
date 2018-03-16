package com.zl.checkapi.service;

import org.springframework.stereotype.Service;

@Service(value = "checkCaseServiceImpl2")
public class CheckCaseServicesImpl2 implements CheckCaseService {
    @Override
    public String checkCase(String req) {
        System.out.println("this is the second class implements interface=>"+req);
        return req;
    }
}
