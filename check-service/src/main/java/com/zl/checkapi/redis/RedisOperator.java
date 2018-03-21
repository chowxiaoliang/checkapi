package com.zl.checkapi.redis;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public interface RedisOperator {
	void insertKeyValue(String key, String value, long expireTime, TimeUnit timeUnit);
	long insertKeyValueIfNotExist(String key, String value, long expireTime, TimeUnit timeUnit);
	boolean insertHash(String key, String field, String value, long expireTime, TimeUnit timeUnit);
	String getHashValue(String key, String field);
	void insertSortedSet(String key, double score, String value, long beginTime, long expireTime, TimeUnit timeUnit);
	void insertSet(String key, String value, long expireTime, TimeUnit timeUnit);
	void deleteKeyValue(String key);
	String getValueByKey(String key);
	Set<String> SortedSetRangeByScore(String key, double beginScore, double endScore);
	void setKeyExpireTime(String key, int seconds);
	boolean existKey(String key);
	void SortedSetInter(String key1, String key2, String retSet);
	String getset(String key, String value, long expireTime, TimeUnit timeUnit);
	Set<String> queryInSortedSet(String key1, String key2, long beginTime, long timeSpan, TimeUnit timeUnit);
	Set<String> queryInSortedSet(List<String> keys, long beginTime, long timeSpan, TimeUnit timeUnit);

}
