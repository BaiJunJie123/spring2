package com.ln.config;

import com.ln.utils.Jianting;
import com.ln.utils.Myjob2;
import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;
import java.util.Properties;

/**
 * @author 白俊杰
 * @Date 2019/12/3
 * @Description
 **/
@Configuration
public class QuartzConfig {

    @Autowired
    private AutowiringSpringBeanJobFactory autowiringSpringBeanJobFactory;

    private static final String QUARTZ_CONFIG = "/quartz.properties";
    // ---------------------------- jobDetail 实列---------------------------
    @Bean
    public JobDetailFactoryBean jobDetail() {
        JobDetailFactoryBean bean = new JobDetailFactoryBean();
        bean.setJobClass(Myjob2.class);
        bean.setDurability(true);
        bean.setRequestsRecovery(true);
        // 给jobs添加trigger触发器，不加这句话，我这里第二次启动时会报错（未添加触发器）
        bean.setDurability(true);
        bean.setName("job5");
        bean.setGroup("ceshi_Group_jobdeil");
        return bean;
    }


    // 配置触发器，这里触发器使用的是cronTrigger，没有使用simpleTrigger，
    @Bean
    public CronTriggerFactoryBean cornTriggerFactoryBean(JobDetail jobDetail) {
        CronTriggerFactoryBean bean = new CronTriggerFactoryBean();
        bean.setCronExpression("*/10 * * * * ?");//5s一次
        bean.setJobDetail(jobDetail);
        //bean.setStartDelay(20);
        bean.setName("job5_cron");
        bean.setGroup("ceshi_Group_jobcron");
        return bean;
    }
    // ------------------------- 监听器-------------------------------------------------
    private Jianting getJianting(){
        return new Jianting();
    }

    // --------------------------schedulerFacctoryBean --------------------------------------
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(DataSourceTransactionManager dataSourceTransactionManager) {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        //自动覆写存在的job
        bean.setOverwriteExistingJobs(true);
        //配置数据源，使用与项目一致的数据源
        bean.setDataSource(dataSourceTransactionManager.getDataSource());
        //将spring管理job自定义工厂交由调度器维护
        bean.setJobFactory(autowiringSpringBeanJobFactory);
        //设置调度器自动运行
        bean.setAutoStartup(true);
        //设置定时调度器命名空间  （集群名）
      //  bean.setSchedulerName("MY-QUARTZ-SCHEDULER");
        //设置存储在quartz上文中的Spring应用上下文key
        bean.setApplicationContextSchedulerContextKey("applicationContext");
       // bean.setGlobalJobListeners();
       // bean.setGlobalTriggerListeners();
        bean.setSchedulerListeners(getJianting());
        try {
            //读取配置文件
            bean.setQuartzProperties(quartzProperties());
        } catch (IOException e) {
            e.printStackTrace();
        }
        bean.setTransactionManager(dataSourceTransactionManager);
        //bean.setStartupDelay(10);
        //读取配置文件
       // bean.setConfigLocation(new ClassPathResource(QUARTZ_CONFIG));
       // bean.setTriggers(cornTriggerFactoryBean.getObject());

        return bean;
    }


    //------------------------------properties--------------------------------------
    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource(QUARTZ_CONFIG));
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }

}
