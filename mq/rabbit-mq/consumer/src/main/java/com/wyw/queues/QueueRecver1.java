package com.wyw.queues;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;
import com.wyw.enums.QueueEnum;
import com.wyw.util.RabbitMQConnectionUtil;

/**
 * @Descroption:
 * @Author: wangyw
 * @Date: 2021/7/16 16:05
 */
public class QueueRecver1 {

    public static void main(String[] args) {

        try {
            Connection connection = RabbitMQConnectionUtil.getConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(QueueEnum.WORK_QUEUE.getCode(), false, false, false, null);
            //同一时刻服务器只发一条消息给消费者
            channel.basicQos(1);

            DeliverCallback deliverCallback = (tag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println("received message:" + message);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //使用手动确认模式
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
            };
            //false表示手动sck
            channel.basicConsume(QueueEnum.WORK_QUEUE.getCode(), false, deliverCallback, tag -> {});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
