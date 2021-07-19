package com.wyw.publish_subscribe;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.wyw.enums.ExchangeEnum;
import com.wyw.util.RabbitMQConnectionUtil;

/**
 * @Descroption: 发布-订阅模式  生产者：交换机：队列：消费者  1：1：n：n
 * 生产者->交换机->队列->消费者
 *
 * 交换机类型：direct  topic  headers  fanout
 * @Author: wangyw
 * @Date: 2021/7/16 17:22
 */
public class Publisher {

    public static void main(String[] args) {
        try {
            Connection connection = RabbitMQConnectionUtil.getConnection();
            Channel channel = connection.createChannel();
            String exchangeName = ExchangeEnum.LOG.getCode();
            String message = "测试消息";
            channel.exchangeDeclare(exchangeName, BuiltinExchangeType.FANOUT);
            //生成一个非持久，独占，自动删除队列（名称随机）
            channel.queueDeclare();
            channel.basicPublish(exchangeName,"",null,message.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
