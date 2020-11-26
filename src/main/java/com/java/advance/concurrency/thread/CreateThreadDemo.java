package com.java.advance.concurrency.thread;

import java.util.concurrent.*;

/**
 * ${Description}
 *
 * @author wyw
 * @date 2020/11/26
 */
public class CreateThreadDemo {

    public static void main(String[] args) {

        Thread thread = new Thread(){
            @Override
            public void run() {
                System.out.println("");
            }
        };

        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                System.out.println();
            }
        });
        
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future future = executor.submit(new Callable() {
            public String call() throws Exception {
                return "";
            }
        });

        try {
            String result = (String) future.get();
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //todo FutureTask

    }
}
