package com.zl.checkapi.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author zhouliang
 * @param 
 */
@Component
public class SpringContext implements ApplicationContextAware {
    private static ApplicationContext applicationContext;
    public SpringContext(){
    }
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContext.applicationContext = applicationContext;
    }
    public static <T> T getBean(String beanName, Class<T> requiredType){
        return applicationContext.getBean(beanName, requiredType);
    }
    public static <T> T getBean(Class<T> requiredType){
        return applicationContext.getBean(requiredType);
    }
}
