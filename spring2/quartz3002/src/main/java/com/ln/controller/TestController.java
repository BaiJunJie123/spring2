package com.ln.controller;

import com.ln.utils.Myjob2;
import com.ln.utils.QuartzUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 白俊杰
 * @Date 2019/12/4
 * @Description
 **/
@RestController
public class TestController {
    @Autowired
    public QuartzUtils quartzUtils;


    @RequestMapping("/addJob")
    public String Job(){
        //quartzUtils.deleteTrigger("t111","tg1111");
        quartzUtils.addJob("jobName1","jobGroupName1", "triggerName1", "triggerGroupName1", Myjob2.class, "*/10 * * * * ?");
        return "add ok";
    }

    @RequestMapping("/delete")
    public String delete(){
        System.out.println("===");
        quartzUtils.deleteJob("jobName1","jobGroupName1","triggerName1","triggerGroupName1");
        return  "delete ok";
    }
    @RequestMapping("/stop")
    public String stop(){
        quartzUtils.stopJob("jobName1","jobGroupName1");
        return "stop ok";
    }
    @RequestMapping("/start")
    public  String start(){
        quartzUtils.resumeJob("jobName1","jobGroupName1");
        return "start ok";
    }
}
