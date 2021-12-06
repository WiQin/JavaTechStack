package com.java.advance.concurrency.keywords.synchrinized_1;

/**
 * synchronized方法发生异常，锁会被释放  ??
 */
public class SynchronizedDemo_07 {

    private int i = 0;

    private synchronized void m() {
        while (true) {
            try {
                System.out.println(i++);
                if (i == 10) {
                    throw new RuntimeException("error");
                }
            } catch (RuntimeException e) {
                e.printStackTrace();
                System.out.println("error");
            }
        }
    }

    public static void main(String[] args) {
        SynchronizedDemo_07 demo = new SynchronizedDemo_07();

        new Thread(() -> {
            demo.m();
        }).start();
    }
}
