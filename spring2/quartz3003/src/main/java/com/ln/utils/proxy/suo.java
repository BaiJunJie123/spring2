package com.ln.utils.proxy;

import java.util.Date;

/**
 * <P>对类的进行描述</P>
 *
 * @version: v1.0.0
 * @author: baijj
 * @date: 2020-02-01 23:02
 */
public class suo {

    public void zhu(){
        synchronized (suo.class){
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(" 这是一个对象锁"+new Date().toString());
        }
    }
}
