package com.ln.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author 白俊杰
 * @Date 2019/11/18
 * @Description
 **/

@EnableWebSecurity
@Configuration

public class CloseCsrf extends WebSecurityConfigurerAdapter {
    @Override
    //新版本的security默认开启csrf了，关掉，在注册中心新建一个类，继承WebSecurityConfigurerAdapter来关闭
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable(); //关闭csrf
        http.authorizeRequests().anyRequest().authenticated().and().httpBasic(); //开启认证
    }


}
