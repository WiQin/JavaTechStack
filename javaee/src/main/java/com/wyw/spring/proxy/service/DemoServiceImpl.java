package com.wyw.spring.proxy.service;

public class DemoServiceImpl implements DemoService{
    @Override
    public void doWork() {
        System.out.println("start to work");
    }
}
