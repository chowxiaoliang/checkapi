package redisson;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.LZ4Codec;
import org.redisson.config.Config;
import org.redisson.config.ReadMode;
import org.redisson.config.TransportMode;
import org.redisson.connection.balancer.RoundRobinLoadBalancer;

import java.util.HashSet;
import java.util.Set;

public class MyRedisson {

    private static RedissonClient redissonClient;

    private MyRedisson(){}

    public static RedissonClient getRedissonClient(){

        if (redissonClient == null){
            synchronized (MyRedisson.class){

                if (redissonClient == null){
                    redissonClient = Redisson.create(createConfig());
                }
            }
        }

        return redissonClient;
    }



    private static Config createConfig(){
        Set<String> slaveSet = new HashSet<>();
        slaveSet.add("redis://192.168.9.31:6380");
        Config config = new Config();
        config.setCodec(new LZ4Codec());
        config.setTransportMode(TransportMode.NIO);

        config.useMasterSlaveServers().setTimeout(3000)
                .setConnectTimeout(5000).setDatabase(0)
                .setReadMode(ReadMode.SLAVE).setRetryAttempts(3)
                .setLoadBalancer(new RoundRobinLoadBalancer())
                .setIdleConnectionTimeout(10000)
                .setMasterAddress("redis://192.168.9.31:6379")
                .setSlaveAddresses(slaveSet);

        return config;

    }
}
