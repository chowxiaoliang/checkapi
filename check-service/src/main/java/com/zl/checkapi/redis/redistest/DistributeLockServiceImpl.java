package com.zl.checkapi.redis.redistest;

import com.zl.checkapi.redis.RedisOperatorJedisCluster;
import com.zl.checkapi.service.DistributeLockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lenovo
 */
@Path("redis")
@Service("distributeLockServiceImpl")
public class DistributeLockServiceImpl implements DistributeLockService {
    @Autowired
    private RedisOperatorJedisCluster redisOperatorJedisCluster;

    private final Logger logger = LoggerFactory.getLogger(DistributeLockServiceImpl.class);

    private AtomicInteger atomicInteger = new AtomicInteger(0);
    @Path("testLock")
    @POST
    @Override
    public String testLock(String lockName) {
        DistributeLock distributeLock = new DistributeLock(lockName, TimeUnit.SECONDS, 60L, redisOperatorJedisCluster.getJedis());
        if(distributeLock.trylock()){
            atomicInteger.incrementAndGet();
            logger.info("lockName=>{} get lock success", lockName);
            System.out.println("总共第"+atomicInteger.get()+"次获得锁,lockName是"+lockName);
            return "get lock success";
        }
        return "get lock fail";
    }
}
