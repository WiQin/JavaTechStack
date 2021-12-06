package com.java.advance.concurrency.keywords.synchrinized_1;

/**
 * ???
 */
public class SynchronizedDemo_03 {

    private static Integer i1 = 0;

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
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
