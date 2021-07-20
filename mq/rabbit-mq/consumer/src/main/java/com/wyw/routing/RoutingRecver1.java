package com.wyw.routing;

import com.rabbitmq.client.*;
import com.wyw.enums.ExchangeEnum;
import com.wyw.enums.QueueEnum;
import com.wyw.enums.RoutingKeyEnum;
import com.wyw.util.RabbitMQConnectionUtil;

import java.io.IOException;

/**
 * @Descroption:
 * @Author: wangyw
 * @Date: 2021/7/20 14:01
 */
public class RoutingRecver1 {

    public static void main(String[] args) throws Exception {

        String exchange = ExchangeEnum.DIRECT_INFORM.getCode();
        String queue_1 = QueueEnum.ROUTING_1.getCode();
        String routing_1 = RoutingKeyEnum.ROUTING_KEY_1.getCode();

        //创建一个连接
        Connection connection = RabbitMQConnectionUtil.getConnection();
        //创建与交换机的通道，每个通道代表一个会话
        Channel channel = connection.createChannel();
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
        //交换机和队列绑定String queue, String exchange, String routingKey
        /**
         * 参数明细
         * 1、队列名称
         * 2、交换机名称
         * 3、路由key
         */
        channel.queueBind(queue_1,exchange,routing_1);
        //定义消费方法
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                long deliveryTag = envelope.getDeliveryTag();
                String exchange = envelope.getExchange();
                //消息内容
                String message = new String(body, "utf-8");
                System.out.println(message);
            }
        };
        /**
         * 监听队列String queue, boolean autoAck,Consumer callback
         * 参数明细
         * 1、队列名称
         * 2、是否自动回复，设置为true为表示消息接收到自动向mq回复接收到了，mq接收到回复会删除消息，设置为false则需要手动回复
         * 3、消费消息的方法，消费者接收到消息后调用此方法
         */
        channel.basicConsume(queue_1, true, defaultConsumer);

    }
}
