package redis;

import redis.clients.jedis.JedisCluster;

import java.util.ArrayList;
import java.util.List;

public class SetT {

    public static void main(String[] args) {

        RedisOperatorJedisCluster redisOperatorJedisCluster = Config.getJedisCluster();
        JedisCluster jedisCluster = redisOperatorJedisCluster.getJedis();

        jedisCluster.del("num");
        // sadd 向set里面添加元素，如果之前已经有则会过滤掉
        System.out.println("向set里面添加元素：" + jedisCluster.sadd("num", "11","21","31","41","51"));

        // smembers 获取set里面的元素
        System.out.println("获取指定key里面的元素：" + jedisCluster.smembers("num"));

        // srem 删除set里面指定的元素
        System.out.println("移除set里面的元素：" + jedisCluster.srem("num", "5"));

        // spop 随机的移除set里面的一个元素
        System.out.println("随机的移除set里面的一个元素：" + jedisCluster.spop("num"));
        System.out.println(jedisCluster.smembers("num"));

        // 多一个参数
//        jedisCluster.spop("num", 12);
        System.out.println(jedisCluster.smembers("num"));

        jedisCluster.del("t");
        String[] strings = new String[3];
        strings[0] = "a";
        strings[1] = "b";
        strings[2] = "c";
        jedisCluster.sadd("t", strings);

        // scard 统计set里面元素的个数
        System.out.println("set里面元素的个数是：" + jedisCluster.scard("t"));

        // sismember
        System.out.println("判断元素是否存在：" + jedisCluster.sismember("t", "a"));

        jedisCluster.del("{test:t}:t1");
        jedisCluster.del("{test:t}:t2");
        jedisCluster.sadd("{test:t}:t1", "1","2","3","4");
        jedisCluster.sadd("{test:t}:t2", "4","5","6","7");
        // smove 将元素从一个set移动到另一个set
        jedisCluster.smove("{test:t}:t1", "{test:t}:t2", "1");
        System.out.println(jedisCluster.smembers("{test:t}:t1"));
        System.out.println(jedisCluster.smembers("{test:t}:t2"));

        // sinter 求多个set的交集
        System.out.println("求两个set的交集是：" + jedisCluster.sinter("{test:t}:t1","{test:t}:t2"));

        // sunion 求多个set的并集
        System.out.println("求两个set的并集是：" + jedisCluster.sunion("{test:t}:t1","{test:t}:t2"));

        // sdiff 求多个set的差集
        System.out.println("求两个set的差集是：" + jedisCluster.sdiff("{test:t}:t1","{test:t}:t2"));
    }
}
