package com.java.advance.concurrency.thread.demo;

public class BankTest {

    public static final int COUNTS = 100;
    public static final double INITIAL_BALAMCE = 1000;
    public static final double MAX_AMOUNT = 1000;
    public static final int DELAY = 10;

    public static void main(String[] args) {
        Bank bank = new Bank(COUNTS, INITIAL_BALAMCE);

        for (int i = 0; i < COUNTS; i++) {
            int fromAccount = i;
            Runnable r = () -> {
                try {
                    while (true) {
                        int toAccount = (int) (bank.size() * Math.random());
                        double amount = MAX_AMOUNT * Math.random();
                        bank.transfer(fromAccount, toAccount, amount);
                        Thread.sleep((int) (DELAY * Math.random()));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
        Thread thread = new Thread(r);
        thread.start();
    }
}
}
