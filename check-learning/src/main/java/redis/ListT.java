package redis;

import redis.clients.jedis.JedisCluster;

public class ListT {

    public static void main(String[] args) {

        RedisOperatorJedisCluster redisOperatorJedisCluster = Config.getJedisCluster();
        JedisCluster jedisCluster = redisOperatorJedisCluster.getJedis();

        jedisCluster.del("char");
        // lpush 在key对应list的头部添加字符串元素
        System.out.println("在key对应的list的头部添加第一个字符串：" + jedisCluster.lpush("char", "d"));
        System.out.println("在key对应的list的头部添加第二个字符串：" + jedisCluster.lpush("char", "c"));
        System.out.println("在key对应的list的头部添加第三个字符串：" + jedisCluster.lpush("char", "b"));
        System.out.println("在key对应的list的头部添加第四个字符串：" + jedisCluster.lpush("char", "a"));

        System.out.println(jedisCluster.lrange("char", 0, 100));

        jedisCluster.del("charA");
        // rpush 在key对应list的尾部添加字符串元素
        System.out.println("在key对应的list的尾部添加第一个字符串：" + jedisCluster.rpush("charA", "A"));
        System.out.println("在key对应的list的尾部添加第二个字符串：" + jedisCluster.rpush("charA", "B"));
        System.out.println("在key对应的list的尾部添加第三个字符串：" + jedisCluster.rpush("charA", "C"));
        System.out.println("在key对应的list的尾部添加第四个字符串：" + jedisCluster.rpush("charA", "D"));
        jedisCluster.rpush("charA", "1", "2");

        System.out.println(jedisCluster.lrange("charA", 0, 100));

        // llen
        System.out.println("length of the list is：" + jedisCluster.llen("charA"));

        // ltrim 截取start和end之间的元素使之成为一个新的list
        System.out.println("获取两个游标之间的元素：" + jedisCluster.ltrim("charA", 0, 2));
        System.out.println(jedisCluster.lrange("charA", 0, 100));

        // lindex 获取指定游标的元素
        System.out.println("获取指定游标的元素：" + jedisCluster.lindex("charA", 2));

        // lset 对指定的游标设置值(不能超过list的大小)
        System.out.println("对指定的游标设置值：" + jedisCluster.lset("charA", 2, "D"));
        System.out.println(jedisCluster.lrange("charA", 0, 100));

        // lrem (remove)
        // 根据参数 COUNT 的值，移除列表中与参数 VALUE 相等的元素。效率较低，慎用
        // count 的值可以是以下几种：
        // count > 0 : 从表头开始向表尾搜索，移除与 VALUE 相等的元素，数量为 COUNT 。
        // count < 0 : 从表尾开始向表头搜索，移除与 VALUE 相等的元素，数量为 COUNT 的绝对值。
        // count = 0 : 移除表中所有与 VALUE 相等的值。
//        jedisCluster.lrem()

        // lpop 从list的头部出队
        System.out.println("从list的头部出队：" + jedisCluster.lpop("char"));
        System.out.println(jedisCluster.lrange("char", 0, 100));

        // rpop 从list的尾部出队
        System.out.println("从list的尾部出队：" + jedisCluster.rpop("char"));
        System.out.println(jedisCluster.lrange("char", 0, 100));
    }
}
