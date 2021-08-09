package com.java.advance.designpattern.decorator.base.decorator;

import com.java.advance.designpattern.decorator.base.Beverage;

/**
 * @author wangyw
 * @version 1.0
 * @description: TODO
 * @date 2021/8/9 0009 21:04
 */
public abstract class CondimentDecorator extends Beverage {

    //所有的装饰着都必须重新实现getDescription方法
    public abstract String getDescription();
}
