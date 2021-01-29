package com.wyw.spring.proxy.interceptor;

import com.wyw.spring.proxy.service.DemoService;
import com.wyw.spring.proxy.service.DemoServiceImpl;

/**
 * 拦截器测试
 */
public class InterceptorTest {

    public static void main(String[] args) {
        DemoService demoService = (DemoService)InterceptorJdkProxy.bind(new DemoServiceImpl(),"com.wyw.spring.proxy.interceptor.MyInterceptor");

        demoService.doWork();
    }
}
