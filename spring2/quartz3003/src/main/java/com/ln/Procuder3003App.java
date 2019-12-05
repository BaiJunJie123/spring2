package com.ln;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 白俊杰
 * @Date 2019/11/17
 * @Description
 **/
@SpringBootApplication
@EnableEurekaClient
@ComponentScan({"com.ln.config","com.ln.utils","com.ln.controller"})
public class Procuder3003App {
    public static void main(String[] args) {
        SpringApplication.run(Procuder3003App.class,args);
        //https://blog.csdn.net/sqlgao22/article/details/100697214 quartz各个表的意思
    }

}
