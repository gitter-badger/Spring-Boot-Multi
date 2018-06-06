package com.highcharts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @program: multi-module
 * @description: Redis
 * @author: Brucezheng
 * @create: 2018-04-10 14:03
 **/
@Component
public class RedisService {
    //@Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    JedisPool jedisPool;

    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public Object get(String key) {
        Object o = redisTemplate.opsForValue().get(key);
        return o;
    }

    public void set2(String key, String value) {
        Jedis resource = jedisPool.getResource();
        resource.set(key, value);
        resource.close();
    }

    /**
     * 存储数据到缓存中，并制定过期时间和当Key存在时是否覆盖。
     *
     * @param key
     * @param value
     * @param nxxx
     *            nxxx的值只能取NX或者XX，如果取NX，则只有当key不存在是才进行set，如果取XX，则只有当key已经存在时才进行set
     *
     * @param expx expx的值只能取EX或者PX，代表数据过期时间的单位，EX代表秒，PX代表毫秒。
     * @param time 过期时间，单位是expx所代表的单位。
     * @return
     */
    public String set(String key, String value, String nxxx, String expx, long time){
        Jedis jedis = jedisPool.getResource();
        String string = jedis.set(key, value, nxxx, expx, time);
        jedis.close();
        return string;
    }


    public String hget(String hkey, String key) {
        Jedis jedis = jedisPool.getResource();
        String string = jedis.hget(hkey, key);
        jedis.close();
        return string;
    }

    public long hset(String hkey, String key, String value) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.hset(hkey, key, value);
        jedis.close();
        return result;
    }

    public long incr(String key) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.incr(key);
        jedis.close();
        return result;
    }

    public long expire2(String key, int second) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.expire(key, second);
        jedis.close();
        return result;
    }

    public String get2(String key) {
        Jedis jedis = jedisPool.getResource();
        String string = jedis.get(key);
        jedis.close();
        return string;
    }

    public long ttl(String key) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.ttl(key);
        jedis.close();
        return result;
    }


    public long del(String key) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.del(key);
        jedis.close();
        return result;
    }


    public long hdel(String hkey, String key) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.hdel(hkey, key);
        jedis.close();
        return result;
    }
}
