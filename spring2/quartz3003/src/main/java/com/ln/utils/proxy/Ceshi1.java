package com.ln.utils.proxy;

/**
 * <P>对类的进行描述</P>
 *
 * @version: v1.0.0
 * @author: baijj
 * @date: 2020-01-31 16:48
 */
public class Ceshi1 {

    public static void main(String[] args) {

        //Daili a = new Daili();
        //a.name = "bjj";
        ////ChuLilei c = new ChuLilei(a);
        ////c.write();
        //DongTai d = new DongTai();
        //CeProxy cc =(CeProxy)  d.bind(a);
        //
        //cc.write();

        suo s = new suo();
        suo z = new suo();


         Thread t1 = new Thread(new Runnable(){

             @Override
             public void run() {
                 s.zhu();
             }
         });

        Thread t2 = new Thread(new Runnable(){

            @Override
            public void run() {
                s.zhu();
            }
        });

        Thread t3 = new Thread(new Runnable(){

            @Override
            public void run() {
                z.zhu();
            }
        });

        Thread t4 = new Thread(new Runnable(){

            @Override
            public void run() {
                z.zhu();
            }
        });


        t1.start();
        t2.start();
        t3.start();
        t4.start();



    }
}
