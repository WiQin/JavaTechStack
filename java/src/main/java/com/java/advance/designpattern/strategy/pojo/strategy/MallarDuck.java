package com.java.advance.designpattern.strategy.pojo.strategy;

import com.java.advance.designpattern.strategy.dynamic.service.impl.FlyWithWings;
import com.java.advance.designpattern.strategy.dynamic.service.impl.Spueak;

/**
 * @author wangyw
 * @version 1.0
 * @description: TODO
 * @date 2021/8/5 0005 22:34
 */
public class MallarDuck extends SuperDuck {

    public MallarDuck() {
        this.flyBehavior = new FlyWithWings();
        this.quackBehavior = new Spueak();
    }

    @Override
    protected void display() {

    }
}
