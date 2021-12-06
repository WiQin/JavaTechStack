package com.wyw.boot_mq;

import com.wyw.RecverApplication;
import com.wyw.config.RabbitMQConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;
import sun.plugin2.message.Message;

import java.nio.channels.Channel;

/**
 * @Descroption:
 * @Author: wangyw
 * @Date: 2021/7/21 11:37
 */
@Component
public class SpringBootRecver {
    //监听email队列
    @RabbitListener(queues = {RabbitMQConfig.QUEUE_INFORM_EMAIL})
    public void receive_email(String msg, Message message, Channel channel){
        System.out.println(msg);
    }

    //监听sms队列
    @RabbitListener(queues = {RabbitMQConfig.QUEUE_INFORM_SMS})
    public void receive_sms(String msg,Message message,Channel channel){
        System.out.println(msg);
    }
}
