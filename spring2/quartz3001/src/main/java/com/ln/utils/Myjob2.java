package com.ln.utils;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author 白俊杰
 * @Date 2019/11/30
 * @Description
 **/
@DisallowConcurrentExecution
public class Myjob2 extends QuartzJobBean {

    private String aa;



    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        for(int i =0;i<10;i++){
            System.out.println("3001第3001个任务："+i);

        }
        System.out.println(jobExecutionContext.getJobDetail().getJobDataMap().get("aa")+"=========================end===================");
    }


    public String getAa() {
        return aa;
    }

    public void setAa(String aa) {
        this.aa = aa;
    }
}
