package com.wyw.dubbo.service;

import com.wyw.micro.api.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class OrderService {

    public static void main(String[] args) {
        doConsume();
    }

    public static void doConsume() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-dubbo.xml");
        context.start();
        System.out.println("开始调用服务");
        UserService userService = (UserService)context.getBean("userService");
        String userInfo = userService.getUserInfo(1111L);
        System.out.println("调用服务，得到结果："+userInfo);
    }
}
