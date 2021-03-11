package com.java.advance.concurrency.thread;

public class D04_ThreadState {

    private static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("new thread run ");
        }
    }

    public static void main(String[] args) {
        MyThread thread = new MyThread();
        System.out.println("新线程创建："+thread.getState());

        thread.start();
        System.out.println("main 线程"+Thread.currentThread().getState());
        try {
            System.out.println("新线程启动："+thread.getState());
            System.out.println("新线程加入主线程后执行");
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(thread.getState());
    }

}
