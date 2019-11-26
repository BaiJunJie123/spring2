package com.ln;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 白俊杰
 * @Date 2019/11/18
 * @Description
 **/
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients("serverCloudApi.servers")
@ComponentScan({"serverCloudApi.servers","com.ln.controller"})
public class FeignApp2 {

    public static void main(String[] args) {
        SpringApplication.run(FeignApp2.class, args);
    }
}
