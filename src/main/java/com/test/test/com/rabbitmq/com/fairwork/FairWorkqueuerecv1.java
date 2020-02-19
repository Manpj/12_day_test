package com.test.test.com.rabbitmq.com.fairwork;

import com.rabbitmq.client.*;
import com.test.test.com.rabbitmq.ConnnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class FairWorkqueuerecv1 {
    private static final String QUEUE_NAME="work_queue";
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //设置每次只接收一下消息
        channel.basicQos(1);
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
                }finally {
                    //确认消息
                    channel.basicAck(envelope.getDeliveryTag(),false);
                }
            }
        };
        //自动应答设置为false
        //监控消费者
        channel.basicConsume(QUEUE_NAME,false,defaultConsumer);

    }
}
