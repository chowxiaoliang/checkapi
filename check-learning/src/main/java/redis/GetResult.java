package redis;

import redis.clients.jedis.JedisCluster;

public class GetResult {

    public static void main(String[] args) {

        RedisOperatorJedisCluster redisOperatorJedisCluster = Config.getJedisCluster();
        JedisCluster jedisCluster = redisOperatorJedisCluster.getJedis();

        String result = jedisCluster.get("height");
        System.out.println("获取指定key的值：" + result);

        System.out.println(jedisCluster.hget("hmset:test", "name"));
    }
}
