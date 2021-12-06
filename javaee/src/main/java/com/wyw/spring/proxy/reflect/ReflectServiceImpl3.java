package com.wyw.spring.proxy.reflect;

public class ReflectServiceImpl3 {
    private String name;
    private String work;

    public ReflectServiceImpl3(String name,String work) {
        this.name = name;
        this.work = work;
    }

    public void doWork() {
        System.out.println(name + "start to "+work);
    }
}
