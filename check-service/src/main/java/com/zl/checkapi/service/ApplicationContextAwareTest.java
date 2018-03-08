package com.zl.checkapi.service;

import com.zl.checkapi.pojo.People;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextAwareTest implements ApplicationContextAware{


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("spring容器加载后就执行这个方法了");
        People people = applicationContext.getBean("people", People.class);
        String address = people.getAddress();
        System.out.println("实现ApplicationContextAware得到bean people的地址是=>"+address);
    }
}
