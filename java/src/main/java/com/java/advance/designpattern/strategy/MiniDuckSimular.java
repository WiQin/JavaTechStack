package com.java.advance.designpattern.strategy;

import com.java.advance.designpattern.strategy.pojo.strategy.MallarDuck;
import com.java.advance.designpattern.strategy.pojo.strategy.SuperDuck;

/**
 * @author wangyw
 * @version 1.0
 * @description: TODO
 * @date 2021/8/5 0005 22:36
 */
public class MiniDuckSimular {

    public static void main(String[] args) {
        SuperDuck mallarDuck = new MallarDuck();

        mallarDuck.performFly();
        mallarDuck.performQuack();
    }
}
