package com.ln.utils;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.bouncycastle.jcajce.provider.asymmetric.ec.KeyFactorySpi;
import org.crazycake.shiro.IRedisManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.serializer.ObjectSerializer;
import org.crazycake.shiro.serializer.RedisSerializer;
import org.crazycake.shiro.serializer.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author 白俊杰
 * @Date 2019/11/23
 * @Description
 **/
@Component
public class RedisCacheManager  implements CacheManager {
    private String keyPrefix = "shiro_redis_cache:";

    private final ConcurrentMap<String, Cache> caches = new ConcurrentHashMap<String, Cache>();
    @Autowired
    private RedisManagers redisManager;


    public String getKeyPrefix() {
        return keyPrefix;
    }

    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }

    public RedisManagers getRedisManager() {
        return redisManager;
    }

    public void setRedisManager(RedisManagers redisManager) {
        this.redisManager = redisManager;
    }

    @Override
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        Cache c = caches.get(s);
       try {
           if (c == null) {
               // initialize the Redis manager instance
               redisManager.init();

               // create a new cache instance
               c = new RedisCache<K, V>(redisManager,keyPrefix);

               // add it to the cache collection
               caches.put(s, c);
           }
           return c;
       }catch (Exception e){
           e.printStackTrace();
       }
       return  null;

    }
}
