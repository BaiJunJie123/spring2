package com.ln.utils;

import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 白俊杰
 * @Date 2019/12/3
 * @Description
 **/
@Component
public class QuartzUtils {

    @Autowired
    @Qualifier("schedulerFactoryBean")
    private SchedulerFactoryBean schedulerFactoryBean;
    //删除jobdeilter
    public  void deleteJob(String jobName,String JobGropName,String triggerName,String triggerGroupName){
        TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
        JobKey jobKey = JobKey.jobKey(jobName, JobGropName);
        Scheduler scheduler = schedulerFactoryBean.getObject();
        Trigger trigger = null;
        try {
            trigger = scheduler.getTrigger(triggerKey);
            if(trigger == null){
                System.out.println("没有trigger......");
                return;
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        try {
            scheduler.pauseTrigger(triggerKey);// 停止触发器
            scheduler.unscheduleJob(triggerKey); // 移除触发器
            scheduler.deleteJob(jobKey); // 删除任务
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }
    //暂停一个任务
    public  void  stopJob(String jobName,String JobGropName){
        try {
            JobKey jobKey = new JobKey(jobName,JobGropName);

            schedulerFactoryBean.getObject().pauseJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
    //恢复一个任务
    public  void  resumeJob(String jobName,String JobGropName){

        JobKey jobKey = new JobKey(jobName,JobGropName);
        try {
            schedulerFactoryBean.getObject().resumeJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
    //启动所有的定时任务
    public void  startAllJob(){
        try {
            schedulerFactoryBean.getObject().start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
    // 关闭所有的定时任务
    public void closeAllJob(){
        try {
            schedulerFactoryBean.getObject().shutdown(true);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
    /*获取任务状态
	 * 		NONE: 不存在
	 * 		NORMAL: 正常
	 * 		PAUSED: 暂停
	 * 		COMPLETE:完成
	 * 		ERROR : 错误
	 * 		BLOCKED : 阻塞
	 */
    public  String getTriggerState(String tirggerName,String tirggerGroup){
        TriggerKey triggerKey = TriggerKey.triggerKey(tirggerName, tirggerGroup);
        Scheduler scheduler = schedulerFactoryBean.getObject();
        String name = null;
        try {
            Trigger.TriggerState triggerState = scheduler.getTriggerState(triggerKey);
            name = triggerState.name();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return name;
    }

    //修改任务的定时时间
    public void  updateCronTime(String tirggerName,String tirggerGroup, String newCron){

        TriggerKey key = TriggerKey.triggerKey(tirggerName,tirggerGroup);
        try {
            CronTrigger trigger = (CronTrigger) schedulerFactoryBean.getObject().getTrigger(key);
          String odrTime =  trigger.getCronExpression();
          if(!odrTime.equals(newCron)){
              CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(newCron);

              // 按新的cronExpression表达式重新构建trigger
              trigger = trigger.getTriggerBuilder().withIdentity(key).withSchedule(scheduleBuilder).build();
              // 按新的trigger重新设置job执行
              schedulerFactoryBean.getObject().rescheduleJob(key, trigger);

          }else{

          }

        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }
    //从数据库中得到当前cron的值
    public String getCron(String tirggerName,String tirggerGroupName){
        TriggerKey triggerKey = TriggerKey.triggerKey(tirggerName, tirggerGroupName);
        Scheduler scheduler = schedulerFactoryBean.getObject();
        String name = null;
        try {
          CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
         name = trigger.getCronExpression();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return name;

    }
    // 添加一个任务
    public void addJob(String jobName, String jobGroupName, String triggerName, String triggerGroupName, Class jobClass, String cron,Object...objects){
       JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroupName).build();
       if(objects != null){
           for(int i =0;i<objects.length;i++){
               jobDetail.getJobDataMap().put("data"+i, objects[i]);
           }
       }
      // jobDetail.getJobDataMap().put("count", 1);
        Scheduler scheduler = schedulerFactoryBean.getObject();
       TriggerBuilder<Trigger> triggerTriggerBuilder = TriggerBuilder.newTrigger();
       triggerTriggerBuilder.withIdentity(triggerName, triggerGroupName);
       triggerTriggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
       //triggerTriggerBuilder.startNow();
       // triggerTriggerBuilder.endAt()
       CronTrigger trigger = (CronTrigger) triggerTriggerBuilder.build();
        try {
            scheduler.scheduleJob(jobDetail,trigger);
          //  scheduler.standby(); // 不会立即执行 挂起 ?
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
    //获取全部任务
    public void getAllJob(){
        Scheduler scheduler = schedulerFactoryBean.getObject();
        try {
         List<String> list =   scheduler.getJobGroupNames();
         for(String groupName : list){
             for(JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))){
                 if(jobKey.getGroup().equals("RECOVERING_JOBS")){
                     continue;
                 }
                 String jobName = jobKey.getName();
                 String jobgroupName = jobKey.getGroup();
                 System.out.println(jobName+"========job========="+jobgroupName);
                List<Trigger> list1 = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
                for (Trigger s : list1){
                    TriggerKey triggerKey =   s.getKey();
                    if(triggerKey.getGroup().equals("RECOVERING_JOBS")){
                        continue;
                    }
                    System.out.println(triggerKey.getGroup()+"=====triggerKey========"+triggerKey.getName());

                }
             }

         }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
    //暂停全部任务
    public void stopAllJob(){
        Scheduler scheduler = schedulerFactoryBean.getObject();

        try {
            List<String> list =   scheduler.getJobGroupNames();
            for(String groupName : list){
                for(JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))){
                    if(jobKey.getGroup().equals("RECOVERING_JOBS")){
                        continue;
                    }
                    String jobName = jobKey.getName();
                    String jobgroupName = jobKey.getGroup();
                    List<Trigger> list1 = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
                    for (Trigger s : list1){
                        TriggerKey triggerKey =   s.getKey();
                        if(triggerKey.getGroup().equals("RECOVERING_JOBS")){
                            continue;
                        }
                        //得到trigger的状态
                        String status = getTriggerState(triggerKey.getName(),triggerKey.getGroup());
                        if(!status.equals("PAUSED")){
                            stopJob(jobName,jobgroupName);
                        }
                    }
                }

            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
