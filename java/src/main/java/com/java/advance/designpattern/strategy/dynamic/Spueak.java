package com.java.advance.designpattern.strategy.dynamic;

import com.java.advance.designpattern.strategy.dynamic.service.QuackBehavior;

/**
 * @author wangyw
 * @version 1.0
 * @description: TODO
 * @date 2021/8/5 0005 22:25
 */
public class Spueak implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("zhi zhi");
    }
}
