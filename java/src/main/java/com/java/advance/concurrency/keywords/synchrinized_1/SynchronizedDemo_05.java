package com.java.advance.concurrency.keywords.synchrinized_1;

/**
 * 读写方法
 */
public class SynchronizedDemo_05 {
    private String account;
    private Double balance;

    private synchronized void set(String name, Double balance) {
        this.account = name;

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.balance = balance;
    }

    private Double getBalance(String name){
        return this.balance;
    }

    public static void main(String[] args) {
        SynchronizedDemo_05 account = new SynchronizedDemo_05();
        new Thread(() -> account.set("A",100.0)).start();

        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(account.getBalance("A"));

        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(account.getBalance("A"));


    }


}
