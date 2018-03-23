package com.zl.checkapi.redis.redistest;

import com.alibaba.dubbo.container.spring.SpringContainer;
import com.alibaba.fastjson.JSONObject;
import com.zl.checkapi.redis.RedisOperatorJedisCluster;
import com.zl.checkapi.service.DataFromRedisService;
import com.zl.checkapi.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.ScanResult;
import redis.clients.jedis.Tuple;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author zhouliang
 */
@Service("dataFromRedisService")
@Path(value = "redis")
@Produces({MediaType.APPLICATION_JSON})
public class DataFromRedisServiceImpl implements DataFromRedisService {
    private final Logger logger = LoggerFactory.getLogger(DataFromRedisServiceImpl.class);
    @Path(value = "getData")
    @POST
    @Override
    public String getData(String type) {
        RedisOperatorJedisCluster redisOperatorJedisCluster = SpringContainer.getContext().getBean("redisOperatorJedisCluster", RedisOperatorJedisCluster.class);
        JedisCluster jedisCluster = redisOperatorJedisCluster.getJedis();
        if(Constants.Redis.SET.equals(type)){
            //String(set setex setnx)
            String a = jedisCluster.set("key:a", "value:a");
            jedisCluster.expire("key:a", 60);
            String b = jedisCluster.setex("key:b", 600, "vaule:b");
            long c = jedisCluster.setnx("key:c", "value:c");
            //List
            long d = jedisCluster.lpush("key:d", "java", "php", "js", "c");
            jedisCluster.expire("key:d", 60);
            //Map
            Map<String, String> map = new HashMap<>(16);
            map.put("name", "zhouliang");
            map.put("address", "GuangDong Province");
            map.put("mobile", "13227136694");
            String f = jedisCluster.hmset("key:f", map);
            //Set
            long g = jedisCluster.sadd("key:g", "aa","bb","cc","dd");
            //SortedSet
            long h = jedisCluster.zadd("key:h", 1, "zaa");
            jedisCluster.zadd("key:h", 2, "zbb");
            jedisCluster.zadd("key:h", 3, "zcc");
            jedisCluster.zadd("key:h", 4, "zdd");
            logger.info("set result a=>{}", a);
            logger.info("set result b=>{}", b);
            logger.info("set result c=>{}", c);
            logger.info("set result d=>{}", d);
            logger.info("set result f=>{}", f);
            logger.info("set result g=>{}", g);
            logger.info("set result h=>{}", h);
            return "set success";
        }
        if(Constants.Redis.GET.equals(type)){
            //String(set setex setnx)
            String a = jedisCluster.get("key:a");
            String b = jedisCluster.get("key:b");
            String c = jedisCluster.get("key:c");
            //-1代表倒数第一个
            List<String> d = jedisCluster.lrange("key:d",0,-1);
            Map<String, String> f = jedisCluster.hgetAll("key:f");
            //Set中所有元素
            Set<String> g = jedisCluster.smembers("key:g");
            //SortedSet中所有元素(-1代表倒数第一个)
            Set<String> h = jedisCluster.zrange("key:h", 0, -1);
            //SCAN、SSCAN、HSCAN、ZSCAN是遍历集合元素的命令。
            //SCAN：遍历选中的Redis数据库的集合，相当于全库扫描。
            //SSCAN：遍历Sets类型的元素。
            //HSCAN：遍历Hash类型及对应的value。
            //ZSCAN：遍历Sorted Set类型和对应的scores。
            //游标一般从0开始
            ScanResult<Tuple> result = jedisCluster.zscan("key:h", "3");
            List<Tuple> list = result.getResult();
            System.out.println("tuple list=>" +JSONObject.toJSONString(list));
            logger.info("get result a=>{}", a);
            logger.info("get result b=>{}", b);
            logger.info("get result c=>{}", c);
            logger.info("get result d=>{}", JSONObject.toJSONString(d));
            logger.info("get result f=>{}", JSONObject.toJSONString(f));
            logger.info("get result g=>{}", JSONObject.toJSONString(g));
            logger.info("get result h=>{}", JSONObject.toJSONString(h));
            return "get success";
        }
        return null;
    }
}
