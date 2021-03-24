package com.java.advance.concurrency.keywords.synchrinized_1;

/**
 * synchronized this *.class object 的区别
 */
public class SynchronizedDemo_01 implements Runnable {
    private static int count = 0;

    private Object objectLock = new Object();

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new SynchronizedDemo_01());
            thread.start();
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(count);
    }

//    @Override
//    public void run() {
//        synchronized (SynchronizedDemo_01.class) {
//            for (int i = 0; i < 100000; i++) {
//                count++;
//            }
//        }
//    }


    //因为每次创建线程都new了SynchronizedDemo_01，所以下面加synchronized的方法就会有问题

//    @Override
//    public void run() {
//        synchronized (this) {
//            for (int i = 0; i < 100000; i++) {
//                count++;
//            }
//        }
//    }


//    @Override
//    public synchronized void run() {
//        for (int i = 0; i < 100000; i++) {
//            count++;
//        }
//    }

    @Override
    public void run() {
        synchronized (objectLock) {
            for (int i = 0; i < 100000; i++) {
                count++;
            }
        }
    }
}
