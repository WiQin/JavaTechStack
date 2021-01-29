package com.wyw.spring.proxy.reflect;

public class ReflectServiceImpl2 {

    private String name;

    public ReflectServiceImpl2(String name) {
        this.name = name;
    }

    public void doWork() {
        System.out.println(name + "start to work");
    }
}
