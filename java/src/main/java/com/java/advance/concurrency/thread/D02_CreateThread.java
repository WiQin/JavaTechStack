package com.java.advance.concurrency.thread;

import java.util.concurrent.*;

/**
 * 创建线程
 * Thread
 * Runnable
 * 线程池 Executors
 *
 * @author wyw
 * @date 2020/11/26
 */
public class D02_CreateThread {

    public static void main(String[] args) {

        //继承Thread
        Thread thread = new Thread(){
            @Override
            public void run() {
                System.out.println("extends Thread");
            }
        };

        //实现Runnable
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("implements runnable");
            }
        });

        //线程池
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future future = executor.submit(new Callable() {
            @Override
            public String call() throws Exception {
                return "thread pool";
            }
        });

        try {
            thread.start();
            thread1.start();
            String result = (String) future.get();
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
