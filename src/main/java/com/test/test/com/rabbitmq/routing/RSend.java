package com.test.test.com.rabbitmq.routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.test.test.com.rabbitmq.ConnnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 路由模式
 */
public class RSend {
    private static final String EXCHANGENAME="routing_exchange";
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGENAME,"direct");
        String msg="hello diret";
        String routingKey="error";
        channel.basicPublish(EXCHANGENAME,routingKey,false,false,null,msg.getBytes());
        channel.close();
        connection.close();

    }
}
