package com.ln;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author 白俊杰
 * @Date 2019/11/17
 * @Description
 **/
@SpringBootApplication
@EnableEurekaServer
public class Eureka8002 {
    public static void main(String[] args) {
        SpringApplication.run(Eureka8002.class,args);
    }
}
