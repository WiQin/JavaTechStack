package com.java.advance.concurrency.keywords.synchrinized_1;

/**
 * 同步方法和非同步方法可以一起执行
 */
public class SynchronizedDemo_04 {

    private synchronized void m() {
        System.out.println(Thread.currentThread().getName() + " start");
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " end");
    }

    private void n() {
        System.out.println(Thread.currentThread().getName() + " start");
        try {
            Thread.sleep(500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " end");
    }

    public static void main(String[] args) {
        SynchronizedDemo_04 demo04 = new SynchronizedDemo_04();
        new Thread(demo04 :: m, "m").start();
        new Thread(demo04 :: n, "n").start();
    }
}
