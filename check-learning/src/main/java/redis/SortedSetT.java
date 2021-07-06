package redis;

import redis.clients.jedis.JedisCluster;

import java.util.HashMap;
import java.util.Map;

public class SortedSetT {

    public static void main(String[] args) {

        RedisOperatorJedisCluster redisOperatorJedisCluster = Config.getJedisCluster();
        JedisCluster jedisCluster = redisOperatorJedisCluster.getJedis();

        jedisCluster.del("zt1");
        // zadd
        jedisCluster.zadd("zt1", 2.4, "t1");
        jedisCluster.zadd("zt1", 3.1, "t2");
        jedisCluster.zadd("zt1", 1.0, "t3");
        jedisCluster.zadd("zt1", 7.9, "t4");
        jedisCluster.zadd("zt1", 10.8, "t3");

        System.out.println("zset里面所有的元素是：" + jedisCluster.zrange("zt1", 0,-1));

        Map<String, Double> map = new HashMap<>(5);
        map.put("key1", 1.2);
        map.put("key2", 2.4);
        map.put("key3", 6.2);
        map.put("key4", 3.5);

        jedisCluster.zadd("zmap", map );

        System.out.println("zset中的所有元素："+jedisCluster.zrange("zmap", 0, -1));
        System.out.println("zset中的所有元素："+jedisCluster.zrangeWithScores("zmap", 0, -1));
        System.out.println("zset中的所有元素："+jedisCluster.zrangeByScore("zmap", 0,100));
        System.out.println("zset中的所有元素："+jedisCluster.zrangeByScoreWithScores("zmap", 0,100));
        System.out.println("zset中key2的分值："+jedisCluster.zscore("zmap", "key2"));
        System.out.println("zset中key2的排名："+jedisCluster.zrank("zmap", "key2"));
        System.out.println("删除zset中的元素key3："+jedisCluster.zrem("zmap", "key3"));
        System.out.println("zset中的所有元素："+jedisCluster.zrange("zmap", 0, -1));
        System.out.println("zset中元素的个数："+jedisCluster.zcard("zmap"));
        System.out.println("zset中分值在1-4之间的元素的个数："+jedisCluster.zcount("zmap", 1, 4));
        System.out.println("key2的分值加上5："+jedisCluster.zincrby("zmap", 5, "key2"));
        System.out.println("key3的分值加上4："+jedisCluster.zincrby("zmap", 4, "key3"));
        System.out.println("zset中的所有元素："+jedisCluster.zrange("zmap", 0, -1));
    }
}
