package com.ln.utils;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.net.SocketTimeoutException;

/**
 * @author 白俊杰
 * @Date 2019/11/30
 * @Description
 **/
@PersistJobDataAfterExecution //多次调用job 确保对Job的持久化 保存一个数据信息  即有状态任务
@DisallowConcurrentExecution  //确保当前任务完再执行下一个任务
public class Myjob2 extends QuartzJobBean {


    @Autowired
    private QuartzUtils quartzUtils;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        for(int i =0;i<10;i++){

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i+"=====3003========okok==============");
        }
        if(jobExecutionContext.getJobDetail().getJobDataMap().get("count")!= null){
            Integer o = (Integer) jobExecutionContext.getJobDetail().getJobDataMap().get("count");
            o++;
            jobExecutionContext.getJobDetail().getJobDataMap().put("count", o);
            System.out.println("====="+o+"=========");
        }


    }
}
