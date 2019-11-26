package com.ln.utils;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author 白俊杰
 * @Date 2019/11/23
 * @Description
 **/
@Component
public class RedisCache<K,V> implements Cache<K,V> {
    private RedisManagers cache;
    private String keyPrefix = "shiro_redis_session:";

    /**
     * 获得byte[]型的key
     * @param key
     * @return
     */
    private byte[] getByteKey(K key){
        if(key instanceof String){
            String preKey = this.keyPrefix + key;
            return preKey.getBytes();
        }else{
            return SerializeUtils.serialize(key);
        }
    }
    @Override
    public V get(K k) throws CacheException {
        System.out.println("根据key从redis中获取对象");
        try {
            if(k == null){
                return  null;
            }else{
                byte[] rawValue = cache.get(getByteKey(k));
                @SuppressWarnings("unchecked")
                V value = (V)SerializeUtils.unserialize(rawValue);
                return  value;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;

    }

    @Override
    public V put(K k, V v) throws CacheException {
        try {
            System.out.println("根据key来存储");
            cache.set(getByteKey(k),SerializeUtils.serialize(v),20*100);
            return  v;
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;

    }

    @Override
    public V remove(K k) throws CacheException {
        try {
            V previous = get(k);
            cache.del(getByteKey(k));
            return previous;
        }catch (Exception e){

            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void clear() throws CacheException {
        try {
            cache.flushDB();
        } catch (Throwable t) {
            throw new CacheException(t);
        }

    }

    @Override
    public int size() {
        try {
            Long longSize = new Long(cache.dbSize());
            return longSize.intValue();
        } catch (Throwable t) {
            throw new CacheException(t);
        }


    }
    @SuppressWarnings("unchecked")
    @Override
    public Set<K> keys() {
        try {
            Set<byte[]> keys = cache.keys((this.keyPrefix + "*").getBytes());
            if (CollectionUtils.isEmpty(keys)) {
                return Collections.emptySet();
            }else{
                Set<K> newKeys = new HashSet<K>();
                for(byte[] key:keys){
                    newKeys.add((K)key);
                }
                return newKeys;
            }
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }

    @Override
    public Collection<V> values() {
        try {
            Set<byte[]> keys = cache.keys((this.keyPrefix + "*").getBytes());
            if (!CollectionUtils.isEmpty(keys)) {
                List<V> values = new ArrayList<V>(keys.size());
                for (byte[] key : keys) {
                    @SuppressWarnings("unchecked")
                    V value = get((K)key);
                    if (value != null) {
                        values.add(value);
                    }
                }
                return Collections.unmodifiableList(values);
            } else {
                return Collections.emptyList();
            }
        } catch (Throwable t) {
            throw new CacheException(t);
        }
    }


    public RedisManagers getCache() {
        return cache;
    }

    public void setCache(RedisManagers cache) {
        this.cache = cache;
    }

    public String getKeyPrefix() {
        return keyPrefix;
    }

    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }

    public RedisCache(RedisManagers cache, String keyPrefix) {
        this.cache = cache;
        this.keyPrefix = keyPrefix;
    }

    public RedisCache() {
    }
}
