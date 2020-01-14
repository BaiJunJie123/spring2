package com.ln.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ln.entity.SchedulingJob;
import com.ln.utils.Myjob2;
import com.ln.utils.QuartzUtils;
import org.json.JSONString;
import org.quartz.impl.JobDetailImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author 白俊杰
 * @Date 2019/12/4
 * @Description
 **/
@Controller
public class TestController {
    @Autowired
    public QuartzUtils quartzUtils;


    @RequestMapping("/addJob")
    @ResponseBody
    public String Job(HttpServletResponse response,String jobName1,String jobGroupName1,String triggerName1,String triggerGroupName1,String classUrlName,String cron,String note){
        response.setHeader("Access-Control-Allow-Origin", "*");
        //quartzUtils.deleteTrigger("t111","tg1111");
        try {
            Object obj = null;
            try {
                 obj=Class.forName(classUrlName).newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
          Object obj2 = quartzUtils.addJob(jobName1,jobGroupName1, triggerName1, triggerGroupName1,obj.getClass() , cron,note);
            if(obj2 == null){
                return "{\"status\":\"jobKey or TriggerKey is existing\"}";
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return "{\"status\":\"addOk\"}";
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(String jobName1,String jobGroupName1,String triggerName1,String triggerGroupName1,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        quartzUtils.deleteJob(jobName1,jobGroupName1,triggerName1,triggerGroupName1);
        return "{\"status\":\"deleteOk\"}";
    }
    @RequestMapping("/stop")
    @ResponseBody
    public String stop(String jobName1,String jobGroupName1,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        quartzUtils.stopJob(jobName1,jobGroupName1);
        return "{\"status\":\"stopOk\"}";
    }
    @RequestMapping("/start")
    @ResponseBody
    public  Object start(String jobName1,String jobGroupName1,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        quartzUtils.resumeJob(jobName1,jobGroupName1);
        return "{\"status\":\"startOk\"}";
    }
    @RequestMapping(value = "/getAllJob",produces = "application/json")
    @ResponseBody
    public String getallJob(HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        List<SchedulingJob> jobList =  quartzUtils.getAllJob();
         String s = JSONObject.toJSONString(jobList);
        return s;
    }
    @RequestMapping("/updateCron")
    @ResponseBody
    public Object updateCron(String tirggerName,String tirggerGroup, String newCron,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        quartzUtils.updateCronTime(tirggerName,tirggerGroup,newCron);
        return "{\"status\":\"updateOk\"}";
    }

    @RequestMapping("/closeAllJob")
    @ResponseBody
    public Object closeAllJob(HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        quartzUtils.stopScheduler();
        return "{\"status\":\"stopOk\"}";
    }
    @RequestMapping("/startAllJob")
    @ResponseBody
    public Object startAllJob(HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        quartzUtils.startAllJob();
        return "{\"status\":\"startOk\"}";
    }

    @RequestMapping("/isShutDown")
    @ResponseBody
    public Object isShutDown(HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
       boolean pan =  quartzUtils.isShutduow();
        return "{\"status\":\""+pan+"\"}";
    }

    @RequestMapping("/isStarted")
    @ResponseBody
    public Object isStarted(HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        boolean pan =  quartzUtils.isStarted();
        return "{\"status\":\""+pan+"\"}";
    }



    @RequestMapping("/login")
    public String hello(){

        return "hello";
    }

    @RequestMapping("/one")
    public String index(){

        return "index";
    }

    @RequestMapping("/new")
    public String new2(){
        return "newJob";
    }

}
