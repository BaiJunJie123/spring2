package com.ln.utils;

import org.crazycake.shiro.IRedisManager;
import org.crazycake.shiro.WorkAloneRedisManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;
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
@ConfigurationProperties(prefix = "spring.redis")
public class RedisManagers  extends WorkAloneRedisManager implements IRedisManager {
    private static final String DEFAULT_HOST = "127.0.0.1:6379";
    private String host;
    private  int port;
    private int timeout;
    private String password;
    private int database;
    private JedisPool jedisPool;



    public RedisManagers() {
    }

    public void init() {
        synchronized(this) {
            if (this.jedisPool == null) {
                //String[] hostAndPort = this.host.split(":");
                this.jedisPool = new JedisPool(this.getJedisPoolConfig(), this.host, this.port, this.timeout, this.password, this.database);
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
