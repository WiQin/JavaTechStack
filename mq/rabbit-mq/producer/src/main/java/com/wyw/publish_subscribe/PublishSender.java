package com.wyw.publish_subscribe;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.wyw.enums.ExchangeEnum;
import com.wyw.enums.QueueEnum;
import com.wyw.util.RabbitMQConnectionUtil;

/**
 * @Descroption: 发布-订阅模式  生产者：交换机：队列：消费者  1：1：n：n
 * 生产者->交换机->队列->消费者
 * 使用生产者发送若干条消息，每条消息都转发到各各队列，每消费者都接收到了消息
 *
 * 交换机类型：direct  topic  headers  fanout
 * @Author: wangyw
 * @Date: 2021/7/16 17:22
 */
public class PublishSender {

    public static void main(String[] args) {
        try {
            Connection connection = RabbitMQConnectionUtil.getConnection();
            Channel channel = connection.createChannel();
            String exchangeName = ExchangeEnum.FANOOUT_INFORM.getCode();
            //声明交换机
            /*
             * 参数：交换机名称,类型
             */
            channel.exchangeDeclare(exchangeName, BuiltinExchangeType.FANOUT);
            //声明队列
            /*
            参数：队列名称,是否持久化,是否独占队列,队列不用是否自动删除,参数
             */
            channel.queueDeclare(QueueEnum.PUBLISH_SUBSCRIBE_1.getCode(),true,false,false,null);
            channel.queueDeclare(QueueEnum.PUBLISH_SUBSCRIBE_2.getCode(),true,false,false,null);
            //交换机和队列绑定
            /*
            参数：队列名称,交换机名称,路由key
             */
            channel.queueBind(QueueEnum.PUBLISH_SUBSCRIBE_1.getCode(),exchangeName,"");
            channel.queueBind(QueueEnum.PUBLISH_SUBSCRIBE_2.getCode(),exchangeName,"");

            for (int i = 0; i < 100; i++) {
                String message = "inform to user"+i;
                //向交换机发送消息
                /*
                参数：交换机名称,routingkey,消息属性,消息内容
                 */
                channel.basicPublish(exchangeName,"",null,message.getBytes());
                System.out.println("send message"+message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
