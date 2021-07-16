package com.wyw.queues;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.wyw.enums.QueueEnum;
import com.wyw.util.RabbitMQConnectionUtil;

/**
 * @Descroption: 工作队列模式  生产者：队列：消费者  1：1：n
 * @Author: wangyw
 * @Date: 2021/7/16 16:04
 */
public class QueueSender {

    public static void main(String[] args) {

        try {
            Connection connection = RabbitMQConnectionUtil.getConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(QueueEnum.WORK_QUEUE.getCode(), false, false, false, null);

            for (int i = 0; i < 1000; i++) {
                String message = "第" + i + "条消息";
                channel.basicPublish("", QueueEnum.WORK_QUEUE.getCode(), null, message.getBytes());
                System.out.println("send message:"+message);

                Thread.sleep(i * 10);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
