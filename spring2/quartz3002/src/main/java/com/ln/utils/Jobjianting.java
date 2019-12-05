package com.ln.utils;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

/**
 * @author 白俊杰
 * @Date 2019/12/4
 * @Description
 **/
public class Jobjianting implements  JobListener {
    @Override
    public String getName() {
        return "job1";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext jobExecutionContext) {
        System.out.println("job执行前");
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {

    }

    @Override
    public void jobWasExecuted(JobExecutionContext jobExecutionContext, JobExecutionException e) {
        System.out.println("job执行后");
    }
}
