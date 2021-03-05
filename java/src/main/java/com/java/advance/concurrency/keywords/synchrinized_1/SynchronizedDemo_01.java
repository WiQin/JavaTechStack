package com.java.advance.concurrency.keywords.synchrinized_1;

public class SynchronizedDemo_01 implements Runnable {
    private static int count = 0;


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

    public void  run() {
        synchronized (SynchronizedDemo_01.class) {
            for (int i = 0; i < 100000; i++) {
                count++;
            }
        }

    }
}
