package com.java.advance.designpattern.decorator.base;

/**
 * @author wangyw
 * @version 1.0
 * @description: TODO
 * @date 2021/8/8 0008 22:31
 */
public abstract class Beverage {
    String description = "Unkonwn Beverage";

    public String getDescription() {
        return description;
    }

    public abstract double cost();
}
