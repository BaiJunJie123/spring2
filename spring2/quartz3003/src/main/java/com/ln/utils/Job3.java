package com.ln.utils;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author 白俊杰
 * @Date 2019/12/26
 * @Description
 **/
public class Job3 extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("=====ceshi========okok=============="+jobExecutionContext.getJobDetail().getKey().getName());
    }
}
