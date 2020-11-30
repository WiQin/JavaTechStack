package com.wyw.dubbo.service;

import com.wyw.micro.api.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * 基于xml配置
 */
public class XmlService {

    public static void main(String[] args) throws IOException {
        doConsume();
    }

    public static void doConsume() throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-dubbo.xml");
        context.start();
        System.out.println("开始调用服务");
        UserService userService = (UserService)context.getBean("userService");
        String userInfo = userService.getUserInfo(1111L);
        System.out.println("调用服务，得到结果："+userInfo);
        System.in.read();
    }
}
