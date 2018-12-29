package com.springmvc.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 *
 * @param
 * @return
 * @Description redis工具类
 * @author liyu from yitang, E-mail: 15381002414@163.com
 * @Date  2018/7/18 17:10
 */
@Component
public class RedisUtils {

    @Autowired
    private StringRedisTemplate redisTemplate;



    /**
     * 以键值对方式保存数据到Redis
     *
     * @param key   键
     * @param value 值
     */
    public void setToRedis(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 以键值对方式保存数据到Redis
     *
     * @param key   键
     * @param value 值
     * @param time  时间长度
     * @param unit  时间单位
     */
    public void setToRedis(String key, String value, long time, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, time, unit);
    }

    /**
     * 以Hash方式保存数据到Redis
     *
     * @param key   键
     * @param field 字段名称
     * @param value 值
     */
    public void setToRedis(String key, String field, String value) {
        redisTemplate.opsForHash().put(key, field, value);
    }

    /**
     * 以Hash方式保存数据到Redis
     *
     * @param key 键
     * @param map Map 对象
     */
    public void setToRedis(String key, Map<String, Object> map) {

        redisTemplate.opsForHash().putAll(key, map);
    }

    /**
     * 从Redis读取指定键的值
     *
     * @param key 键
     * @return Value
     */
    public String getFromRedis(String key) {
        return redisTemplate.opsForValue().get(key);
    }
    /**
     * 获取hashKey对应的所有键值
     *
     * @param key 键
     * @return Value
     */
    public Object hmget(String key) {
        return redisTemplate.opsForHash().entries(key);
    }
    /**
     * 从Redis读取指定键下的字段名称的值
     *
     * @param key   键
     * @param field 字段名称
     * @return Value
     */
    public Object getFromRedis(String key, String field) {
        return redisTemplate.opsForHash().get(key, field);
    }

    /**
     * 从Redis删除指定键
     *
     * @param key 键
     */
    public void deleteFromRedis(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 删除hash
     *
     * @param key
     * @param field
     */
    public void deleteFromRedis(String key, String field) {
        redisTemplate.opsForHash().delete(key, field);
    }

    /**
     * Redis中是否存在指定键
     *
     * @param key 键
     * @return 是否存在指定键
     */
    public Boolean redisHasKey(String key) {
        return redisTemplate.hasKey(key);
    }
}
