package com.miao.redisClient.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis实现
 *
 * @author miaodc
 * @date 2019/02/22 10:33
 */
@Service
public class RedisService implements IRedisService{
    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    @Override
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key,value);
    }

    @Override
    public String get(String key) {
        Object obj = redisTemplate.opsForValue().get(key);
        if(obj != null){
            return (String)obj;
        }
        return null;
    }

    @Override
    public void setEx(String key, String value, long time, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, time, unit);
    }

    @Override
    public Boolean setNx(String key, String value) {
        return redisTemplate.opsForValue().setIfAbsent(key,value);
    }

    @Override
    public Boolean setNxEx(String key, String value, long time, TimeUnit unit) {
        return redisTemplate.opsForValue().setIfPresent(key,value,time,unit);
    }

    @Override
    public void multiSet(Map<String, String> keyValues) {
        redisTemplate.opsForValue().multiSet(keyValues);
    }

    @Override
    public Boolean multiSetNx(Map<String, String> keyValues) {
        return redisTemplate.opsForValue().multiSetIfAbsent(keyValues);
    }

    @Override
    public Boolean expire(String key, long time, TimeUnit unit) {
        return redisTemplate.boundZSetOps(key).expire(time,unit);
    }

    @Override
    public Boolean persist(String key) {
        return redisTemplate.boundSetOps(key).persist();
    }

    @Override
    public Long getExpire(String key) {
        return redisTemplate.boundSetOps(key).getExpire();
    }

    @Override
    public Boolean del(String key) {
        return redisTemplate.delete(key);
    }

    @Override
    public Boolean exist(String key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public void rename(String key, String newKey) {
        redisTemplate.rename(key,newKey);
    }

    @Override
    public void hSet(String key, String hKey, String hValue) {
        redisTemplate.opsForHash().put(key,hKey,hValue);
    }

    @Override
    public Boolean hSetNx(String key, String hKey, String hValue) {
        return redisTemplate.opsForHash().putIfAbsent(key,hKey,hValue);
    }

    @Override
    public String hGet(String key, String hKey) {
        Object obj = redisTemplate.opsForHash().get(key, hKey);
        if(obj != null){
            return (String)obj;
        }
        return null;
    }

    @Override
    public void multiHSet(String key, Map<String, String> keyValues) {
        redisTemplate.opsForHash().putAll(key,keyValues);
    }

    @Override
    public Long hDel(String key, String... hKeys) {
        return redisTemplate.opsForHash().delete(key,hKeys);
    }

    @Override
    public Boolean hExist(String key, String hKey) {
        return redisTemplate.opsForHash().hasKey(key,hKey);
    }

    @Override
    public Map<Object, Object> getHEntries(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    @Override
    public Set<Object> getHKeys(String key) {
        return redisTemplate.opsForHash().keys(key);
    }

    @Override
    public Long hSize(String key) {
        return redisTemplate.opsForHash().size(key);
    }

    @Override
    public Long lPush(String key, Object value) {
        return redisTemplate.opsForList().leftPush(key,value);
    }

    @Override
    public Object lPop(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    @Override
    public Object lPop(String key, long time, TimeUnit unit) {
        return redisTemplate.opsForList().leftPop(key,time,unit);
    }

    @Override
    public void lPushAll(String key, List<Object> values) {
        redisTemplate.opsForList().leftPushAll(key,values);
    }

    @Override
    public Long rPush(String key, Object value) {
        return redisTemplate.opsForList().rightPush(key,value);
    }

    @Override
    public Object rPop(String key) {
        return redisTemplate.opsForList().rightPop(key);
    }

    @Override
    public Object rPop(String key, long time, TimeUnit unit) {
        return redisTemplate.opsForList().rightPop(key,time,unit);
    }

    @Override
    public void rPushAll(String key, List<Object> values) {
        redisTemplate.opsForList().rightPushAll(values);
    }

    @Override
    public Object lIndex(String key, long index) {
        return redisTemplate.opsForList().index(key,index);
    }

    @Override
    public Long lSize(String key) {
        return redisTemplate.opsForList().size(key);
    }

    @Override
    public Long lRemove(String key, long count, Object value) {
        return redisTemplate.opsForList().remove(key,count,value);
    }

    @Override
    public Object rPopLPush(String key1, String key2) {
        return redisTemplate.opsForList().rightPopAndLeftPush(key1,key2);
    }

    @Override
    public Long sAdd(String key, String... values) {
        return redisTemplate.opsForSet().add(key,values);
    }

    @Override
    public Set<Object> sDiff(String key1, String key2) {
        return redisTemplate.opsForSet().difference(key1,key2);
    }

    @Override
    public Long sDiffStore(String key1, String key2, String newKey) {
        return redisTemplate.opsForSet().differenceAndStore(key1,key2,newKey);
    }

    @Override
    public Set<Object> sUnion(String key1, String key2) {
        return redisTemplate.opsForSet().union(key1,key2);
    }

    @Override
    public Long sUnionStore(String key1, String key2, String newKey) {
        return redisTemplate.opsForSet().unionAndStore(key1,key2,newKey);
    }

    @Override
    public Set<Object> sInner(String key1, String key2) {
        return redisTemplate.opsForSet().intersect(key1,key2);
    }

    @Override
    public Long sInnerStore(String key1, String key2, String newKey) {
        return redisTemplate.opsForSet().intersectAndStore(key1,key2,newKey);
    }

    @Override
    public Long sRemove(String key, Object... values) {
        return redisTemplate.opsForSet().remove(key,values);
    }

    @Override
    public List<Object> sRandomDistinct(String key, long count) {
        return redisTemplate.opsForSet().randomMembers(key,count);
    }

    @Override
    public Boolean sMove(String key1, Object value, String key2) {
        return redisTemplate.opsForSet().move(key1,value,key2);
    }

    @Override
    public Long sSize(String key) {
        return redisTemplate.opsForSet().size(key);
    }

    @Override
    public Boolean sIsMember(String key, Object value) {
        return redisTemplate.opsForSet().isMember(key,value);
    }

    @Override
    public Set<Object> sMembers(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    @Override
    public Boolean zsAdd(String key, Object value, double score) {
        return redisTemplate.opsForZSet().add(key,value,score);
    }

    @Override
    public Long zsMultiAdd(String key, Set<ZSetOperations.TypedTuple<Object>> tuple) {
        return redisTemplate.opsForZSet().add(key,tuple);
    }

    @Override
    public Long zsRemove(String key, String... values) {
        return redisTemplate.opsForZSet().remove(key,values);
    }

    @Override
    public Double zsIncrementScore(String key, Object value, double score) {
        return redisTemplate.opsForZSet().incrementScore(key,value,score);
    }

    @Override
    public Long zsRank(String key, Object value) {
        return redisTemplate.opsForZSet().rank(key,value);
    }

    @Override
    public Set<Object> zsRange(String key, long start, long end) {
        return redisTemplate.opsForZSet().range(key,start,end);
    }

    @Override
    public Set<ZSetOperations.TypedTuple<Object>> zsRangeWithScores(String key, long start, long end) {
        return redisTemplate.opsForZSet().rangeByScoreWithScores(key,start,end);
    }
}
