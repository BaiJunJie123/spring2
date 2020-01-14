package com.ln;

import com.ln.configUtils.RedisConfig;
import com.ln.utils.RedisManagers;
import com.ln.utils.RedisSessionDAO;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author 白俊杰
 * @Date 2019/11/17
 * @Description
 **/
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.ln.dao")
@ComponentScan({"com.ln.controller","com.ln.service","com.ln.configUtils","com.ln.utils"})
@EnableConfigurationProperties({RedisManagers.class})
@EnableRedisHttpSession
public class Procuder7002App {
    public static void main(String[] args) {
        SpringApplication.run(Procuder7002App.class,args);
    }
    //直接把trigger以及jobDetail注入到spring上下文，spring会自动用Scheduler注册trigger。
}
