package com.ln.utils.proxy;

/**
 * <P>对类的进行描述</P>
 *
 * @version: v1.0.0
 * @author: baijj
 * @date: 2020-01-31 16:42
 */
public class Daili implements  CeProxy {
    public String name;
    @Override
    public void write() {
        System.out.println("this "+ name +" 代理写了代码");
    }
}