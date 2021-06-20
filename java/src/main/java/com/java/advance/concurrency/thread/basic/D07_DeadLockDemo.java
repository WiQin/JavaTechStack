package com.java.advance.concurrency.thread.basic;

/**
 * 死锁测试
 *
 * @author wyw
 * @date 2020/11/26
 */
public class D07_DeadLockDemo {

    private static final String RESOURCE_A = "a";
    private static final String RESOURCE_B = "b";

    public static void main(String[] args) {
        deadLock();
    }

    private static void deadLock() {
        final Thread threadA = new Thread(new Runnable() {
            public void run() {
                synchronized (RESOURCE_A) {
                    System.out.println("get resource a");
                    try {
                        Thread.sleep(3000L);
                        synchronized (RESOURCE_B) {
                            System.out.println("get resource b");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread threadB = new Thread(new Runnable() {
            public void run() {
                synchronized (RESOURCE_B) {
                    System.out.println("get resource b");
                    synchronized (RESOURCE_A) {
                        System.out.println("get resource a");
                    }
                }
            }
        });

        threadA.start();
        threadB.start();

    }

}
