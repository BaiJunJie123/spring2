package com.ln;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * <P>对类的进行描述</P>
 *
 * @version: v1.0.0
 * @author: baijj
 * @date: 2020-01-15 15:26
 */
@SpringBootApplication
@EnableEurekaClient
@ComponentScan({"com.ln.controller","com.ln.config"})
public class BeetlApp {

    public static void main(String[] args) {
        SpringApplication.run(BeetlApp.class,args);
    }

}
