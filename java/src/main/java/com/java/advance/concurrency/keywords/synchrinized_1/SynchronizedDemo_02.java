package com.java.advance.concurrency.keywords.synchrinized_1;

public class SynchronizedDemo_02 {
    public static void main(String[] args) {
        //执行完同步代码块之后紧接着再会去执行一个静态同步方法，
        // 而这个方法锁的对象依然就这个类对象，那么这个正在执行的线程不需要重新获取锁
        synchronized (SynchronizedDemo_02.class) {
            System.out.println("xxx");
        }

        method();
    }

    private static void method() {

    }
}
