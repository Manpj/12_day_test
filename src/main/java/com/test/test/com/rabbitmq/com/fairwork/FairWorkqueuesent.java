package com.test.test.com.rabbitmq.com.fairwork;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.test.test.com.rabbitmq.ConnnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class FairWorkqueuesent {

    private static final String QUEUE_NAME="work_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);
        for(int i=0;i<50;i++){
            //发布消息
            String msg="fair work_queue"+i;
            channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());
        }
        channel.close();
        connection.close();
    }
}
