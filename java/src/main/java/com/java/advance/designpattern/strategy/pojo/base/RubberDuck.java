package com.java.advance.designpattern.strategy.pojo.base;

/**
 * @author wangyw
 * @version 1.0
 * @description: TODO
 * @date 2021/8/5 0005 22:08
 */
public class RubberDuck extends AbstractDuck{

    @Override
    protected void quack() {
        System.out.println("zhi zhi");
    }

    @Override
    protected void display() {
        System.out.println("橡皮鸭");
    }
}
