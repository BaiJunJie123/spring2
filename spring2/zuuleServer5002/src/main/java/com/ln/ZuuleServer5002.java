package com.ln;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author 白俊杰
 * @Date 2019/11/18
 * @Description
 **/
@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@EnableZuulProxy
public class ZuuleServer5002 {
    public static void main(String[] args) {
        SpringApplication.run(ZuuleServer5002.class,args);
    }
}
