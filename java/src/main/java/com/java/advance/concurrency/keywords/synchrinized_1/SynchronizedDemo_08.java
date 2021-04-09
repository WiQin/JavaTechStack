package com.java.advance.concurrency.keywords.synchrinized_1;

/**
 * 锁升级   偏向锁 自旋锁（占cpu,用户态，适用于执行时间短，线程数少） 重量级锁（操作系统，内核态，线程进入等待队列，不占内存，执行时间长）
 * synchronized的底层实现
 * JDK早期的 重量级 - OS
 * 后来的改进
 * 锁升级的概念：
 *     我就是厕所所长 （一 二）
 *
 * sync (Object)
 * markword 记录这个线程ID （偏向锁）
 * 如果线程争用：升级为 自旋锁
 * 10次以后，
 * 升级为重量级锁 - OS
 *
 * 执行时间短（加锁代码），线程数少，用自旋
 * 执行时间长，线程数多，用系统锁
 */
public class SynchronizedDemo_08 {
}