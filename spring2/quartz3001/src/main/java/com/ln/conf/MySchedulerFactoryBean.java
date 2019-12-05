package com.ln.conf;

import org.springframework.boot.autoconfigure.quartz.SchedulerFactoryBeanCustomizer;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

/**
 * @author 白俊杰
 * @Date 2019/12/2
 * @Description  自定义扩展quartz执行器
 **/
@Component
public class MySchedulerFactoryBean implements SchedulerFactoryBeanCustomizer {

    @Override
    public void customize(SchedulerFactoryBean schedulerFactoryBean) {
        schedulerFactoryBean.setOverwriteExistingJobs(true); //更新trigger的 表达式时，同步数据到数据库qrtz_cron_triggers表 开启
        schedulerFactoryBean.setApplicationContextSchedulerContextKey("applicationContext");//注入applicationContext
        schedulerFactoryBean.setStartupDelay(10);  // 延时启动 单位秒
    }
}
