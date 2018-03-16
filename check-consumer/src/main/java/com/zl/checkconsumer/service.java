package com.zl.checkconsumer;

import com.zl.checkapi.service.CheckCaseService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by lenovo on 2017/9/13.
 */
public class service {

    public static void main(String[] args){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("application.xml");
        CheckCaseService checkCaseService = ctx.getBean("checkCaseServiceImpl2", CheckCaseService.class);
        String result = checkCaseService.checkCase("zhouliang");
        System.out.println("execute start...");
        System.out.println(result);

    }
}

