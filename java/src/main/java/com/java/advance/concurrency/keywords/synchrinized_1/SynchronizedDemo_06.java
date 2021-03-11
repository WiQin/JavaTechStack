package com.java.advance.concurrency.keywords.synchrinized_1;

/**
 * synchronized是可重入锁
 * 一个同步方法可以调用另外一个同步方法，一个线程已经拥有某个对象的锁，再次申请的时候仍然会得到该对象的锁.
 * 也就是说synchronized获得的锁是可重入的
 */
public class SynchronizedDemo_06 {

    synchronized void m() {
        System.out.println("m start");
        n();
        System.out.println("m end");

    }

    synchronized void n() {
        System.out.println("n start");

        System.out.println("n end");

    }

    public static void main(String[] args) {
        new SynchronizedDemo_06().m();
    }
}
