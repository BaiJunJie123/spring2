package com.ln.utils;

import java.io.*;

/**
 * @author 白俊杰
 * @Date 2019/11/23
 * @Description
 **/
public class SerializeUtils {

    public static byte[] serialize(Object object) {
        ObjectOutputStream oos = null;
         ByteArrayOutputStream baos = null;
        try {
             // 序列化
            baos = new ByteArrayOutputStream();
             oos = new ObjectOutputStream(baos);
             oos.writeObject(object);
         byte[] bytes = baos.toByteArray();
         return bytes;
         } catch (Exception e) {
        e.printStackTrace();
        }
         return null;
    }


    public static Object unserialize(byte[] bytes) {
    ByteArrayInputStream bais = null;
        try {
    // 反序列化
    bais = new ByteArrayInputStream(bytes);
    ObjectInputStream ois = new ObjectInputStream(bais);
    return ois.readObject();
    } catch (Exception e) {
        e.printStackTrace();
        }
     return null;
    }

}
