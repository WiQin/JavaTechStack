package com.java.advance.designpattern.strategy.pojo.strategy;

import com.java.advance.designpattern.strategy.dynamic.service.FlyBehavior;
import com.java.advance.designpattern.strategy.dynamic.service.QuackBehavior;

/**
 * @author wangyw
 * @version 1.0
 * @description: TODO
 * @date 2021/8/5 0005 22:28
 */
public abstract class SuperDuck {

    protected FlyBehavior flyBehavior;

    protected QuackBehavior quackBehavior;

    //抽象方法，交由子类去实现
    protected abstract void display();

    protected void swim() {
        System.out.println("hua la la");
    }

    public void performFly() {
        flyBehavior.fly();
    }

    public void performQuack() {
        quackBehavior.quack();
    }

    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    public void setQuackBehavior(QuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }
}
