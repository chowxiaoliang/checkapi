package com.zl.checkapi.redis;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redisson.clients.jedis.JedisCluster;

import java.net.InetAddress;
import java.util.concurrent.TimeUnit;


/**
 * 使用Redis实现分布式调度器全局锁
 * 保证在启动多个dubbo服务的时候，一个时间段类只能有一个定时任务在执行
 * 
 * 在一段时间内，执有锁
 * @author hujun 2017.3.25
 *
 */
public class RedisDistributeSchedulerLock {
	
	private static Logger log = LoggerFactory.getLogger(RedisDistributeSchedulerLock.class);
	private static String hostId = null;
	private static final String LOCK_PREFIX = "lock:distribute:scheduler:";
	
	private String lockName;
	private JedisCluster jedis;
	
	
	/**
	 * 
	 * @param lockName 同一个名称表示同一把锁
	 * @param jedis
	 */
	public RedisDistributeSchedulerLock(String lockName,JedisCluster jedis){
		
		if(StringUtils.isBlank(lockName)){
			throw new NullPointerException("lockName is Null");
		}
		this.lockName = LOCK_PREFIX + lockName;
		this.jedis = jedis;
	}
	
	/**
	 * 仅在调用时锁为空闲状态才获取该锁，
	 * 如获取成功，会保持锁的时长（传入时间参数）
	 * 如果锁可用，则获取锁，并立即返回值 true。
	 * 如果锁不可用，则此方法将立即返回值 false。 
	 */
	public boolean tryLock(long time,TimeUnit unit){
		
		String lockValue = jedis.get(lockName);
		
		if(lockValue == null){
			long rs = jedis.setnx(lockName, getHostname());
			if(rs == 1L){
				jedis.expire(lockName, (int)unit.toSeconds(time));
				return true;
			}
		}else if(lockValue.equals(getHostname())){
			jedis.setex(lockName, (int)unit.toSeconds(time), getHostname());
			return true;
		}
		
		return false;
		
	}
	
	private static String getHostname(){
		
		if(hostId != null){
			return hostId;
		}
		
		String hostName = "";
		try {
			InetAddress address = InetAddress.getLocalHost();
			if(address==null){
				log.warn("InetAddress.getLocalHost() is null ");
				hostName = "unknow-host-"+ RandomUtils.nextInt(10000,99999);
			}else{
				hostName = address.getHostAddress();
				log.info("hostip:{},hostname:{}",address.getHostAddress(),address.getHostName());
				if(StringUtils.isEmpty(hostName) || "localhost".equals(hostName) || "127.0.0.1".equals(hostName)){
					hostName = address.getHostName();
				}
				if(StringUtils.isEmpty(hostName)){
					hostName = "unknow-host-"+RandomUtils.nextInt(10000,99999);
				}
			}
		} catch (Exception e) {
			log.error("unknow host error!,init host ID failed! =>"+e.getMessage());
			hostName = "unknow-host-"+(e.getMessage().length()>32?e.getMessage().substring(0,30):e.getMessage());
		}
		hostId = hostName;
		return hostName;
	}
	
}
