package com.wyw.basic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.wyw.enums.QueueEnum;
import com.wyw.util.RabbitMQConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Descroption:
 * @Author: wangyw
 * @Date: 2021/7/16 14:19
 */
public class Recver {
    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");


        try {
            Connection connection = RabbitMQConnectionUtil.getConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(QueueEnum.BAISC.getCode(), false, false, false, null);
            System.out.println("receiver declared");
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println("received message:" + message);
            };
            channel.basicConsume(QueueEnum.BAISC.getCode(), true, deliverCallback, consumerTag -> {
            });
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } catch (TimeoutException e) {
            e.printStackTrace();
            return;
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }
}
