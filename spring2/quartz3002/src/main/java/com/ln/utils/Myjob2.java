package com.ln.utils;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author 白俊杰
 * @Date 2019/11/30
 * @Description
 **/
@DisallowConcurrentExecution
public class Myjob2 extends QuartzJobBean {


    @Autowired
    private QuartzUtils quartzUtils;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        for(int i =0;i<10;i++){
            if(i==3){
                //quartzUtils.deleteJob("ceshi_Group_jobdeil", "job5","ceshi_Group_jobcron");
              // String names = quartzUtils.getTriggerState("job5_cron","ceshi_Group_jobcron");
               // System.out.println("names = " + names);
                //quartzUtils.updateCronTime("job5_cron", "ceshi_Group_jobcron", "*/10 * * * * ?");
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i+"=====3003========okok==============");
        }

    }
}
