package com.ln;
import com.ln.utils.RedisManagers;
import com.ln.utils.RedisSessionDAO;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
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
@EnableRedisHttpSession   //分布式回话session
public class Procuder7001App {
    public static void main(String[] args) {
        SpringApplication.run(Procuder7001App.class,args);


    }
}
