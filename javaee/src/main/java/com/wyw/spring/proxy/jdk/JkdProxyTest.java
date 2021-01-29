package com.wyw.spring.proxy.jdk;

import com.wyw.spring.proxy.service.DemoService;
import com.wyw.spring.proxy.service.DemoServiceImpl;

public class JkdProxyTest {
    public static void main(String[] args) {
        JdkProxyExample jdkProxy = new JdkProxyExample();
        DemoService proxy = (DemoService)jdkProxy.bind(new DemoServiceImpl());
        proxy.doWork();
    }
}
