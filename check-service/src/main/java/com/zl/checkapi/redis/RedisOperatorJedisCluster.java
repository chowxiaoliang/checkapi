package com.zl.checkapi.redis;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redisson.clients.jedis.HostAndPort;
import redisson.clients.jedis.JedisCluster;
import redisson.clients.jedis.JedisPool;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 操作Redis集群
 * @author gaoyang1
 *
 */
public class RedisOperatorJedisCluster implements RedisOperator {
	private String ip;
	private int port;
	private int maxTotal;
	private int maxIdle;
	private int maxWaitMillis;
//	private int callTime;
	
	private boolean testOnIdle;  //是否checkIdle
	private int timeCheckIdle;  //每隔多少秒check一次
	private int idleTimeout;     //超时时间
	private int numTestsPerEvictionRun;  //一次驱逐过程中，最多驱逐对象的个数
	
	private JedisCluster jedis;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RedisOperatorJedisCluster.class);
	
	public RedisOperatorJedisCluster(String ip,int port,int maxTotal,int maxIdle,int maxWaitMillis,
								String strTestOnIdle,int timeCheckIdle,int idleTimeout,int numTestsPerEvictionRun){
		this.ip = ip;
		this.port = port;
		this.maxTotal = maxTotal;
		this.maxIdle = maxIdle;
		this.maxWaitMillis = maxWaitMillis;
		
		//check idle
		if (strTestOnIdle.equals("true"))
			this.setTestOnIdle(true);
		else
			this.setTestOnIdle(false);

		this.setTimeCheckIdle(timeCheckIdle);
		this.setIdleTimeout(idleTimeout);
		this.setNumTestsPerEvictionRun(numTestsPerEvictionRun);

		init();
	}
	
	private synchronized void init(){
		Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
		
		try {
			jedisClusterNodes.add(new HostAndPort(ip, port));
			
			GenericObjectPoolConfig config = new GenericObjectPoolConfig();
			config.setMaxTotal(maxTotal);
			config.setMaxIdle(maxIdle);
			config.setMaxWaitMillis(maxWaitMillis);
			
			config.setTestWhileIdle(testOnIdle);
			config.setTimeBetweenEvictionRunsMillis(this.timeCheckIdle*1000);
			config.setMinEvictableIdleTimeMillis(this.idleTimeout*1000);
			config.setNumTestsPerEvictionRun(this.numTestsPerEvictionRun);
			
			if(jedis != null)
				jedis.close();
			
			jedis = new JedisCluster(jedisClusterNodes,config);
			
			Map<String, JedisPool> clusterNodeInfo = jedis.getClusterNodes();
			if(clusterNodeInfo != null)
				LOGGER.info("init.clusterNodeInfo:" + clusterNodeInfo.keySet());
						
			LOGGER.info("RedisOperatorJedisCluster init success");
		} catch (Exception e) {
			LOGGER.error("init fail",e);
		}
		
		
	}
	
	@Override
	public void insertKeyValue(String key, String value,long expireTime,TimeUnit timeUnit) {
		if(value == null){
			LOGGER.warn("insertKeyValue.value==null. key={}",key);
			return;
		}
		
		try {
//			jedis.set(key, value);
//			jedis.expire(key, expireMinutes*60);
			
			if(expireTime > 0)
				jedis.setex(key, (int)timeUnit.toSeconds(expireTime), value);
			else
				jedis.set(key, value);
			
		} catch (Exception e) {
			LOGGER.error("insertKeyValue fail",e);
//			init();
		}
		
//		callTime+=2;
//		LOGGER.info("insertKeyValue call:" + callTime);
	}
	

	@Override
	public long insertKeyValueIfNotExist(String key, String value,long expireTime,TimeUnit timeUnit) {		
		if(value == null){
			LOGGER.warn("insertKeyValueIfNotExist.value==null. key={}",key);
			return 0;
		}
		
		try {
			long ret = jedis.setnx(key, value);
			if(expireTime > 0){
				jedis.expire(key, (int)timeUnit.toSeconds(expireTime));
			}
			
			return ret;
		} catch (Exception e) {
			LOGGER.error("insertKeyValueIfNotExist fail",e);
//			init();
			return -1;
		}
	}

	@Override
	public boolean insertHash(String key, String field, String value,long expireTime,TimeUnit timeUnit) {
		try {
			jedis.hset(key, field, value);
			
			if(expireTime > 0)
				jedis.expire(key, (int)timeUnit.toSeconds(expireTime));
			
			return true;
		} catch (Exception e) {
			LOGGER.error("insertHash fail",e);
			
			return false;
		}
		
//		callTime+=2;
//		LOGGER.info("insertHash call:" + callTime);
	}

	@Override
	public void insertSortedSet(String key, double score, String value,long beginTime,long expireTime,TimeUnit timeUnit) {
		try {
			jedis.zadd(key, score, value);
			
			//整个SortedSet的生存时间为expireTime
			if(expireTime > 0){
				//保持SortedSet里只保留最近expireTime的记录
				jedis.zremrangeByScore(key, 0, (beginTime-timeUnit.toMillis(expireTime)));
				jedis.expire(key, (int)timeUnit.toSeconds(expireTime));
			}
		} catch (Exception e) {
			LOGGER.error("insertSortedSet fail",e);
//			init();
		}
		
//		callTime+=3;
//		LOGGER.info("insertSortedSet call:" + callTime);
	}
	
	public void insertSortedSet(String key, double score, String value) {
		
		jedis.zadd(key, score, value);
			
	}

	@Override
	public void insertSet(String key, String value,long expireTime,TimeUnit timeUnit) {
		try {
			jedis.sadd(key, value);
			
			if(expireTime > 0)
				jedis.expire(key, (int)timeUnit.toSeconds(expireTime));
		} catch (Exception e) {
			LOGGER.error("insertSet fail",e);
//			init();
		}
		
//		callTime+=2;
//		LOGGER.info("insertSet call:" + callTime);
	}

	@Override
	public void deleteKeyValue(String key) {
		try {
			jedis.del(key);
		} catch (Exception e) {
			LOGGER.error("deleteKeyValue fail",e);
//			init();
		}
		
//		callTime++;
//		LOGGER.info("deleteKeyValue call:" + callTime);
	}

	@Override
	public String getValueByKey(String key) {
		try {
			String ret = jedis.get(key);
			
//			callTime++;
//			LOGGER.info("getValueByKey call:" + callTime);
			
			return ret;
		} catch (Exception e) {
			LOGGER.error("getValueByKey fail",e);
//			init();
		}
		
//		callTime++;
//		LOGGER.info("getValueByKey call:" + callTime);
		return null;
		
	}

	@Override
	public Set<String> SortedSetRangeByScore(String key, double beginScore,
			double endScore) {
		try {
			Set<String> ret = jedis.zrangeByScore(key, beginScore, endScore);
//			System.out.println("SortedSetRangeByScore|||key" + key + "|||begin:" + beginScore + "|||end:" + endScore + "|||" + ret.toString());
			
//			callTime++;
//			LOGGER.info("SortedSetRangeByScore call:" + callTime);
			
			return ret;
		} catch (Exception e) {
			LOGGER.error("SortedSetRangeByScore fail",e);
//			init();
		}
		
//		callTime++;
//		LOGGER.info("SortedSetRangeByScore call:" + callTime);

		
		return null;
	}

	@Override
	public void setKeyExpireTime(String key, int seconds) {
		try {
			jedis.expire(key, seconds);
		} catch (Exception e) {
			LOGGER.error("setKeyExpireTime fail",e);
//			init();
		}
		
//		callTime++;
//		LOGGER.info("setKeyExpireTime call:" + callTime);

	}

	@Override
	public void SortedSetInter(String key1, String key2, String retSet) {
		// TODO Auto-generated method stub
		
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	@Override
	public String getset(String key, String value, long expireTime, TimeUnit timeUnit) {
		try {
			String ret = jedis.getSet(key, value);
			if(expireTime > 0)
				jedis.expire(key, (int)timeUnit.toSeconds(expireTime));
			
			return ret;
		} catch (Exception e) {
			LOGGER.error("getset fail",e);
		}
		
		
		return null;
	}
	
	@Override
	public Set<String> queryInSortedSet(List<String> keys,long beginTime,long timeSpan,TimeUnit timeUnit) {
		//至少一个条件进行查询
		if(keys.size() <= 0)
			return null;
		
		long tmBegin = beginTime - timeUnit.toMillis(timeSpan);
		long tmEnd = beginTime;

		
		//如果只有一个查询条件，直接获取即可
		if(keys.size() == 1){
			Set<String> ret = SortedSetRangeByScore(keys.get(0), tmBegin, tmEnd);
			return ret;
		}
		
		
		Set<String> result = null;
//		boolean bFirst = true;
		for (String key : keys) {
			Set<String> tmp = SortedSetRangeByScore(key, tmBegin, tmEnd);

			if (tmp == null)  //tmp为null，说明调用出现异常了
				return null;
			
			if(tmp.size() <= 0)  //只要出现SortedSet.size==0的，就返回一个空set
				return new LinkedHashSet<String>();

			if (result == null)
				result = tmp;
			else {
				result.retainAll(tmp);

				if (result.size() <= 0)
					return new LinkedHashSet<String>();
			}

		}
		
		return result;
	}

	@Override
	public Set<String> queryInSortedSet(String key1, String key2,long beginTime,long timeSpan,TimeUnit timeUnit){
		if(StringUtils.isBlank(key1) || StringUtils.isBlank(key2))
			return null;
		
		long tmBegin = beginTime - timeUnit.toMillis(timeSpan);
		long tmEnd = beginTime;
		
		Set<String> ret1 = SortedSetRangeByScore(key1, tmBegin, tmEnd);
		Set<String> ret2 = SortedSetRangeByScore(key2, tmBegin, tmEnd);
		
		if(ret1 == null || ret2 == null)
			return null;
		
		if(ret1.size() <= 0 || ret2.size() <= 0)
			return new LinkedHashSet<String>();
		
		ret1.retainAll(ret2);
		
		return ret1;
		
	}

	public boolean isTestOnIdle() {
		return testOnIdle;
	}

	public void setTestOnIdle(boolean testOnIdle) {
		this.testOnIdle = testOnIdle;
	}

	public int getTimeCheckIdle() {
		return timeCheckIdle;
	}

	public void setTimeCheckIdle(int timeCheckIdle) {
		this.timeCheckIdle = timeCheckIdle;
	}

	public int getIdleTimeout() {
		return idleTimeout;
	}

	public void setIdleTimeout(int idleTimeout) {
		this.idleTimeout = idleTimeout;
	}

	public int getNumTestsPerEvictionRun() {
		return numTestsPerEvictionRun;
	}

	public void setNumTestsPerEvictionRun(int numTestsPerEvictionRun) {
		this.numTestsPerEvictionRun = numTestsPerEvictionRun;
	}

	@Override
	public String getHashValue(String key, String field) {
		try {
			String ret = jedis.hget(key, field);
			
			return ret;
		} catch (Exception e) {
			LOGGER.error("getHashValue fail",e);
		}
		
		
		return null;
	}

	@Override
	public boolean existKey(String key) {
		try {
			return jedis.exists(key);
		} catch (Exception e) {
			LOGGER.error("existKey fail",e);
			return false;
		}
	}

	public JedisCluster getJedis() {
		return jedis;
	}
	

}
