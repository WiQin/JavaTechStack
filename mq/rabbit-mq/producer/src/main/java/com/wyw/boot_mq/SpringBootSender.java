package com.wyw.boot_mq;

import com.wyw.SenderApplication;
import com.wyw.config.RabbitMQConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Descroption:
 * @Author: wangyw
 * @Date: 2021/7/21 11:30
 */
@SpringBootTest(classes = SenderApplication.class)
@RunWith(SpringRunner.class)
public class SpringBootSender {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    public void testSend() {
        for (int i=0;i<5;i++){
            String message = "sms email inform to user"+i;
            rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_TOPICS_INFORM,"inform.sms.email",message);
            System.out.println("Send Message is:'" + message + "'");
        }
    }

}
