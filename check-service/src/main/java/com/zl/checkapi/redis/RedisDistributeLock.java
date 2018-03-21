package com.zl.checkapi.redis;

import org.apache.commons.lang3.StringUtils;


/**
 * 使用Redis实现分布式全局锁
 * 算法实现参考：http://redis.io/commands/setnx
 * @author gaoyang1
 *
 */
public class RedisDistributeLock {
	private String lockName;
	private long expireTime;
	private RedisOperator redisOper;
	
	
	/**
	 * 
	 * @param lockName 同一个名称表示同一把锁
	 * @param expireTime 锁的超时时间，如果超过，该锁失效，其他线程可获取锁
	 * @param redisOper
	 */
	public RedisDistributeLock(String lockName,long expireTime,RedisOperator redisOper){
		this.lockName = lockName;
		this.redisOper = redisOper;
		this.expireTime = expireTime * 1000;
	}
	
	
	private String getLockKey(){
		if(StringUtils.isBlank(lockName))
			return null;
		
		return "lock:" + lockName;
	}
	
	/**
	 * 抛出异常或返回-1，说明未获取到锁
	 * return: 返回值需要在unlock时作为参数传入
	 * @param timeout -1:永久等待
	 * @throws Exception 
	 */
	public long lock(long timeout) throws Exception{
		String key = getLockKey();
		
		if(key == null)
			throw new Exception("lock must have a name");
		
		
		boolean infinite = (timeout == -1);
		while(infinite || timeout >= 0){
			Long value = System.currentTimeMillis() + expireTime;
			long keyTimeout = value.longValue();
			long setnxRet = redisOper.insertKeyValueIfNotExist(key, value.toString(), -1, null);
			if(setnxRet > 0){
				//暂无其他线程或进程加锁
				return keyTimeout;
			}
			else if(setnxRet == -1){
				return -1;
			}
			else{
				//已经有锁了
				String val = redisOper.getValueByKey(key);
				if(StringUtils.isBlank(val)){
					//key刚好被删除了，重来
					continue;
				}

				if(System.currentTimeMillis() > Long.parseLong(val)){
					Long newVal = System.currentTimeMillis() + expireTime;
					String oldVal = redisOper.getset(key, newVal.toString(), -1, null);
					if(StringUtils.isBlank(val)){
						//key刚好被删除了，重来
						continue;
					}
					
					if(System.currentTimeMillis() > Long.parseLong(oldVal)){
						keyTimeout = newVal.longValue();
						return keyTimeout;
					}
					else{
						//获取失败，继续等待
					}
				}
				else{
					//锁还没超时，继续等待
				}
			}
			
			if(!infinite)
				timeout -= 100;
			
			Thread.sleep(100);
		}
	
		
		return -1;
	}
	
	/**
	 * 
	 * @param keyTimeout  lock的返回值
	 */
	public void unlock(long keyTimeout){
		if(System.currentTimeMillis() > keyTimeout){
			return;
		}
		
		redisOper.deleteKeyValue(getLockKey());
	}
	
	public String getLockName() {
		return lockName;
	}
	
	public long getExpireTime() {
		return expireTime;
	}
}
