package com.test.test.com.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMQSends {

    private static final String QUEUE_NAME="simple-queue-test";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        String msg="hello simplequeue";
        channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());
        System.out.println("send--------"+msg);
        channel.close();
        connection.close();
    }
}
