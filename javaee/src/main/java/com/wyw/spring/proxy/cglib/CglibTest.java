package com.wyw.spring.proxy.cglib;

import com.wyw.spring.proxy.service.DemoService;
import com.wyw.spring.proxy.service.DemoServiceImpl;

public class CglibTest {

    public static void main(String[] args) {
        CglibProxyExample cpe = new CglibProxyExample();
        DemoService service = (DemoServiceImpl)cpe.getProxy(DemoServiceImpl.class);
        service.doWork();
    }
}
