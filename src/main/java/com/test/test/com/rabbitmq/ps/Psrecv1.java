package com.test.test.com.rabbitmq.ps;

import com.rabbitmq.client.*;
import com.test.test.com.rabbitmq.ConnnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Psrecv1 {

    private static  final  String QUEUENAME="ps_queue";
    private static final String EXCHANGE_NAME="exchange1_fanout";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        //生命队列
        channel.queueDeclare(QUEUENAME,false,false,false,null);
        //绑定交换机
        channel.exchangeBind(QUEUENAME,EXCHANGE_NAME,"");
        //一次接收一个消息
        channel.basicQos(1);
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg=new String(body);
                System.out.println("订阅消息1-----"+msg);
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
        channel.basicConsume(QUEUENAME,false,null);
    }
}
