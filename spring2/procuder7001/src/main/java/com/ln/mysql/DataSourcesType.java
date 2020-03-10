package com.ln.mysql;

import org.springframework.stereotype.Component;

/**
 * @author 白俊杰
 * @Date 2019/12/9
 * @Description
 **/
@Component
public class DataSourcesType {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    //write
    public static void setDataType(String typeName){

        threadLocal.set(typeName);
    }
    //read
    public static String getDataType(){
       return threadLocal.get();
    }

    //remove
    public static void remvode(){
        threadLocal.remove();
    }
}
