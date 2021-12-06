package com.java.advance.designpattern.singleton;

import java.util.Objects;

/**
 * @author wangyw
 * @version 1.0
 * @description: TODO
 * @date 2021/8/11 0011 22:21
 */
public class BaseSingleton {
    private static BaseSingleton instance = null;

    private BaseSingleton() {}

    public BaseSingleton getInstance() {
        if (Objects.isNull(instance)) {
            instance = new BaseSingleton();
        }
        return instance;
    }
}
