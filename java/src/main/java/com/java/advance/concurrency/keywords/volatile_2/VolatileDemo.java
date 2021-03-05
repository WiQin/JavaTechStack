package com.java.advance.concurrency.keywords.volatile_2;

/**
 * 保证线程可见性  （MESI 缓存一致性协议 ）
 * 禁止指令重排序
 */
public class  VolatileDemo {
    private int i = 0;
    private volatile boolean flag = false;

    public void write() {
        i = 1;
        flag = true;
    }

    public void read() {
        if (flag) {
            int a = i;
            System.out.println(i);
        }
    }
}
