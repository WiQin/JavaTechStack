package com.wyw.routing;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.wyw.enums.ExchangeEnum;
import com.wyw.enums.QueueEnum;
import com.wyw.enums.RoutingKeyEnum;
import com.wyw.util.RabbitMQConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


/**
 * @Descroption: 路由模式
 * 1、每个消费者监听自己的队列，并且设置routingkey。
 * 2、生产者将消息发给交换机，由交换机根据routingkey来转发消息到指定的队列。
 * @Author: wangyw
 * @Date: 2021/7/20 11:41
 */
public class RoutingSender {
    public static void main(String[] args) {

        Connection connection = null;
        Channel channel = null;
        try {
            String exchange = ExchangeEnum.DIRECT_INFORM.getCode();

            String queue_1 = QueueEnum.ROUTING_1.getCode();
            String queue_2 = QueueEnum.ROUTING_2.getCode();

            String routing_1 = RoutingKeyEnum.ROUTING_KEY_1.getCode();
            String routing_2 = RoutingKeyEnum.ROUTING_KEY_2.getCode();

            //创建连接
            connection = RabbitMQConnectionUtil.getConnection();

            //创建与交换机的通道，每个通道代表一个会话
            channel = connection.createChannel();
            //声明交换机 String exchange, BuiltinExchangeType type
            /**
             * 参数明细
             * 1、交换机名称
             * 2、交换机类型，fanout、topic、direct、headers
             */
            channel.exchangeDeclare(exchange, BuiltinExchangeType.DIRECT);
            /**
             * 参数明细：
             * 1、队列名称
             * 2、是否持久化
             * 3、是否独占此队列
             * 4、队列不用是否自动删除
             * 5、参数
             */
            channel.queueDeclare(queue_1, true, false, false, null);
            channel.queueDeclare(queue_2, true, false, false, null);
            //交换机和队列绑定String queue, String exchange, String routingKey
            /**
             * 参数明细
             * 1、队列名称
             * 2、交换机名称
             * 3、路由key
             */
            channel.queueBind(queue_1,exchange, routing_1);
            channel.queueBind(queue_2,exchange,routing_2);
            //发送邮件消息
            for (int i=0;i<10;i++){
                String message = "email inform to user"+i;
                //向交换机发送消息 String exchange, String routingKey, BasicProperties props, byte[] body
                /**
                 * 参数明细
                 * 1、交换机名称，不指令使用默认交换机名称 Default Exchange
                 * 2、routingKey（路由key），根据key名称将消息转发到具体的队列，这里填写队列名称表示消息将发到此队列
                 * 3、消息属性
                 * 4、消息内容
                 */
                channel.basicPublish(exchange, routing_1, null, message.getBytes());
                System.out.println("Send Message is:'" + message + "'");
            }
            //发送短信消息
            for (int i=0;i<10;i++){
                String message = "sms inform to user"+i;
                //向交换机发送消息 String exchange, String routingKey, BasicProperties props, byte[] body
                channel.basicPublish(exchange, routing_2, null, message.getBytes());
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
