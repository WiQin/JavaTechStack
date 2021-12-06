package com.wyw.spring.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * jdk动态代理实现
 */
public class JdkProxyExample implements InvocationHandler {

    /**
     * 真实对象
     */
    private Object target = null;

    /**
     * 绑定真实对象与代理对象，并返回代理对象
     * @param target
     * @return
     */
    public Object bind(Object target) {
        this.target = target;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
        target.getClass().getInterfaces(),this);
    }

    /**
     * 代理方法实现
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("进入代理逻辑方法");
        System.out.println("准备开始调用真实对象逻辑");
        Object obj = method.invoke(target, args);
        System.out.println("调用真实对象后的而业务逻辑");
        return obj;
    }
}
