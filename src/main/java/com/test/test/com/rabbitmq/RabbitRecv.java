package com.test.test.com.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitRecv {
    private  static final String QUEUE_NAME="simple-queue-test";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel){

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message=new String(body);
                System.out.println("Received------"+message);
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };
        channel.basicConsume(QUEUE_NAME,false,defaultConsumer);

    }
}
