package com.java.advance.concurrency.thread.thread_pool;

import java.util.concurrent.*;

/**
 * @Descroption: Future&Callable
 * @Author: wangyw
 * @Date: 2021/6/20 15:44
 */
public class D01_FutureAndCallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Callable<String> callable = () -> {
            Thread.sleep(5000);
            return "Callable execute finish";
        };
        System.out.println("提交Callable到线程池");
        Future<String> future = executorService.submit(callable);
        System.out.println("主线程等待获取Future结果");
        String result= future.get();
        System.out.println("主线程获取到结果"+result);

        executorService.shutdown();

    }
}
