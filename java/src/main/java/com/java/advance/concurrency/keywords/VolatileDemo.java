package com.java.advance.concurrency.keywords;

public class VolatileDemo {
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
