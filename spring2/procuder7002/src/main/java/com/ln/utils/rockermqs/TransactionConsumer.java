package com.ln.utils.rockermqs;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.List;

/**
 * @author 白俊杰
 * @Date 2019/11/27
 * @Description
 **/
public class TransactionConsumer {

    private static final String groupName = "transaction_Consumer_group";
    private static  final  String nameSrv = "192.168.127.142:9876;192.168.127.143:9876";

    public static void main(String[] args) {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(groupName);
        consumer.setNamesrvAddr(nameSrv);
        consumer.setMessageModel(MessageModel.CLUSTERING);
        consumer.setConsumeTimeout(50000);
        consumer.registerMessageListener(new Listeners());
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        try {
            consumer.subscribe("transaction_Topic", "*");
            consumer.start();
            System.out.println("消费者启动.......");
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    public static void consumer(){

    }

    static class Listeners implements MessageListenerConcurrently{

        @Override
        public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
            try {
                for(MessageExt e : list){

                    System.out.println(new String(e.getBody())+"=====消费的内容====");
                    System.out.println(e.getTopic()+"=====消费的主题======");

                }
            }catch (Exception e ){
               return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }

            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
    }
}
