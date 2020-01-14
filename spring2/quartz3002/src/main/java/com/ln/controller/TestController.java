package com.ln.controller;

import com.ln.utils.QuartzUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
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
    public String Job(String jobName1,String jobGroupName1,String triggerName1,String triggerGroupName1,String classUrlName,String cron){
        //quartzUtils.deleteTrigger("t111","tg1111");
        try {
            Class<? extends QuartzJobBean> classType= (Class<? extends QuartzJobBean>) Class.forName(classUrlName);
            quartzUtils.addJob(jobName1,jobGroupName1, triggerName1, triggerGroupName1, classType.getClass(), cron);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return "add ok";
    }

    @RequestMapping("/delete")
    public String delete(String jobName1,String jobGroupName1,String triggerName1,String triggerGroupName1){
        quartzUtils.deleteJob(jobName1,jobGroupName1,triggerName1,triggerGroupName1);
        return  "delete ok";
    }
    @RequestMapping("/stop")
    public String stop(String jobName1,String jobGroupName1){
        quartzUtils.stopJob(jobName1,jobGroupName1);
        return "stop ok";
    }
    @RequestMapping("/start")
    public  String start(String jobName1,String jobGroupName1){
        quartzUtils.resumeJob(jobName1,jobGroupName1);
        return "start ok";
    }
    @RequestMapping("getallJob")
    public String getallJob(){
        quartzUtils.getAllJob();
        return "get all jobs";
    }
}
