package com.wyw.queues;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;
import com.wyw.enums.QueueEnum;
import com.wyw.util.RabbitMQConnectionUtil;

/**
 * @Descroption:
 * @Author: wangyw
 * @Date: 2021/7/16 16:20
 */
public class QueueRecver2 {

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
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
            };

            channel.basicConsume(QueueEnum.WORK_QUEUE.getCode(), false, deliverCallback, tag -> {});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
