/*
package com.ln.mysql;

import net.sf.saxon.functions.ConstantFunction;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

*/
/**
 * @author 白俊杰
 * @Date 2019/12/9
 * @Description
 **//*

@Aspect
@Order(0)
@Lazy(false)
@Component
public class SwitchDataSources {
    @Before("execution(* com.ln.service.*.*(..))")
    public void befer(JoinPoint joinPoint){
            String name = joinPoint.getSignature().getName();
            if(name.startsWith("cha")||name.startsWith("find")||name.startsWith("list")||
                    name.startsWith("count")||name.startsWith("select")){
                System.out.println("======select=======");
                DataSourcesType.setDataType("select");
            }else{
                System.out.println("======update=======");
                DataSourcesType.setDataType("update");
            }
    }
}
*/
