package com.java.advance.designpattern.singleton;

import java.util.Objects;

/**
 * @author wangyw
 * @version 1.0
 * @description: TODO
 * @date 2021/8/11 0011 22:41
 */
public class SynchronizedSingleton {
    private static SynchronizedSingleton instance = null;

    private SynchronizedSingleton() {}

    public synchronized SynchronizedSingleton getInstance() {
        if (Objects.isNull(instance)) {
            instance = new SynchronizedSingleton();
        }
        return instance;
    }
}
