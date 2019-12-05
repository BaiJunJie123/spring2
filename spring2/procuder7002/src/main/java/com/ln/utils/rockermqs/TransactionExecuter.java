package com.ln.utils.rockermqs;

import org.apache.rocketmq.client.producer.LocalTransactionExecuter;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.common.message.Message;

/**
 * @author 白俊杰
 * @Date 2019/11/27
 * @Description
 **/
public class TransactionExecuter implements LocalTransactionExecuter {
    @Override
    public LocalTransactionState executeLocalTransactionBranch(Message message, Object o) {
        System.out.println(message.toString());
        System.out.println("message = "+new String(message.getBody()));
        System.out.println(o.toString()+"======arg");
        if(o.toString() == "bjj4"){
            return  LocalTransactionState.UNKNOW;
        }
        //此处执行数据库本地事务操作
        //LocalTransactionState.ROLLBACK_MESSAGE  数据库操作失败则发送rokkback
        return LocalTransactionState.COMMIT_MESSAGE;
    }
}
