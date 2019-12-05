package com.ln.conf;

import com.ln.utils.Myjob2;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 白俊杰
 * @Date 2019/12/2
 * @Description
 **/
@Configuration
public class QuartzConfig {

    /* @Bean
     public JobDetailFactoryBean jobDetailFactoryBeanOne(){
         JobDetailFactoryBean job = new JobDetailFactoryBean();
         //durability 表示任务完成之后是否依然保留到数据库，默认falses
         job.setDurability(true);
         //当Quartz服务被中止后，再次启动或集群中其他机器接手任务时会尝试恢复执行之前未完成的所有任务
         job.setRequestsRecovery(false);
         job.setJobClass(Myjob2.class);
         //可以设置MAP
         Map<String,String> jobDataAsMap = new HashMap<>();
         jobDataAsMap.put("targetObject","jobDetailFactoryBeanOne"); //spring 中bean的名字
         jobDataAsMap.put("targetMethod","execute");   // 随便传值
         job.setJobDataAsMap(jobDataAsMap);
         return  job;


     }
     @Bean
     public CronTriggerFactoryBean cronTriggerFactoryBeanOne(JobDetail jobDetailFactoryBeanOne){
         CronTriggerFactoryBean cron = new CronTriggerFactoryBean();
         cron.setJobDetail(jobDetailFactoryBeanOne);
         cron.setCronExpression("0 0 23 L * ?");
         return  cron;
     }*/













    @Bean
    public JobDetail myJobDetail2(){
        //storeDurably() 即使没有Trigger关联时，也不需要删除该JobDetail
        JobDetail jobDetail = JobBuilder.newJob(Myjob2.class).withIdentity("myJob2").usingJobData("aa", "bjjbjj").storeDurably().build();
        //jobDetail.getJobBuilder().requestRecovery(false);
        //jobDetail.getJobBuilder().storeDurably(false);
        return jobDetail;
    }
    @Bean
    public Trigger myTrigger2(){
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("*/5 * * * * ?");
        //.withSchedule(SimpleScheduleBuilder.simpleSchedule()
        //                        .withIntervalInSeconds(10)
        //                        .repeatForever()
        //                )
        return TriggerBuilder.newTrigger()
                .forJob("myJob2").withSchedule(cronScheduleBuilder)
                .build();
    }
}
