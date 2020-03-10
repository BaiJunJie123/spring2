package com.ln.utils.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * <P>动态代理</P>
 *
 * @version: v1.0.0
 * @author: baijj
 * @date: 2020-01-31 19:22
 */
public class DongTai implements InvocationHandler {


    public  Object rults;

    public Object bind(Object obj){

        this.rults = obj;
        return Proxy.newProxyInstance(

                this.rults.getClass().getClassLoader(), this.rults

                        .getClass().getInterfaces(), this);


    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Object result = null;
        System.out.println("问候之前的日志记录...");
        result = method.invoke(this.rults, args);
        return result;
    }
}
