package redis;

public class Config {

    private final static String[] IP = {"192.168.9.20","192.168.9.20","192.168.9.20","192.168.9.20","192.168.9.20","192.168.9.20"};
    private final static int[] PORT = {6380,6381,6382,6383,6384,6385};

    private Config(){}

    private volatile static RedisOperatorJedisCluster redisOperatorJedisCluster = null;

    public static RedisOperatorJedisCluster getJedisCluster(){

        if (redisOperatorJedisCluster == null){

            synchronized (Config.class){

                if (redisOperatorJedisCluster == null){

                    redisOperatorJedisCluster = new RedisOperatorJedisCluster(IP, PORT, 1000,1000,
                            3000,"true", 3000, 3000, 1000);
                }
            }
        }
        return redisOperatorJedisCluster;
    }
}
