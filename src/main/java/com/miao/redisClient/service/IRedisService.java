package com.miao.redisClient.service;

import org.springframework.data.redis.core.ZSetOperations;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis接口
 *
 * @author miaodc
 * @date 2019/02/22 10:32
 */
public interface IRedisService {

    /**
     * 设置 String 类型 key-value
     * @param key
     * @param value
     */
    void set(String key, String value);

    /**
     * 获取 String 类型 key的value值
     * @param key
     * @return
     */
    String get(String key);

    /**
     * 设置 String 类型 key-value 并添加过期时间
     * @param key
     * @param value
     * @param time
     * @param unit
     */
    void setEx(String key, String value, long time, TimeUnit unit);

    /**
     * 设置 String 类型 key-value key不存在时才设置
     * @param key
     * @param value
     * @return true 设置成功; false key已存在，设置失败
     */
    Boolean setNx(String key, String value);

    /**
     * 设置 String 类型 key-value key不存在时才设置 并添加过期时间
     * @param key
     * @param value
     * @param time
     * @param unit
     * @return true 设置成功; false key已存在，设置失败
     */
    Boolean setNxEx(String key, String value, long time, TimeUnit unit);

    /**
     * 批量添加key value
     * @param keyValues
     */
    void multiSet(Map<String,String> keyValues);

    /**
     * 批量添加key value 只有在键不存在时,才添加
     * map 中只要有一个key存在,则全部不添加
     * @param keyValues
     * @return true 设置成功; false 已有存在的key，设置失败
     */
    Boolean multiSetNx(Map<String,String> keyValues);

    /**
     * 给一个指定的 key 添加过期时间
     * @param key
     * @param time
     * @param unit
     * @return
     */
    Boolean expire(String key, long time, TimeUnit unit);

    /**
     * 移除指定key的过期时间,使其永久有效
     * @param key
     * @return
     */
    Boolean persist(String key);

    /**
     * 获取指定key 的过期时间
     * @param key
     * @return
     */
    Long getExpire(String key);

    /**
     * 删除指定 key-value
     * @param key
     * @return
     */
    Boolean del(String key);

    /**
     * 验证key是否存在
     * @param key
     * @return
     */
    Boolean exist(String key);

    /**
     * 重命名key
     * 慎用，如果newKey已存在，则会覆盖。
     * 使用之前应先用exist()方法验证newKey是否已存在
     * @param key
     * @param newKey
     * @return
     */
    void rename(String key, String newKey);

    /**
     * 添加Hash类型的键值对
     * @param key
     * @param hKey 键
     * @param hValue 值
     */
    void hSet(String key, String hKey, String hValue);

    /**
     * 添加Hash类型的键值对,只有hKey不存在时才添加
     * @param key
     * @param hKey
     * @param hValue
     * @return
     */
    Boolean hSetNx(String key, String hKey, String hValue);

    /**
     * 获取key下的指定hashkey的value值
     * @param key
     * @param hKey
     * @return
     */
    String hGet(String key, String hKey);

    /**
     * 批量添加 hash 的 键值对
     * 有则覆盖,没有则添加
     * @param key
     * @param keyValues
     */
    void multiHSet(String key, Map<String,String> keyValues);

    /**
     * 删除指定 hash 的 HashKey
     * @param key
     * @param hKeys
     * @return 删除成功的 数量
     */
    Long hDel(String key, String ...hKeys);

    /**
     * 验证key下的hKey是否存在
     * @param key
     * @param hKey
     * @return
     */
    Boolean hExist(String key, String hKey);

    /**
     * 获取 key 下的 所有  hKey 和 value
     * @param key
     * @return
     */
    Map<Object,Object> getHEntries(String key);

    /**
     * 获取 key 下的 所有 hKey 字段名
     * @param key
     * @return
     */
    Set<Object> getHKeys(String key);

    /**
     * 获取指定hash下面的键值对数量
     * @param key
     * @return
     */
    Long hSize(String key);

    /**
     * 指定list从左入栈
     * @param key
     * @param value
     * @return
     */
    Long lPush(String key, Object value);

    /**
     * 指定list从左出栈
     * @param key
     * @return
     */
    Object lPop(String key);

    /**
     * 指定list从左出栈
     * 如果列表没有元素,会堵塞到列表一直有元素或者超时为止
     * @param key
     * @param time
     * @param unit
     * @return
     */
    Object lPop(String key, long time, TimeUnit unit);

    /**
     * 从左边依次入栈
     * 如: a b c => c b a
     * @param key
     * @param values
     */
    void lPushAll(String key,List<Object> values);

    /**
     * 指定list从右入栈
     * @param key
     * @param value
     * @return
     */
    Long rPush(String key, Object value);

