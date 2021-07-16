package com.wyw.base;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.wyw.enums.QueueEnum;
import com.wyw.util.RabbitMQConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Descroption:  基本使用   生产者：队列：消费者  1：1：1
 * @Author: wangyw
 * @Date: 2021/7/16 14:05
 */
public class Sender {

    public static void main(String[] args) {
        String message;
        try {
            Connection connection = RabbitMQConnectionUtil.getConnection();
            message = "hello world";
            Channel channel = connection.createChannel();
            channel.queueDeclare(QueueEnum.BAISC.getCode(), false, false, false, null);
            channel.basicPublish("", QueueEnum.BAISC.getCode(), null, message.getBytes());
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
        System.out.println("send message:"+message);
    }
}
