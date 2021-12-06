package com.wyw.header;

import com.rabbitmq.client.*;
import com.wyw.util.RabbitMQConnectionUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Descroption:
 * @Author: wangyw
 * @Date: 2021/7/21 10:30
 */
public class HeaderRecver2 {

    //队列名称
    private static final String QUEUE_INFORM_SMS = "queue_inform_sms";
    private static final String EXCHANGE_HEADERS_INFORM = "exchange_headers_inform";

    public static void main(String[] args) throws Exception {
        Connection connection = RabbitMQConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_HEADERS_INFORM, BuiltinExchangeType.HEADERS);
        Map<String, Object> headers_email = new HashMap<String, Object>();
        headers_email.put("inform_email", "email");
        //交换机和队列绑定
        channel.queueBind(QUEUE_INFORM_SMS, EXCHANGE_HEADERS_INFORM, "", headers_email);
        //定义消费方法
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                long deliveryTag = envelope.getDeliveryTag();
                String exchange = envelope.getExchange();
                //消息内容
                String message = new String(body, "utf-8");
                System.out.println(message);
            }
        };
        //指定消费队列
        channel.basicConsume(QUEUE_INFORM_SMS, true, consumer);

    }
}
