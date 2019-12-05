package com.ln.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author 白俊杰
 * @Date 2019/12/3
 * @Description
 **/
@Component
public class Start implements ApplicationRunner {

    @Autowired
    public QuartzUtils quartzUtils;
    @Override
    public void run(ApplicationArguments args) throws Exception {
      //  System.out.println(quartzUtils+"==============================start..................");
        //quartzUtils.deleteTrigger("t111","tg1111");
       // quartzUtils.buildJobAndStart(Myjob2.class, "j111", "g111", "t111", "tg1111", "*/10 * * * * ?");
    }
}
