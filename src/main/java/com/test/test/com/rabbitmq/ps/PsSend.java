package com.test.test.com.rabbitmq.ps;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.test.test.com.rabbitmq.ConnnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 订阅模式
 */
public class PsSend {
    private static final String EXCHANGE_NAME="exchange1_fanout";
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        //声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");//交换机类型：分发
        //发送消息
        String msg="hello pssend";
        channel.basicPublish(EXCHANGE_NAME,"",false,null,msg.getBytes());
        channel.close();
        connection.close();
    }

}
