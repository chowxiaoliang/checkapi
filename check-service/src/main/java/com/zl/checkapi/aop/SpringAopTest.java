package com.zl.checkapi.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author zhouliang
 */
@Component
@Aspect
public class SpringAopTest {
    private final Logger logger = LoggerFactory.getLogger(SpringAopTest.class);

    @After(value = "execution(* com.zl.checkapi.service..*.*(..))")
    public void logMethod(JoinPoint joinPoint){
        logger.info("enter aop method");
        String str = String.valueOf(joinPoint.getArgs()[0]);
        System.out.println(str);
        logger.info("exit aop method");
    }

}
