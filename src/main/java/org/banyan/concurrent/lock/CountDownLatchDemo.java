package org.banyan.concurrent.lock;

import java.util.concurrent.CountDownLatch;

/**
 * 该类主要用于并发同步操作，如果是串行好像用不上这个，子任务执行countDown减一，指导count==0
 * 接着继续执行主线程
 * 倒计时锁存器的同步控制。
 * 使用AQS状态表示计数
 * CountDownLatch类位于java.util.concurrent包下，利用它可以实现类似计数器的功能。比如有一个任务A，
 * 它要等待其他4个任务执行完毕之后才能执行，此时就可以利用CountDownLatch来实现这种功能了。
 * <p>
 * CountDownLatch类只提供了一个构造器：
 *
 * @author krisjin
 * @date 2020/11/19
 */
public class CountDownLatchDemo {

    public static void main(String[] args) {
        demo1();


    }

    /**
     *
     */
    public static void demo1() {

        //创建一个控制线程间等待的 thread count down latch
        CountDownLatch countDownLatch = new CountDownLatch(2);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.err.println("t1 done...");
                countDownLatch.countDown();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.err.println("t2 done...");
                countDownLatch.countDown();
            }
        });

        t1.start();
        t2.start();

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.err.println("main done...");
    }

}
