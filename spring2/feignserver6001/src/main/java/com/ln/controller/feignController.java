package com.ln.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import serverCloudApi.entitys.User;
import serverCloudApi.servers.Userservice;

import java.util.List;

/**
 * @author 白俊杰
 * @Date 2019/11/18
 * @Description
 **/
@Controller
public class feignController {
    @Autowired
    private Userservice userservice;

    @RequestMapping("/www")
    public String getInfo(){
        List<User> list = userservice.findList();
        for(User s : list){
            System.out.println(s.getDbSource()+"=="+s.getName());
        }
        return "hello";
    }
}
