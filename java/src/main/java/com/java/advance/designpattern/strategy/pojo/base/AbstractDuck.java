package com.java.advance.designpattern.strategy.pojo.base;


public abstract class AbstractDuck {

    //抽象方法，交由子类去实现
    protected abstract void display();

    protected void swim() {
        System.out.println("hua la la");
    }

    /*-------下面的方法不是所有子类实现都一样，可以想办法优化-------------*/
    protected void quack() {
        System.out.println("gua gua");
    }

    protected void fly() {
        //emm..好像有什么问题，不是所有人的鸭子都会飞
        System.out.println("I believe I can fly");
    }
}
