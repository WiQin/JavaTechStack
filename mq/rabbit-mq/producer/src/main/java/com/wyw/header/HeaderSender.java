package com.wyw.header;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.wyw.util.RabbitMQConnectionUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * @Descroption:
 * @Author: wangyw
 * @Date: 2021/7/21 10:16
 */
public class HeaderSender {

    //队列名称
    private static final String QUEUE_INFORM_EMAIL = "queue_inform_email";
    private static final String QUEUE_INFORM_SMS = "queue_inform_sms";
    private static final String EXCHANGE_HEADERS_INFORM="exchange_headers_inform";

    public static void main(String[] args) {
        Connection connection = null;
        Channel channel = null;
        try {
            connection = RabbitMQConnectionUtil.getConnection();
            channel = connection.createChannel();

            /**
             * 参数明细
             * 1、交换机名称
             * 2、交换机类型，fanout、topic、direct、headers
             */
            channel.exchangeDeclare(EXCHANGE_HEADERS_INFORM, BuiltinExchangeType.HEADERS);

            Map<String, Object> headers_email = new HashMap<String, Object>();
            headers_email.put("inform_type", "email");
            Map<String, Object> headers_sms = new HashMap<String, Object>();
            headers_sms.put("inform_type", "sms");
            //声明队列
            /**
             * 参数明细：
             * 1、队列名称
             * 2、是否持久化
             * 3、是否独占此队列
             * 4、队列不用是否自动删除
             * 5、参数
             */
//            channel.queueDeclare(QUEUE_INFORM_EMAIL, true, false, false, headers_email);
//            channel.queueDeclare(QUEUE_INFORM_SMS, true, false, false, headers_sms);
            channel.queueBind(QUEUE_INFORM_EMAIL,EXCHANGE_HEADERS_INFORM,"",headers_email);
            channel.queueBind(QUEUE_INFORM_SMS,EXCHANGE_HEADERS_INFORM,"",headers_sms);

            for (int i = 0; i < 10; i++) {
                String message = "email inform to user"+i;
                AMQP.BasicProperties.Builder properties = new AMQP.BasicProperties.Builder();
                properties.headers(headers_email);
                channel.basicPublish(EXCHANGE_HEADERS_INFORM, "", properties.build(), message.getBytes());
                System.out.println("Send Message is:'" + message + "'");
            }
            for (int i = 0; i < 10; i++) {
                String message = "sms inform to user"+i;
                AMQP.BasicProperties.Builder properties = new AMQP.BasicProperties.Builder();
                properties.headers(headers_sms);
                channel.basicPublish(EXCHANGE_HEADERS_INFORM, "", properties.build(), message.getBytes());
                System.out.println("Send Message is:'" + message + "'");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(channel!=null){
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            }
            if(connection!=null){
                try {
                    connection.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
