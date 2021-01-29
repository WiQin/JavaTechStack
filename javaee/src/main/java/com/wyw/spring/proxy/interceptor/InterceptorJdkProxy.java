package com.wyw.spring.proxy.interceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * jdk动态代理中实现拦截器
 */
public class InterceptorJdkProxy implements InvocationHandler {

    //真实对象
    private Object target;
    //拦截器全限定名
    private String interceptorClass = null;

    public InterceptorJdkProxy(Object target, String interceptorClass) {
        this.target = target;
        this.interceptorClass = interceptorClass;
    }

    public static Object bind(Object target, String interceptorClass) {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),new InterceptorJdkProxy(target,interceptorClass));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (interceptorClass == null) {
            //没有设置拦截器就反射原有方法
            return method.invoke(target,args);
        }

        Object result = null;
        //通过反射生成拦截器
        Interceptor interceptor = (Interceptor)Class.forName(interceptorClass).newInstance();
        //调用前置方法
        if (interceptor.before(proxy, target, method, args)) {
            //反射原有对象方法
            result = method.invoke(target, args);
        } else {
            //返回false执行around方法
            interceptor.around(proxy,target,method,args);
        }
        //调用后置方法
        interceptor.after(proxy,target,method,args);
        return result;
    }
}
