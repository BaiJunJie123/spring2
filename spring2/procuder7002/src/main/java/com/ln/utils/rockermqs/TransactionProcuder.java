package com.ln.utils.rockermqs;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.io.UnsupportedEncodingException;

/**
 * @author 白俊杰
 * @Date 2019/11/27
 * @Description   事务详细提供者 目的： 只要解决分布式事务
 **/
public class TransactionProcuder {

    private static final String groupName = "transaction_procuder_group";
    private static  final  String nameSrv = "192.168.127.142:9876;192.168.127.143:9876";

   // private static TransactionExecuter executer = new TransactionExecuter();



     private static  TransactionMQProducer mqProducer =null;

    public static void main(String[] args) {
        try {
            init();
            System.out.println("提供者启动........");
            sendMessages();
        } catch (MQClientException e) {
            e.printStackTrace();
        }

    }

    public static void init() throws MQClientException {
        mqProducer = new TransactionMQProducer(groupName);
        mqProducer.setNamesrvAddr(nameSrv);
        mqProducer.setTransactionListener(new TransactionListener() {
            @Override
            public LocalTransactionState executeLocalTransaction(Message message, Object o) {
                //执行同步本地事务
                System.out.println(message.toString());
                System.out.println("message = "+new String(message.getBody()));
                System.out.println(o.toString()+"======arg");
                if(o.toString().equals("bjj6")){
                    System.out.println("========unkonw=======");
                    return  LocalTransactionState.UNKNOW;
                }
                //此处执行数据库本地事务操作
                //LocalTransactionState.ROLLBACK_MESSAGE  数据库操作失败则发送rokkback
                return LocalTransactionState.COMMIT_MESSAGE;
            }
            // 状态为nukown时 mq会回查 进入此方法携带状态为nukown的消息
            @Override
            public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
                try {
                    System.out.println(new String(messageExt.getBody(),"UTF-8")+"============checkLocalTransaction====");
                    System.out.println(messageExt.toString()+"=====messageExt==============");
                    return  LocalTransactionState.COMMIT_MESSAGE;
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    return LocalTransactionState.ROLLBACK_MESSAGE;
                }
            }
        });

       mqProducer.start();
    }

    //发送消息
    public static void sendMessages(){
        for(int i =5;i<10;i++){

            Message mag = new Message("transaction_Topic","tagA",("key"+i).toString(),("helloTransaction_"+i).getBytes());
            try {
                SendResult sendResult = mqProducer.sendMessageInTransaction(mag, "bjj"+i);
                System.out.println(sendResult+"=========send de message==========");
            } catch (MQClientException e) {
                e.printStackTrace();
            }
        }
    }



}
