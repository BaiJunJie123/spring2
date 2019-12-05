package com.ln;
import com.ln.utils.Myjob2;
import org.quartz.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.xml.bind.SchemaOutputResolver;

/**
 * @author 白俊杰
 * @Date 2019/11/17
 * @Description
 **/
@SpringBootApplication
@EnableEurekaClient
@ComponentScan({"com.ln.config","com.ln.utils"})
public class Procuder3002App {
    public static void main(String[] args) {
        SpringApplication.run(Procuder3002App.class,args);
    }

}
