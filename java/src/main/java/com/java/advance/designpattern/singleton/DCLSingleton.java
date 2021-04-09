package com.java.advance.designpattern.singleton;

/** 单例模式-双重检查
 * @author wyw
 * @date 2021/04/09
 */
public class DCLSingleton {

    private volatile static DCLSingleton instance = null;

    private DCLSingleton() {

    }

    public static DCLSingleton getInstance() {
        if (instance == null) {
            synchronized (DCLSingleton.class) {
                if (instance == null) {
                    instance = new DCLSingleton();
                }
            }
        }
        return instance;
    }
}
