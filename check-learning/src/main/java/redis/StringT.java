package redis;

import redis.clients.jedis.JedisCluster;
import java.util.List;

public class StringT {

    private static String prefix = "one:two";

    /**
     * 用于隔开缓存前缀与缓存键值
     */
    private static String KEY_SPLIT = ":";

    private static String nameKey = prefix + KEY_SPLIT + "name";

    public static void main(String[] args) throws InterruptedException {

        RedisOperatorJedisCluster redisOperatorJedisCluster = Config.getJedisCluster();
        JedisCluster jedisCluster = redisOperatorJedisCluster.getJedis();
        // set
        String result1 = jedisCluster.set("name", "zhou");
        System.out.println("result1 = " + result1);

        // set
        // @param nxxx NX|XX, NX -- Only set the key if it does not already exist. XX -- Only set the key
        //   *          if it already exist.
        //   * @param expx EX|PX, expire time units: EX = seconds; PX = milliseconds
        String result2 = jedisCluster.set("name", "zhou", "XX", "EX", 10);
        System.out.println("result2 = " + result2);

        // get
        String result3 = jedisCluster.get("name");
        System.out.println("result3 = " + result3);

        // exists
        boolean result4 = jedisCluster.exists("name");
        System.out.println("result4 = " + result4);

        // 多key的操作一定要遵循 crc16校验方式，前缀必须一致，这样就会使计算出来的slot是同一个
        long result5 = jedisCluster.exists("{name:}doge", "{name:}cat", "{name:}pig");
        System.out.println("result5 = " + result5);

        // persist 持久化一个key的value,给设置了过期时间的key持久话
        jedisCluster.set("result6", "result6");
        jedisCluster.expire("result6", 5);
//        TimeUnit.SECONDS.sleep(3);
        System.out.println("result6 = " + jedisCluster.get("result6"));
        long persistResult = jedisCluster.persist("result6");
        System.out.println("result6 = " + persistResult);

        // type 查看键所存储的值类型
        System.out.println("查看name存储的值类型：" + jedisCluster.type("name"));

        // expire 给key设置过期时间，默认是秒
        System.out.println("给name设置过期时间：" + jedisCluster.expire("name", 5));

        // pexpire 给key设置过期时间，单位是毫秒
        System.out.println("给name设置过期时间，单位是毫秒：" + jedisCluster.pexpire("name", 3000));

        // expireAt 给key设置过期时间，以unix的时间方式,10位时间戳的方式(单位是秒)
        System.out.println("给name设置过期时间，在给定的时间点过期：" + jedisCluster.expireAt("name", 1625455410));

        // pexpireAt 给key设置过期时间，以unix的方式，13位时间戳的方式(单位是毫秒)
        jedisCluster.set("age", "10");
        System.out.println("给age设置过期时间，在给定的时间点过期：" + jedisCluster.pexpireAt("age",1625467559000L));

        // ttl 查看键的剩余生存时间,返回值是秒(若小于0则已经到期)
        System.out.println("查看键的剩余生存时间是：" + jedisCluster.ttl("age"));

        // pttl 查看键的剩余生存时间，返回值是毫秒(若小于0则已经到期)
        System.out.println("查看键的剩余生存时间是：" + jedisCluster.pttl("age"));

        // setbit  Setbit命令用于对key所储存的字符串值，设置或清除指定偏移量上的位(bit)
        // 在redis中，存储的字符串都是以二进制的形式存在的
        // 引用：https://blog.csdn.net/hgd613/article/details/54095729
        jedisCluster.set("andy", "a");
        System.out.println("设置指定偏移量上的位：" + jedisCluster.setbit("andy", 2, "1"));
        System.out.println("设置指定偏移量上的位：" + jedisCluster.setbit("andy", 3, true));
        System.out.println("获取指定偏移量上的为：" + jedisCluster.getbit("andy", 3));

        // setrange 用指定的字符串覆盖给定key所储存的字符串值，覆盖的位置从偏移量offset开始
        System.out.println("根据偏移量覆盖旧值：" + jedisCluster.setrange("andy", 3, "应用层.txt"));

        // getrange

        // getSet

        // setnx

        // setex

        // psetex

        // decrBy 根据给定的值做减操作
        jedisCluster.set("height", "120");
        System.out.println("decrBy操作，结果为操作完后的值：" + jedisCluster.decrBy("height", 10));

        // decr 对key的value做减减操作
        System.out.println("decr操作，即是做减减操作：" + jedisCluster.decr("height"));

        // incrBy 根据给定的值做加操作
        jedisCluster.set("weight", "300");
        System.out.println("incrBy操作，结果为操作完后的值：" + jedisCluster.incrBy("weight", 50));

        // incr 对给定的值做加加操作
        System.out.println("incr操作，即是做加加操作：" + jedisCluster.incr("weight"));

        // append

        // substr

        // 批量操作
        String result = jedisCluster.mset("{" + prefix + KEY_SPLIT + "}" + "name", "张三", "{" + prefix + KEY_SPLIT + "}" + "age", "23", "{" + prefix + KEY_SPLIT + "}" + "address", "adfsa", "{" + prefix + KEY_SPLIT + "}" + "score", "100");
        System.out.println(result);

        String name = jedisCluster.get("{" + prefix + KEY_SPLIT + "}" + "name");
        System.out.println(name);

        Long del = jedisCluster.del("{" + prefix + KEY_SPLIT + "}" + "age");
        System.out.println(del);

        List<String> values = jedisCluster.mget("{" + prefix + KEY_SPLIT + "}" + "name", "{" + prefix + KEY_SPLIT + "}" + "age", "{" + prefix + KEY_SPLIT + "}" + "address");
        System.out.println(values);

    }
}
