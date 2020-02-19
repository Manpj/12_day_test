package com.test.test.com.rabbitmq.workqueue;

import com.rabbitmq.client.*;
import com.test.test.com.rabbitmq.ConnnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Workqueuerecv1 {
    private static final String QUEUE_NAME="work_queue";
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //定义一个消费者
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg=new String(body);
                System.out.println("workqueuerecv1---------"+msg);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        //监控消费者
        channel.basicConsume(QUEUE_NAME,false,defaultConsumer);

    }
}
