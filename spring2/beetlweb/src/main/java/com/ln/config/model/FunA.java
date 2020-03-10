package com.ln.config.model;


import org.beetl.core.Context;
import org.beetl.core.Function;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * <P>对类的进行描述</P>
 *
 * @version: v1.0.0
 * @author: baijj
 * @date: 2020-01-17 13:32
 */
public class FunA implements Function {
    @Override
    public Object call(Object[] objects, Context context) {
        Object o = objects[0];
        Object o1 = objects[1];
        System.out.println("========="+o.toString()+"========="+o1.toString());
        try {
            context.byteWriter.write("bjj and you li hai".toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
