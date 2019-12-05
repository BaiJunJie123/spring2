package com.ln.utils;

import org.crazycake.shiro.IRedisManager;
import org.crazycake.shiro.WorkAloneRedisManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author 白俊杰
 * @Date 2019/11/23
 * @Description
 **/
@Primary
@Component
@ConfigurationProperties(prefix = "")
public class RedisManagers  extends WorkAloneRedisManager implements IRedisManager {
    private static final String DEFAULT_HOST = "127.0.0.1:6379";
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private  int port;
    @Value("${spring.redis.timeout}")
    private int timeout;
    //@Value("")
    private String password;
    @Value("${spring.redis.database}")
    private int database;
    @Value("${spring.redis.lettuce.pool.max-wait}")
    private  int wait;
    @Value("${spring.redis.lettuce.pool.min-idle}")
    private  int minIdle;
    @Value("${spring.redis.jedis.pool.max-idle}")
    private  int maxIdle;
    @Value("${spring.redis.jedis.pool.max-active}")
    private  int maxActive;
    private JedisPool jedisPool;

    public JedisPoolConfig getJedisPoolConfigs(){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(maxActive);
        config.setMaxWaitMillis(wait);
        config.setMinIdle(minIdle);
        config.setMaxIdle(maxIdle);
        return  config;
    }



    public RedisManagers() {

    }

    public void init() {
        synchronized(this) {
            if (this.jedisPool == null) {
                //String[] hostAndPort = this.host.split(":");
                this.jedisPool = new JedisPool(this.getJedisPoolConfigs(), this.host, this.port, this.timeout, this.password, this.database);
            }

        }
    }

    protected Jedis getJedis() {
        if (this.jedisPool == null) {
            this.init();
        }

        return this.jedisPool.getResource();
    }

    public String getHost() {
        return this.host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getTimeout() {
        return this.timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDatabase() {
        return this.database;
    }

    public void setDatabase(int database) {
        this.database = database;
    }

    public JedisPool getJedisPool() {
        return this.jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public int getPort() {
        return port;
    }

    public int getWait() {
        return wait;
    }

    public void setWait(int wait) {
        this.wait = wait;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public int getMaxActive() {
        return maxActive;
    }

    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void flushDB() {
        Jedis jedis = null;
        try {
             jedis = getJedis();
            jedis.flushDB();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            jedis.close();
        }


    }

    public long dbSize() {
        Jedis jedis = null;
        try {
            jedis = getJedis();
           Long num = jedis.dbSize();
            return num;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }finally {
            jedis.close();
        }

    }
}
