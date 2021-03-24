package com.java.advance.concurrency.keywords.volatile_2;

import java.util.concurrent.TimeUnit;

/**
 * ${Description}
 *
 * @author wyw
 * @date 2021/03/11
 */
public class VolatileDemo_02 {
    volatile boolean running = true; //对比一下有无volatile的情况下，整个程序运行结果的区别
    void m() {
        System.out.println("m start");
        while(running) {
        }
        System.out.println("m end!");
    }

    public static void main(String[] args) {
        VolatileDemo_02 t = new VolatileDemo_02();



        new Thread(t::m, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.running = false;


    }
}
