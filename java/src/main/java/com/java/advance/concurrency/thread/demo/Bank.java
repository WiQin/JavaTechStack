package com.java.advance.concurrency.thread.demo;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {

    public Lock bankLock;

    public Condition condition;

    /**
     * 账户列表
     */
    private final double[] accounts;

    /**
     * 初始化账户信息及余额
     *
     * @param n           账户数量
     * @param initBalance 初始金额
     */
    public Bank(int n, double initBalance) {
        accounts = new double[n];
        Arrays.fill(accounts, initBalance);
        bankLock = new ReentrantLock();
        condition = bankLock.newCondition();
    }

    /**
     * 转账
     * 显式加锁
     * @param from
     * @param to
     * @param amount
     */
    public void transfer(int from, int to, double amount) throws InterruptedException {
        bankLock.lock();
        try {
            if (accounts[from] < amount) {
                condition.await();
            }
            System.out.print(Thread.currentThread());
            accounts[from] -= amount;
            System.out.printf("%10.2f from %d to %d ", amount, from, to);
            accounts[to] += amount;
            System.out.printf("total balance:%10.2f%n", getTotalBalance());
            condition.signalAll();
        } finally {
            bankLock.unlock();
        }
    }

    /**
     * synchronized
     * @param from
     * @param to
     * @param amount
     * @throws InterruptedException
     */
    public synchronized void transfer2(int from, int to, double amount) throws InterruptedException {

        if (accounts[from] < amount) {
            wait();
        }
        System.out.print(Thread.currentThread());
        accounts[from] -= amount;
        System.out.printf("%10.2f from %d to %d ", amount, from, to);
        accounts[to] += amount;
        System.out.printf("total balance:%10.2f%n", getTotalBalance());
        notifyAll();
    }

    /**
     * 计算所有账户总额
     *
     * @return
     */
    public double getTotalBalance() {
        bankLock.lock();
        try {
            double sum = 0;
            for (double a : accounts) {
                sum += a;
            }
            return sum;
        } finally {
            bankLock.unlock();
        }
    }

    public int size() {
        return accounts.length;
    }
}
