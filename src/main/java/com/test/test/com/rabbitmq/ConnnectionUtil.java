package com.test.test.com.rabbitmq;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConnnectionUtil {


    public static Connection getConnection() throws IOException, TimeoutException {
        ConnectionFactory factory=new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        factory.setVirtualHost("/vhost");
        factory.setUsername("usertest");
        factory.setPassword("usertest");
        return factory.newConnection();

    }

}
