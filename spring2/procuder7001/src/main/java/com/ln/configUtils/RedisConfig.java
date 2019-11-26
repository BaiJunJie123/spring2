package com.ln.configUtils;

import com.ln.utils.Redisutils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.HashSet;
import java.util.Set;

/*
 * @author 白俊杰
 * @Date 2019/11/1
 * @Description  使用了@EnableRedisHttpSession 回话共享后 直接使用httpsession中的工厂
 */

@Configuration
public class RedisConfig {
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;
    @Autowired
    private Redisutils redisutils;
    private static String host = "127.0.0.1";
    private static Integer maxActive = 30000;
    private static Integer maxIdle = 0;
    private static Integer maxWait = -1;
    private static Integer minIdle = 0;
    private static Integer port = 6379;
    private static Integer timeout =-1;

    /*@Bean
    public JedisPoolConfig jedisPoolConfig(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMinIdle(minIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWait);
        jedisPoolConfig.setMaxTotal(maxActive);
        return jedisPoolConfig;

    }*/


/*    @Bean
    public JedisConnectionFactory jedisConnectionFactoryforRedis(JedisPoolConfig jedisPoolConfig){
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setHostName(host);
        jedisConnectionFactory.setPoolConfig(jedisPoolConfig);
        jedisConnectionFactory.setPort(port);
        jedisConnectionFactory.setTimeout(timeout);
        return jedisConnectionFactory;
    }*/
    @Bean
    public RedisTemplate<String,Object> functionDomainRedisTemplate(){
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<String,Object>();
        initDomainRedisTemplate(redisTemplate,redisConnectionFactory);
        return redisTemplate;
    }

    private void initDomainRedisTemplate(RedisTemplate redisTemplate,RedisConnectionFactory redisConnectionFactory) {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        // 开启事务
        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.setConnectionFactory(redisConnectionFactory);
    }

//配置缓存管理器

    @Bean
    @Primary
    public SimpleCacheManager cacheManager() {
        SimpleCacheManager sim = new SimpleCacheManager();
        Set<Cache> setcache =  new HashSet<Cache>();
        setcache.add(redisutils);
        sim.setCaches(setcache);
        return sim;
    }
}
