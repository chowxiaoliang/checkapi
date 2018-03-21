package com.zl.checkapi.redis.redistest;

import com.alibaba.dubbo.container.spring.SpringContainer;
import com.zl.checkapi.redis.RedisOperatorJedisCluster;
import org.springframework.stereotype.Component;

@Component
public class TestOne {
    RedisOperatorJedisCluster redisOperatorJedisCluster = SpringContainer.getContext().getBean("redisOperatorJedisCluster", RedisOperatorJedisCluster.class);

    public static void main(String[] args){

        String ip = redisOperatorJedisCluster.getIp();
        System.out.println(ip);
    }
}
