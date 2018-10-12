package com.yidao.appstore.service;

import java.util.Map;

public interface IRedisService {
    /**
     * 批量删除对应的value
     *
     * @param keys
     */
    public  void remove(final String... keys) throws Exception ;
    /**
     * 批量删除key
     *
     * @param pattern
     */
    public  void removePattern(final String pattern) throws Exception ;
    /**
     * 删除对应的value
     *
     * @param key
     */
    public  void remove(final String key) throws Exception;
    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
    public  boolean exists(final String key) throws Exception;
    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public  String get(final String key) throws Exception;
    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public  boolean set(final String key, Object value) throws Exception ;
    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public  boolean set(final String key, Object value, Long expireTime) throws Exception;

    public   boolean hmset(String key, Map<String, String> value) throws Exception;

    public   Map<String,String> hmget(String key) throws Exception ;

    public   boolean hmset(String key, Map<String, String> value, Long expireTime) throws Exception;
}