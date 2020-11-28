package com.java.advance.concurrency.keywords;

public class SynchronizedDemo3 {

    private static Integer i1 = 0;

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    int s = write(i1);
                    read(s);
                }
            });
            thread.start();
        }

    }

    private static int write(int i) {
         i++;
        return i;
    }

    private static void read(int i) {
        int a = i;
        System.out.println(a);
    }


}
