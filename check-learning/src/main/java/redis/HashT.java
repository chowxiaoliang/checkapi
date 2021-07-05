package redis;

import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.JedisCluster;

import java.util.HashMap;
import java.util.Map;

public class HashT {

    public static void main(String[] args) {

        RedisOperatorJedisCluster redisOperatorJedisCluster = Config.getJedisCluster();
        JedisCluster jedisCluster = redisOperatorJedisCluster.getJedis();

        // hset 对指定的key设置单个field的值

        // As per Redis 4.0.0, HMSET is considered deprecated. Please use HSET in new code.
        // 根据Redis 4.0.0，HMSET被视为已弃用。请在新代码中使用HSET

        System.out.println("hset设置值certNo：" + jedisCluster.hset("map:test", "certNo", "123456"));
        System.out.println("hset设置值name：" +jedisCluster.hset("map:test", "name", "yangxiaoxiao"));
        System.out.println("hset设置值mobile：" + jedisCluster.hset("map:test", "mobile", "13227136694"));

        Map<String, String> resultMap = jedisCluster.hgetAll("map:test");
        System.out.println("获取的结果是：" + JSONObject.toJSONString(resultMap));

        // hget 对指定的key获取单个field的值
        System.out.println("通过hget获取指定key的field的值：" + jedisCluster.hget("map:test", "name"));

        // hsetnx 对给定的key的field设置值，如果不存在则set并返回1，如果存在则不set并返回0
        System.out.println("通过hsetnx设置给定key的field的值：" + jedisCluster.hsetnx("map:test", "name", "wangwenqi"));

        // hmset 直接设置整个map对象
        Map<String, String> map = new HashMap<>(16);
        map.put("certNo", "123456");
        map.put("name", "yangxiaoxiao");
        map.put("mobile", "13227136694");
        map.put("age", "10");
        System.out.println("使用hmset设置hash值：" + jedisCluster.hmset("hmset:test", map));
        System.out.println("获取的结果是：" + JSONObject.toJSONString(jedisCluster.hgetAll("hmset:test")));

        // hmget 批量获取key指定的field的值
        System.out.println("通过hmget批量获取key指定的field的值：" + jedisCluster.hmget("hmset:test", "certNo", "name"));

        // hincrBy
        System.out.println("通过hincrBy增加指定field的值：" + jedisCluster.hincrBy("hmset:test", "age", 5));

        // hincrByFloat
        System.out.println("通过hincrByFloat增加指定field的值：" + jedisCluster.hincrByFloat("hmset:test", "age", 2.5));

        // hexists 判断指定key的field是否存在
        System.out.println("判断指定key的field是否存在：" + jedisCluster.hexists("hmset:test", "name"));

        // hdel 删除指定key的filed
        System.out.println("删除指定key的field：" + jedisCluster.hdel("hmset:test", "name"));

        // hlen 返回指定key的hash对象的长度
        System.out.println("获取指定key的hash对象的长度" + jedisCluster.hlen("hmset:test"));

        // hkeys 返回指定key的hash对象的所有key
        System.out.println("获取指定key的hash对象的所有key：" + jedisCluster.hkeys("hmset:test"));

        // hvals 返回指定key的hash对象的所有value
        System.out.println("获取指定key的hash对象的所有value：" + jedisCluster.hvals("hmset:test"));

    }
}
