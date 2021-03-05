package com.java.advance.concurrency.thread;

/**
 * 线程方法
 */
public class D03_ThreadMethod {

    //sleep
    static void testSleep() {
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //yield  它能够让当前线程从“运行状态”进入到“就绪状态”，从而让其他等待线程获取执行权，但是不能保证在当前线程调用yield()之后，其他线程就一定能获得执行权，也有可能是当前线程又回到“运行状态”继续运行
    static void testYield() {
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("A"+i);
                if (i%10 == 0) Thread.yield();
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("B"+i);
                if (i%10 == 0) Thread.yield();
            }
        }).start();
    }

    static void testJoin() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("t1:" + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t2");
        });
        t1.start();
        t2.start();

    }

    public static void main(String[] args) {
//        testSleep();
//        testYield();
        testJoin();
    }
}
