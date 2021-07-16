package com.wyw.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @Descroption:
 * @Author: wangyw
 * @Date: 2021/7/16 15:07
 */
public class RabbitMQConnectionUtil {

    public static Connection getConnection() throws Exception {
        //ConnectionFactory 和 Channel都实现了Cloneable接口,无需手动关闭
        //定义连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置服务地址
        factory.setHost("localhost");
        //端口
        factory.setPort(5672);
        //设置账号信息，用户名、密码、vhost
        //后续计划接阿波罗
        factory.setVirtualHost("basic");
        factory.setUsername("admin");
        factory.setPassword("admin");
        // 通过工程获取连接
        Connection connection = factory.newConnection();
        return connection;
    }
}
