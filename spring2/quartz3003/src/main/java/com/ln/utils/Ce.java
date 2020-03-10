package com.ln.utils;

import org.junit.jupiter.api.Test;
import org.quartz.Job;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 白俊杰
 * @Date 2019/12/30
 * @Description
 **/
public class Ce {

    @Test
    public void st(){
        Job3 x = new Job3();
        if( x instanceof QuartzJobBean){
            System.out.println("QuartzJobBean 是它的父类");
        }
    }

    @Test
    public void start(){
        String s = "acc";
        Scanner scanner = new Scanner(s);




        //if(scanner.hasNext("\\w+")){
           /* scanner.next();
            MatchResult matchResult =   scanner.match();
            System.out.println(matchResult.start());
            System.out.println("扫描正确");*/

                //Object object = Class.forName("com.ln.utils.Job3").newInstance();
           /* try {
                    for (Class c :  Class.forName("com.ln.utils.Jobjianting").getInterfaces()){

                    System.out.println(c.getName());
                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
*/
        //}
        //Pattern pattern = Pattern.compile("c|b");
        //        //Matcher matcher = pattern.matcher(s);
        //        //System.out.println(matcher.find());
        //        //System.out.println(matcher.lookingAt());
        //
        //        //System.out.println(matcher.start());
        //        //System.out.println(matcher.end());
        //        //System.out.println(matcher.reset("acc"));
    }
}
