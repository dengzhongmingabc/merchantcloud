package com.yidao.datacenter.service.impl;

import com.yidao.datacenter.service.IRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redicache 工具类
 *
 */
@Repository
@SuppressWarnings("unchecked")
@Component
@Service
public class RedisServiceImpl implements IRedisService {
    @SuppressWarnings("rawtypes")
    @Autowired
    private  RedisTemplate redisTemplate;
    /**
     * 批量删除对应的value
     *
     * @param keys
     */
    public  void remove(final String... keys) throws Exception {
        for (String key : keys) {
            remove(key);
        }
    }
    /**
     * 批量删除key
     *
     * @param pattern
     */
    public  void removePattern(final String pattern) throws Exception {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0)
            redisTemplate.delete(keys);
    }
    /**
     * 删除对应的value
     *
     * @param key
     */
    public  void remove(final String key) throws Exception {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }
    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
    public  boolean exists(final String key) throws Exception {
        return redisTemplate.hasKey(key);
    }
    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public  String get(final String key) throws Exception {
        Object result = null;
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        if(result==null){
            return null;
        }
        return result.toString();
    }
    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public  boolean set(final String key, Object value) throws Exception {
        boolean result = false;
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        operations.set(key, value);
        result = true;
        return result;
    }
    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public  boolean set(final String key, Object value, Long expireTime) throws Exception {
        boolean result = false;
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        operations.set(key, value);
        redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
        result = true;
        return result;
    }

    public   boolean hmset(String key, Map<String, String> value) throws Exception {
        boolean result = false;
        redisTemplate.opsForHash().putAll(key, value);
        result = true;
        return result;
    }

    public   boolean hmset(String key, Map<String, String> value,Long expireTime) throws Exception {
        boolean result = false;
        redisTemplate.opsForHash().putAll(key, value);
        redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
        result = true;
        return result;
    }
    public   Map<String,String> hmget(String key)  throws Exception {
        Map<String,String> result =null;
        result=  redisTemplate.opsForHash().entries(key);
        return result;
    }
}