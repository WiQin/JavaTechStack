package com.wyw.spring.proxy.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 反射测试
 */
public class ReflectTest {

    public static void main(String[] args) {
//        reflect();
//        reflectOfArg();
        reflectOfArgs();
    }

    /**
     * 无参对象
     */
    private static void reflect() {
        ReflectServiceImpl object = null;
        try {
            object = (ReflectServiceImpl) Class.forName("com.wyw.spring.proxy.reflect.ReflectServiceImpl").newInstance();
            Method method = object.getClass().getMethod("doWork", String.class);
            method.invoke(object, "无参数对象");

        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | ClassNotFoundException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 单参对象
     */
    private static void reflectOfArg() {
        ReflectServiceImpl2 object = null;
        try {
            object = (ReflectServiceImpl2) Class.forName("com.wyw.spring.proxy.reflect.ReflectServiceImpl2").
                    getConstructor(String.class).newInstance("单参数对象");
            Method method = object.getClass().getMethod("doWork");
            method.invoke(object);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | ClassNotFoundException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 多参对象
     */
    private static void reflectOfArgs() {
        ReflectServiceImpl3 object = null;
        try {
            object = (ReflectServiceImpl3) Class.forName("com.wyw.spring.proxy.reflect.ReflectServiceImpl3").
                    getConstructor(String.class,String.class).newInstance("多参数对象","执行方法");
            Method method = object.getClass().getMethod("doWork");
            method.invoke(object);
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | ClassNotFoundException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
