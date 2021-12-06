package com.java.advance.concurrency.keywords.volatile_2;

/**
 * voaltile作用：
 * 保证线程可见性  （MESI 缓存一致性协议 ）
 * 禁止指令重排序
 */
public class VolatileDemo_01 {
    private int i = 0;
    private /*volatile*/ boolean flag = false;

    public void write() {
        i = 1;
        flag = true;
    }

    public void read() {
        if (flag) {
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        VolatileDemo_01 demo = new VolatileDemo_01();

        new Thread(() -> demo.write()).start();

        new Thread(() -> demo.read()).start();

    }
}
