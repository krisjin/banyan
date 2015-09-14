package com.concurrent.threadpool;

import java.util.concurrent.CountDownLatch;

/**
 * 使用{@link CountDownLatch} 一个同步辅助类，在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待。
 * <p/>
 * User : krisibm@163.com
 * Date: 2015/9/14
 * Time: 14:53
 */
public class WorkerThreadTest {

    public static void main(String[] args) {
        int threadNum = 5;
        CountDownLatch signal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(threadNum);

        for (int i = 1; i <= threadNum; i++) {
            WorkerThread workerThread = new WorkerThread(signal, doneSignal);
            new Thread(workerThread).start();
        }
        try {
            signal.countDown();
            doneSignal.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("主线程结束...........");
    }
}
