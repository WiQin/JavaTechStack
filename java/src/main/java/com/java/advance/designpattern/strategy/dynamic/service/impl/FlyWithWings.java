package com.java.advance.designpattern.strategy.dynamic.service.impl;

import com.java.advance.designpattern.strategy.dynamic.service.FlyBehavior;

/**
 * @author wangyw
 * @version 1.0
 * @description: TODO
 * @date 2021/8/5 0005 22:21
 */
public class FlyWithWings implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("I believe I can fly");
    }
}
