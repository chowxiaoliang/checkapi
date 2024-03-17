//package com.zl.checkapi.redis.redistest;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.util.StringUtils;
//import redisson.clients.jedis.JedisCluster;
//
//import java.util.concurrent.TimeUnit;
//
///**
// * @author lenovo
// */
//
//class DistributeLock {
//    private final Logger logger = LoggerFactory.getLogger(DistributeLock.class);
//
//    private String lockName;
//
//    private TimeUnit timeUnit;
//
//    private Long expireTime;
//
//    private JedisCluster jedisCluster;
//
//    public DistributeLock(String lockName, TimeUnit timeUnit, Long expireTime, JedisCluster jedisCluster){
//        this.lockName = lockName;
//        this.timeUnit = timeUnit;
//        this.expireTime = expireTime;
//        this.jedisCluster = jedisCluster;
//    }
//
//    boolean trylock(){
//        try{
//            if(StringUtils.isEmpty(lockName)){
//                logger.error("lockName should not be empty");
//                return false;
//            }
//            long result = jedisCluster.setnx(lockName, getLockVal(lockName, timeUnit, expireTime));
//            if(result==0){
//                //判断是否是死锁（当前时刻大于上次上锁时刻加上过期时间则为死锁）
//                String val = jedisCluster.get(lockName);
//                long lastTimeMillis = Long.valueOf(val.split(":")[1]);
//                if(System.currentTimeMillis() > lastTimeMillis){
//                    //死锁 删除key 解除锁
//                    unlock(lockName);
//                    //重新上锁
//                    jedisCluster.setex(lockName, (int)timeUnit.toSeconds(expireTime), getLockVal(lockName, timeUnit, expireTime));
//                    return true;
//                }
//            }
//            if(result==1){
//                //key不存在
//                jedisCluster.expire(lockName, (int)timeUnit.toSeconds(expireTime));
//                return true;
//            }
//        }catch (Exception e){
//            logger.error("get lock error", e);
//        }
//        return false;
//    }
//
//    private void unlock(String lockName){
//        if(StringUtils.isEmpty(lockName)){
//            logger.error("lockName should not be empty");
//            return;
//        }
//        jedisCluster.del(lockName);
//    }
//
//    /**
//     * value结构是锁名称：当前时间+过期时间（毫秒）
//     * @param lockName
//     * @param timeUnit
//     * @param expireTime
//     * @return
//     */
//    private String getLockVal(String lockName, TimeUnit timeUnit, Long expireTime){
//        if (StringUtils.isEmpty(lockName)) {
//            logger.error("lockName should not be empty");
//            return null;
//        }
//        return lockName +":" + System.currentTimeMillis() + timeUnit.toMillis(expireTime);
//    }
//}