    /**
     * 指定list从右出栈
     * @param key
     * @return
     */
    Object rPop(String key);

    /**
     * 指定list从右出栈
     * 如果列表没有元素,会堵塞到列表一直有元素或者超时为止
     * @param key
     * @param time
     * @param unit
     * @return
     */
    Object rPop(String key, long time, TimeUnit unit);

    /**
     * 从右边依次入栈
     * 如: a b c => a b c
     * @param key
     * @param values
     */
    void rPushAll(String key,List<Object> values);

    /**
     * 根据下标获取值,只是获取值并不出栈，出栈只能从头或尾部
     * @param key
     * @param index
     * @return
     */
    Object lIndex(String key, long index);

    /**
     * 获取list的长度
     * @param key
     * @return
     */
    Long lSize(String key);

    /**
     * 删除指定count个值为value的list成员
     * @param key
     * @param count
     * @param value
     * @return
     */
    Long lRemove(String key, long count, Object value);

    /**
     * 移除列表的最后一个元素，并将该元素添加到另一个列表并返回
     * @param key1
     * @param key2
     * @return
     */
    Object rPopLPush(String key1, String key2);

    /**
     * 向set添加元素
     * @param key
     * @param values
     * @return 有效插入个数(因为set是集合不存在重复数据,当参数有重复数据时,会存在部分无效数据)
     */
    Long sAdd(String key, String ...values);

    /**
     * 获取集合的差集
     * @param key1
     * @param key2
     * @return
     */
    Set<Object> sDiff(String key1, String key2);

    /**
     * 获取集合的差集并保存到另一个集合
     * @param key1
     * @param key2
     * @param newKey
     * @return 差集的元素个数
     */
    Long sDiffStore(String key1, String key2, String newKey);

    /**
     * 获取集合的并集
     * @param key1
     * @param key2
     * @return
     */
    Set<Object> sUnion(String key1, String key2);

    /**
     * 获取集合的并集并保存到另一个集合
     * @param key1
     * @param key2
     * @param newKey
     * @return 并集的元素个数
     */
    Long sUnionStore(String key1, String key2, String newKey);

    /**
     * 获取集合的交集
     * @param key1
     * @param key2
     * @return
     */
    Set<Object> sInner(String key1, String key2);

    /**
     * 获取集合的交集并保存到另一个集合
     * @param key1
     * @param key2
     * @param newKey
     * @return 并集的元素个数
     */
    Long sInnerStore(String key1, String key2, String newKey);

    /**
     * 删除一个或多个集合中的指定值
     * @param key
     * @param values
     * @return 成功删除数量
     */
    Long sRemove(String key, Object ...values);

    /**
     * 随机获取指定数量的元素,去重(同一个元素只能选择一次)
     * @param key
     * @param count
     * @return
     */
    List<Object> sRandomDistinct(String key, long count);

    /**
     * 把指定元素从一个集合移动到另一个集合
     * @param key1
     * @param value
     * @param key2
     * @return
     */
    Boolean sMove(String key1, Object value, String key2);

    /**
     * 获取集合大小
     * @param key
     * @return
     */
    Long sSize(String key);

    /**
     * 判断集合中是否有value
     * @param key
     * @param value
     * @return
     */
    Boolean sIsMember(String key, Object value);

    /**
     * 返回集合中所有元素
     * @param key
     * @return
     */
    Set<Object> sMembers(String key);

    /**
     * 添加 ZSet 元素
     * @param key
     * @param value
     * @param score
     * @return
     */
    Boolean zsAdd(String key, Object value, double score);

    /**
     * 批量添加 Zset
     * @param key
     * @param tuple
     * @return
     */
    Long zsMultiAdd(String key,Set<ZSetOperations.TypedTuple<Object>> tuple);

    /**
     * Zset 删除一个或多个元素
     * @param key
     * @param values
     * @return
     */
    Long zsRemove(String key, String ...values);

    /**
     * 对指定的zset的value值,socre属性做增减操作
     * @param key
     * @param value
     * @param score
     * @return
     */
    Double zsIncrementScore(String key, Object value, double score);

    /**
     * 获取 key 中指定 value 的排名(从0开始,从小到大排序)
     * @param key
     * @param value
     * @return
     */
    Long zsRank(String key, Object value);

    /**
     * 获取索引区间内的排序结果集合(从0开始,从小到大,只有列名)
     * @param key
     * @param start
     * @param end
     * @return
     */
    Set<Object> zsRange(String key, long start, long end);

    /**
     * 获取索引区间内的排序结果集合(从0开始,从小到大,带上分数)
     * @param key
     * @param start
     * @param end
     * @return
     */
    Set<ZSetOperations.TypedTuple<Object>> zsRangeWithScores(String key, long start, long end);

}
