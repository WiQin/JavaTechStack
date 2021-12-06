package com.java.advance.concurrency.thread.basic;

/**
 * 线程方法
 * ①sleep方法给其他线程运行机会时不考虑线程的优先级,因此会给低线程优先级运行的机会,
 * 而yield方法只会给相同优先级或者更高优先级线程运行的机会
 * ②线程执行sleep()方法后转入阻塞状态,所以,执行sleep()方法的线程在指定的时间内不会被执行,
 * 而yield()方法只是使当前线程重新回到可执行状态,所以执行yield()方法的线程可能在进入可执行状态后马上又被执行
 * ③sleep()方法声明抛出InterruptedException,而yield()方法没有声明任何异常
 * ④sleep()方法比yield()方法(跟操作系统相关)有更好的可移植性
 *
 *
 * wait和sleep方法的区别：
 * wait只能在同步（synchronize）环境中被调用，而sleep不需要。详见Why to wait and notify needs to call from synchronized method
 * 进入wait状态的线程能够被notify和notifyAll线程唤醒，但是进入sleeping状态的线程不能被notify方法唤醒。
 * wait通常有条件地执行，线程会一直处于wait状态，直到某个条件变为真。但是sleep仅仅让你的线程进入睡眠状态。
 * wait方法在进入wait状态的时候会释放对象的锁，但是sleep方法不会。
 * wait方法是针对一个被同步代码块加锁的对象，而sleep是针对一个线程。更详细的讲解可以参考《Java核心技术卷1》，里面介绍了如何使用wait和notify方法。
 *
 *
 * Java中sleep方法的几个注意点：
 * Thread.sleep()方法用来暂停线程的执行，将CPU放给线程调度器。
 * Thread.sleep()方法是一个静态方法，它暂停的是当前执行的线程。
 * Java有两种sleep方法，一个只有一个毫秒参数，另一个有毫秒和纳秒两个参数。
 * 与wait方法不同，sleep方法不会释放锁
 * 如果其他的线程中断了一个休眠的线程，sleep方法会抛出Interrupted Exception。
 * 休眠的线程在唤醒之后不保证能获取到CPU，它会先进入就绪态，与其他线程竞争CPU。
 * 有一个易错的地方，当调用t.sleep()的时候，会暂停线程t。这是不对的，因为Thread.sleep是一个静态方法，它会使当前线程而不是线程t进入休眠状态。
 *
 *
 * join（）:保证线程先后执行
 */
public class D03_ThreadMethod {

    //sleep
    //就是指让当前正在运行的占用cpu时间片的线程挂起，把cpu的时间片交给其他线程，
    // 但是并没有指定把CPU的时间片接下来到底交给哪个线程，而是让这些线程自己去竞争（一般操作系统会根据优先级调度）
    static void testSleep() {
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            System.out.println("thread_2 start to run ");
        }).start();
    }

    //yield  它能够让当前线程从“运行状态”进入到“就绪状态”，从而让其他等待线程获取执行权，但是不能保证在当前线程调用yield()之后，其他线程就一定能获得执行权，也有可能是当前线程又回到“运行状态”继续运行
    static void testYield() {
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("A"+i);
                if (i%10 == 0) Thread.yield();
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("B"+i);
                if (i%10 == 0) Thread.yield();
            }
        }).start();
    }

    static void testJoin() {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println("t1:" + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t2");
        });
        t1.start();
        t2.start();

    }

    public static void main(String[] args) {
//        testSleep();
//        testYield();
        testJoin();
    }
}
